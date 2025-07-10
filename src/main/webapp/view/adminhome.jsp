<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null || !"admin".equalsIgnoreCase(username)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>

    <h2>Welcome, Admin!</h2>
    <p>This is your admin control panel.</p>

    <hr>

    <h3>Management Sections:</h3>
    <ul>
        <li><form action="${pageContext.request.contextPath}/UserListServlet" method="get">
            <input type="submit" value="Manage Users">
        </form></li>
        <li><form action="${pageContext.request.contextPath}/TeaServlet" method="get">
            <input type="submit" value="Manage Posts">
        </form></li>
        <li><form action="manage_reviews.jsp" method="get">
            <input type="submit" value="Manage Reviews">
        </form></li>
    </ul>

    <br><br>
    <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>

</body>
</html>
