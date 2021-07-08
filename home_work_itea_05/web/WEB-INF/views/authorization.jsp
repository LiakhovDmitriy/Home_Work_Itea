<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
		<title>Authorization</title>
		<meta charset="UTF-8"/>
		<link href="source/style.css" rel="stylesheet"/>
</head>
<body>


<c:choose>
		<c:when test="${(login==null && password==null) || error != null || userPojo == null}">
				<form id="loginForm" action="/successful" method="post" style="margin: 50px auto 0">

						<div class="field">
								<label>Enter your login:</label>
								<div class="input"><input type="text" name="f1" value="${login}" id="login"/></div>
						</div>
						<div class="field">
								<label>Enter your password:</label>
								<div class="input"><input type="password" name="f2" value="${password}" id="password"/></div>
						</div>
						<div class="submit">
								<button value="send" type="submit">Enter</button>
						</div>
				</form>
		</c:when>


		<c:when test="${userPojo != null}">
				<form style="margin: 50px auto 0" id="loginForm">
						<div class="field"><label>Login: ${login} </label><br>
								<label>Password: ${password}</label><br>
						</div>
				</form>
				<form style="margin: 50px auto 0" id="loginForm">
						<div><label>Hello, ${userPojo}! </label></div>
						<div style='color:green'><label>Access granted!</label></div>
						</div>
				</form>
				<form id="loginForm" style="padding: 0px 24px 0; margin: 10px auto 0" action="/successful" method="get">
						<input
										type="hidden" name="logout"/>
						<div class="submit" style="margin: 0px -24px 0">
								<input style="float: unset" value="logout" type="submit"/>
						</div>
				</form>
		</c:when>

		<c:otherwise>

		</c:otherwise>

</c:choose>

<c:if test="${error != null}">
		<form style="margin: 50px auto 0" id="loginForm">
				<div class="field"><label>${error}</label></div>
		</form>
</c:if>


</form>


</body>
</html>


