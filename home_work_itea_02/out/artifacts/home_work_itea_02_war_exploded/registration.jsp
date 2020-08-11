<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Registration</title>
</head>
    <%
    boolean showForm = true;
    boolean isError = false;
    StringBuilder errorText = new StringBuilder();
    errorText.append("<table class='table'>");

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
            errorText.append("<tr><td>The 'login' is empty.</td></td>");
            isError = true;
        }

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(login);
        boolean loginBol = matcher.matches();
        if (!loginBol) {
            errorText.append("<tr><td>The 'login' is wrong. It should look like 'name@gmail.com'.</td></td>");
            isError = true;
        }
        if (password.isEmpty()) {
            errorText.append("<tr><td>The 'password' is empty.</td></td>");
            isError = true;
        }
        if (rePassword.isEmpty()) {
            errorText.append("<tr><td>The 'rePassword' is empty.</td></td>");
            isError = true;
        }
        if(!password.isEmpty() && !rePassword.isEmpty() && password.equals(rePassword)){ //если не пусті і однакові
        Pattern patternP = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher matcherP = patternP.matcher(password);
        boolean passwordBol = matcherP.matches();
        if (!passwordBol) {
            errorText.append("<tr><td>The 'password' and 'rePassword' must be the same and at least 8 characters, contains at least one digit, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string.</td></td>");
            isError = true;
        }
        } else {
            errorText.append("<tr><td>The 'password' and 'rePassword' must be the same.</td></td>");
            isError = true;
        }
        if (name.isEmpty()) {
            errorText.append("<tr><td>The 'name' is empty.</td></td>");
            isError = true;
        }
        Pattern patternN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).{3,}$");
        Matcher matcherN = patternN.matcher(name);
        boolean passwordName = matcherN.matches();
        if (!passwordName) {
            errorText.append("<tr><td>The 'Name' must be the correct. At least 3 characters, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string</td></td>");
            isError = true;
        }
        if (gender.isEmpty()) {
            errorText.append("<tr><td>The 'gender' is empty.</td></td>");
            isError = true;
        }
        if (address.isEmpty()) {
            errorText.append("<tr><td>The 'address' is empty.</td></td>");
            isError = true;
        }
        if (comment.isEmpty()) {
            errorText.append("<tr><td>The 'comment' is empty.</td></td>");
            isError = true;
        }
        if (agree == null) {
            errorText.append("<tr><td>The 'agree' is empty.</td></td>");
            isError = true;
        }
        if (!isError) {
%>
<center>
    <table class="table">
        <tr>
            <td>
                <h5 align="center">Registration successful!</h5>

                <form action="authorization.jsp" align="center">
                    <button type="submit" value="send" class="btn btn-dark" >Go to authorization page.</button>
                </form>
            </td>
        </tr>
    </table>
</center>
    <%
            showForm = false;
        }
    }
    errorText.append("</table>");
    if (showForm) {%>

<center>
    <table>
        <tr>
            <td>
                <form>
                    <table height="350">
                        <tr>
                            <td>Login:</td>
                            <td><input type="text" name="login" value="<%=(login!=null?login:"")%>"></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="password" ></td>
                        </tr>
                        <tr>
                            <td>Retype password:</td>
                            <td><input type="password" name="rePassword"></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="name" value="<%=(name!=null?name:"")%>"></td>
                        </tr>
                        <tr>
                            <td>Gender:</td>
                            <td>
                                Male<input type="radio" name="gender"
                                           value="male" <%=(gender!=null && gender.equals("male")?"checked":"")%>
                                <%=(gender==null?"checked":"")%>>
                                Female<input type="radio" name="gender"
                                             value="female" <%=(gender!=null && gender.equals("female")?"checked":"")%>>
                            </td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td><select name="address">
                                <option value="lnr"<%=(address != null && address.equals("lnr") ? "selected" : "")%>>
                                    LRN
                                </option>
                                <option value="dnr"<%=(address != null && address.equals("dnr") ? "selected" : "")%>>
                                    DNR
                                </option>
                                <option value="crimea"<%=(address != null && address.equals("crimea") ? "selected" : "")%>>
                                    Crimea
                                </option>
                            </select></td>
                        </tr>
                        <tr>
                            <td>Comment:</td>
                            <td><textarea name="comment" cols="25" rows="5" ></textarea></td>
                        </tr>
                        <tr>
                            <td>I agree:</td>
                            <td><input type="checkbox" name="agree"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="send"></td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <%out.write(errorText.toString());%>
            </td>
        </tr>
    </table>
    <%}%>
</center>
