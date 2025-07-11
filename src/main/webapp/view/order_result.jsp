<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.datapackage.model.OrderItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Result</title>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
    if (cart != null && !cart.isEmpty()) {
%>
    <p>Welcome, <strong><%= username %></strong>!</p>
    <h2><%= request.getAttribute("message") %></h2>
    <br>
    <a href="<%= request.getContextPath() %>/displayTea">Continue Shopping</a>
<%
    } else {
%>
    <p>Your cart is empty. <a href="<%= request.getContextPath() %>/displayTea">Shop Now</a></p>
<%
    }
%>
</body>
</html>
