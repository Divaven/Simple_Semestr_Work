<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация</h2>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="${pageContext.request.contextPath}/register" method="post">
    Имя: <input type="text" name="name" maxlength="50" required><br>
    Email: <input type="email" name="email" maxlength="50" required><br>
    Пароль: <input type="password" name="password" maxlength="50" required><br>
    <input type="submit" value="Зарегистрироваться">
</form>
<p>Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a></p>
</body>
</html>
