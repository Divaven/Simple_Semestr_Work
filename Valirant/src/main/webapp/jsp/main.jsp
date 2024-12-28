<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 28.12.2024
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.itis.dto.UserDTO" %>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
%>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<h2>Добро пожаловать, <%= user.getName() %>!</h2>
<p>Роль: <%= user.getRole() %></p>

</body>
</html>
