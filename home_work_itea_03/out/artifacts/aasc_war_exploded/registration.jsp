<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<%@include file="includes/menu.jsp" %>
<html>
<head>
			<title>Registration</title>
</head>
			<%
    boolean showForm = true;
    boolean isError = false;
    StringBuilder errorText = new StringBuilder();
    errorText.append("<form id='loginForm' style='margin-top: 20px'> <div class='field'>");

    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String rePassword = request.getParameter("rePassword");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String address = request.getParameter("address");
    String comment = request.getParameter("comment");
    String agree = request.getParameter("agree");

    if (login != null) {
        if (login.isEmpty()) {
            errorText.append("<label>The 'login' is empty.</label>");
            isError = true;
        }

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(login);
        boolean loginBol = matcher.matches();
        if (!loginBol) {
            errorText.append("<label>The 'login' is wrong. It should look like 'name@gmail.com'.</label>");
            isError = true;
        }
        if (password.isEmpty()) {
            errorText.append("<label>The 'password' is empty.</label>");
            isError = true;
        }
        if (rePassword.isEmpty()) {
            errorText.append("<label>The 'rePassword' is empty.</label>");
            isError = true;
        }
        if(!password.isEmpty() && !rePassword.isEmpty() && password.equals(rePassword)){ //если не пусті і однакові
        Pattern patternP = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher matcherP = patternP.matcher(password);
        boolean passwordBol = matcherP.matches();
        if (!passwordBol) {
            errorText.append("<label>The 'password' and 'rePassword' must be the same and at least 8 characters, contains at least one digit, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string.</label>");
            isError = true;
        }
        } else {
            errorText.append("<label>The 'password' and 'rePassword' must be the same.</label>");
            isError = true;
        }
        if (name.isEmpty()) {
            errorText.append("<label>The 'name' is empty.</label>");
            isError = true;
        }
        Pattern patternN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).{3,}$");
        Matcher matcherN = patternN.matcher(name);
        boolean passwordName = matcherN.matches();
        if (!passwordName) {
            errorText.append("<label>The 'Name' must be the correct. At least 3 characters, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string</label>");
            isError = true;
        }
        if (gender.isEmpty()) {
            errorText.append("<label>The 'gender' is empty.</label>");
            isError = true;
        }
        if (address.isEmpty()) {
            errorText.append("<label>The 'address' is empty.</label>");
            isError = true;
        }
        if (comment.isEmpty()) {
            errorText.append("<label>The 'comment' is empty.</label>");
            isError = true;
        }
        if (agree == null) {
            errorText.append("<label>The 'agree' is empty.</label>");
            isError = true;
        }
        errorText.append("</div></form>");
        if (!isError) {
%>

<form action="authorization.jsp" align="center" id="loginForm" style="margin-top: 20px">
			<h5>Registration successful!</h5>
			<div class="field">
						<button type="submit" value="send">Go to authorization page.</button>
			</div>
</form>

			<%
            showForm = false;
        }
    }
    errorText.append("</table>");
    if (showForm) {
%>

<form id="loginForm" style="margin-top: 0px">

			<div class="field">
						<label>Login:</label>
						<div class="input"><input type="text" name="login" value="<%=(login!=null?login:"")%>" id="login"/></div>
						<label>Password:</label>
						<div class="input"><input type="password" name="password" value="<%=(password!=null?password:"")%>"
																																id="password"/></div>
						<label>Retype password:</label>
						<div class="input"><input type="password" name="rePassword" value="<%=(rePassword!=null?rePassword:"")%>"
																																id="rePassword"/></div>
						<label>Name:</label>
						<div class="input"><input type="text" name="name" value="<%=(name!=null?name:"")%>" id="name"/></div>

						<label style="margin-bottom: 5px">Gender:
									<div style="margin-left: 20px">Male<input style="box-shadow: unset; width: 10px" type="radio" name="gender"
																																																			value="male" <%=(gender!=null && gender.equals("male")?"checked":"")%>
												<%=(gender==null?"checked":"")%>></div>
									<div style="margin-left: 20px">Female<input style="box-shadow: unset; width: 10px" type="radio" name="gender"
																																																					value="female" <%=(gender!=null && gender.equals("female")?"checked":"")%> <%=(gender==null?"checked":"")%>>
									</div>
						</label>

						<label>Address:</label>
						<div class="input">
									<select name="address">
												<option value="lnr"<%=(address != null && address.equals ("lnr") ? "selected" : "")%>>
															LRN
												</option>
												<option value="dnr"<%=(address != null && address.equals ("dnr") ? "selected" : "")%>>
															DNR
												</option>
												<option value="crimea"<%=(address != null && address.equals ("crimea") ? "selected" : "")%>>
															Crimea
												</option>
									</select></td>
						</div>

						<label>Comment:</label>
						<div class="input"><textarea name="comment" cols="25" rows="5"></textarea></div>

						<div class="submit">
									<label>I agree:
												<input style="box-shadow: unset; width: 20px " type="checkbox" name="agree">
												<button value="send" type="submit">Enter</button>
									</label>
						</div>
			</div>

</form>
			<%out.write (errorText.toString ());%>


			<% }%>
