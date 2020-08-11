<%@ page import="liachov.DBwork" %>
<%@include file="includes/menu.jsp" %>
<%@ page import="java.util.Date" %>

<%!
			long dateStart = 0;
			int counter = 0;
			int timeCount = 5000;
%>
<%
			String login = request.getParameter ("f1");
			String password = request.getParameter ("f2");
			boolean isShowForm = false;
			StringBuilder result = new StringBuilder ();
			if (request.getParameter ("logout") != null) {
						session.setAttribute ("userName", null);
			}

			if (session.getAttribute ("userName") != null) {
						isShowForm = false;
%>
<form id="loginForm" style="padding: 0px 24px 0" action="" method="post"><input type="hidden" name="logout"/>
			<div class="submit" style="margin: 0px -24px 0">
						<button style="float: unset" value="send" type="submit" value="logout">Logout</button>
			</div>
</form>
<%
			} else {
						if (login == null && password == null && dateStart == 0) {
									isShowForm = true;
						} else {
									if (login.length () == 0 && password.length () == 0) {
												result.append ("<form style=\"margin: 50px auto 0 \" id=\"loginForm\" ><div class=\"field\"><label>Please input value of login and password first</label></div></form>");
												isShowForm = true;
												counter++;
									} else {
												out.write ("<form style=\"margin: 50px auto 0 \" id=\"loginForm\" ><div class=\"field\"><label>Login: " + login + "</label><br>");
												out.write ("<label>Password: " + password + "</label><br>");
												DBwork dbWork = new DBwork ();
												String nameUser = dbWork.getName (login, password);
												if (nameUser != null) {
															session.setAttribute ("userName", nameUser);
															result.append ("<div><label>Hello, " + nameUser + "! </label></div>");
															result.append ("<div style='color:green'><label>Access granted!</label></div></div></form>");

												} else {
															result.append ("<div style='color:red'><label>Access denied!</label></div></div></form>");
															isShowForm = true;
															counter++;
												}
									}
						}


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
			}
			out.write (result.toString ());

			if (isShowForm) {

%>
<body>
<form id="loginForm" action="" method="post" style="margin: 50px auto 0">
			<div class="field">
						<label>Enter your login:</label>
						<div class="input"><input type="text" name="f1" value="" id="login"/></div>
			</div>
			<div class="field">
						<label>Enter your password:</label>
						<div class="input"><input type="password" name="f2" value="" id="password"/></div>
			</div>
			<div class="submit">
						<button value="send" type="submit">Enter</button>

			</div>
</form>
</body>
</html>
<%}%>

