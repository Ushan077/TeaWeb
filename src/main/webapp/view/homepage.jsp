<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>

<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp"); // Redirect to login if session expired
        return;
    }
%>

    <h2>Welcome, <%= username %>!</h2>
    <p>Explore, buy, and post tea products easily!</p>

    <br><br><br><br><br><br><br><br><br><br>

    <hr>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/displayTea">Buy Tea</a> | 
        <a href="post_tea.jsp">Post Tea</a> | 
        <a href="about_us.jsp">About Us</a> | 
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>

    </div>

</body>
</html>
