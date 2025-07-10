<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
    <h2>Sign Up</h2>

    <!-- Message Dropdown -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <select onchange="alert(this.value)">
            <option value=""><%= message %></option>
            <option value="ok">OK</option>
        </select>
    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/signup" method="post">
        Full Name: <input type="text" name="fullname" required><br><br>
        Username: <input type="text" name="username" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        Address: <br>
        <textarea name="address" rows="5" cols="40" required></textarea><br><br>
        <input type="submit" value="Sign Up">
    </form>
    <a href="view/login.jsp">Login</a>
</body>
</html>
