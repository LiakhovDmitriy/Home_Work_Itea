<%--
  Created by IntelliJ IDEA.
  User: dimal
  Date: 26.07.2020
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Table builder</title>
</head>
<body>
<%
    String width = request.getParameter("width");
    String height = request.getParameter("height");
    int widthInt = 0;
    int heightInt = 0;
    boolean error = false;
    if ((width == "" && height == "") || width == null && height == null) {
%>
<center>
    <form action="" class="table">
        <table border="0">
            <tr>
                <td>Width</td>
                <td><input name="width" type="text"/></td>
            </tr>
            <tr>
                <td>Height</td>
                <td><input name="height" type="text"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="center"><input value="send" type="submit" class="btn btn-dark"/></td>
            </tr>
        </table>
    </form>
</center>
<%
    }
    if ((width != null || height != null) && (width != "" || height != "")) {
        try {
            widthInt = Integer.parseInt(width);
            heightInt = Integer.parseInt(height);
        } catch (Exception e) {
            width = "";
            height = "";
            error = true;
        }
        if (error || widthInt <= 0 || heightInt <= 0) {
%>
<center>
    <table class="table" align="center">
        <tr align="center">
            <td>
                <p>You input incorrect</p>
                <form action="">
                    <button type="submit" value="send" class="btn btn-dark">Try again</button>
                </form>
            </td>
        </tr>
    </table>
</center>
<%
} else {
%>
<table class="table" align="center">
    <%
        int c = 1;
        for (int i = 0; widthInt > i; i++) {
            out.write("<tr>");
            for (int j = 0; heightInt > j; j++) {
                if (j == i) {
                    out.write("<td align='center' bgcolor='#67abf0'>" + c + "</td>");
                } else out.write("<td align='center'>" + c + "</td>");
                c++;
            }
            out.write("</tr>");
        }
    %>
</table>
<%
        }
    }
%>
</body>
</html>
