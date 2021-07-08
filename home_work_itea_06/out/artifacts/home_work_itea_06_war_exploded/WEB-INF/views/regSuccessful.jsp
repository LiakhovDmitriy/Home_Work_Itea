<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
			<title>Registration</title>
			<meta charset="UTF-8"/>
			<link href="source/style.css" rel="stylesheet"/>
</head>
<body>
<form action="/authorization" method="get" align="center" id="loginForm" style="margin-top: 20px">
			<h5>Registration successful! </h5>
			<h5>Hello, ${login}</h5>
			<div class="field">
						<button type="submit" value="send">Go to authorization page.</button>
			</div>
</form>

</body>
</html>
