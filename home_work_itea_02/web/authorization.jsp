<%@ page import="java.util.Date" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<html>
<head>
	  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
			integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	  <title>Authorization</title>
</head>
<%!
	  long dateStart = 0;
	  int counter = 0;
	  int timeCount = 5000;
%>
<body>
<%
	  String login = request.getParameter ("login");
	  String password = request.getParameter ("password");
	  boolean isShowForm = false;
	  StringBuilder result = new StringBuilder ();
	  result.append ("<center><table class='table'>");

	  if (login == null && password == null && dateStart == 0) {
			isShowForm = true;
	  } else {
			if (login.length () == 0 && password.length () == 0) {
				  result.append ("<tr><td align=\"center\">Please input value of login and password first</td></tr>");
				  isShowForm = true;
				  counter++;
			} else {
				  result.append ("<tr><td align=\"center\">Login: " + login + "</td></tr><br>");
				  result.append ("<tr><td align=\"center\">Password: " + password + "</td></tr>");
				  Pattern pattern = Pattern.compile ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				  Matcher matcher = pattern.matcher (login);
				  boolean loginBol = matcher.matches ();
				  Pattern patternP = Pattern.compile ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
				  Matcher matcherP = patternP.matcher (password);
				  boolean passwordBol = matcherP.matches ();
				  if (loginBol && passwordBol) {
						result.append ("<tr><td align=\"center\"><div style='color:green'>Access granted!</div></td></tr>");
						result.append ("<td>\n" +
								"                <form action=\"homePage.jsp\" align=\"center\">\n" +
								"                    <button type=\"submit\" value=\"send\" class=\"btn btn-dark\" >Go to home page.</button>\n" +
								"                </form>\n" +
								"            </td>");

				  } else {
						result.append ("<tr><td align=\"center\"><div style='color:red'>Access denied! <br>Login should look like 'name@gmail.com'<br>Password at least 8 characters, contains at least one digit, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string</div></td></tr>");

						isShowForm = true;
						counter++;
				  }
			}
	  }
%>
</center>
<%
	  if (counter >= 3) {
			if (dateStart == 0) {
				  dateStart = new Date ().getTime ();
			}
			isShowForm = false;
			Date currentTime = new Date ();
			long timeLeft = (dateStart + timeCount) - currentTime.getTime ();

			if (currentTime.getTime () >= dateStart + timeCount) {
				  isShowForm = true;
				  counter = 0;
				  dateStart = 0;
			} else {
				  result.append ("Time left: " + (timeLeft / 1000));
			}
	  }
	  out.write (result.toString ());

	  if (isShowForm) {
%>
<center>
	  <form action="">
			<table border="0">
				  <tr>
						<td>Login</td>
						<td><input name="login" type="text"/></td>
				  </tr>
				  <tr>
						<td>Password</td>
						<td><input name="password" type="password"/></td>
				  </tr>
				  <tr>
						<td></td>
						<td align="right"><input value="send" type="submit"/></td>
				  </tr>
			</table>
	  </form>
</center>
<%}%>
</body>
</html>

