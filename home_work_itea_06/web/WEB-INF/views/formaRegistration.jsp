<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

		<title>Registration</title>
		<meta charset="UTF-8"/>
		<link href="source/style.css" rel="stylesheet"/>
</head>
<body>

<form id="loginForm" style="margin-top: 0px" action="/registration" method="post">


		<div class="field">

				<label>Login:</label>
				<div class="input"><input type="text" name="login" value="${login}" id="login"/></div>
				<label>Password:</label>
				<div class="input"><input type="password" name="password" value="${password}"
																	id="password"/></div>
				<label>Retype password:</label>
				<div class="input"><input type="password" name="rePassword" value="" id="rePassword"/></div>
				<label>Name:</label>
				<div class="input"><input type="text" name="name" value="${name}" id="name"/></div>

				<label style="margin-bottom: 5px">Gender:


						Male<input style="box-shadow: unset; width: 10px" type="radio" name="gender"
											 value="male"
						<c:if test="${gender == null}">
											 checked
						</c:if>
						${genderM} id="genderM">

						Female<input style="box-shadow: unset; width: 10px" type="radio"
												 name="gender"
												 value="female" id="genderF" ${genderF}>
				</label>

				<label>Address:</label>
				<div class="input">
						<select name="address">
								<option value="lrn" ${addressLrn}>
										LRN
								</option>
								<option value="dnr" ${addressDnr}>
										DNR
								</option>
								<option value="crimea" ${addressCrimea}>
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

${ERROR}

</body>
</html>
