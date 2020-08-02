<%--
  Created by IntelliJ IDEA.
  User: GiangOggy
  Date: 01/08/2020
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form method="post" action="CompleteChangePass">
    <input style="visibility: hidden" name="email" type="text" value="<%=email%>">
    <input name="pass" type="password">
    <input name="repass" type="password">
    <input type="submit">
</form>
</body>
</html>

