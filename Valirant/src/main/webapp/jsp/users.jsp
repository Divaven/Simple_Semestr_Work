<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 27.12.2024
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/profile" method="post">
    <input name="first_name" placeholder="Your name">
    <input type="submit" value="Send">
</form>
<table>
    <tr>
        <th>ID</th>
        <th>FIRST NAME</th>
        <th>LAST NAME</th>
        <th>AGE</th>
    </tr>

    <c:forEach items="${usersForJsp}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>

        </tr>
    </c:forEach>


</table>
</body>
</html>
