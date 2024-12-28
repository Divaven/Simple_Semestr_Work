<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<h2>Вход</h2>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="${pageContext.request.contextPath}/login" method="post">
    Email: <input type="email" name="email" required><br>
    Пароль: <input type="password" name="password" required><br>
    <input type="submit" value="Войти">
</form>
<p><a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></p>
</body>
</html>
