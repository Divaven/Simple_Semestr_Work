<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 28.12.2024
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.itis.models.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("/jsp/login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Добро пожаловать</title>
</head>
<body>
<h2>Добро пожаловать, <%= user.getName() %>!</h2>
<p>Ваша роль: <%= user.getRole() %></p>
<p>Ваш рейтинг: <%= user.getRating() %></p>
</body>
</html>
