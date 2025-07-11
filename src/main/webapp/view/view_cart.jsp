<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.datapackage.model.OrderItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Shopping Cart</title>
</head>
<body>

	<%
	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
	%>

    <%
    List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
    if (cart != null && !cart.isEmpty()) {
    %>
    <p>Welcome, <strong><%= username %></strong>!</p>
    <table border="1">
        <tr>
            <th>Tea Name</th>
            <th>Quantity</th>
            <th>Unit Price (Rs)</th>
            <th>Total (Rs)</th>
            <th>Seller</th>
            
        </tr>
        <%
        java.math.BigDecimal totalAmount = new java.math.BigDecimal(0);
        for (OrderItem item : cart) {
            java.math.BigDecimal itemTotal = item.getTea().getPrice().multiply(new java.math.BigDecimal(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        %>
        <tr>
			    <td><%= item.getTea().getName() %></td>
			    <td><%= item.getQuantity() %></td>
			    <td><%= item.getTea().getPrice() %></td>
			    <td><%= itemTotal %></td>
			    <td><%= item.getTea().getSeller() %></td>
		</tr>

        <% } %>
        <tr>
            <td colspan="3" align="right"><strong>Total Amount:</strong></td>
            <td><strong><%= totalAmount %></strong></td>
        </tr>
    </table>

    <form action="${pageContext.request.contextPath}/checkout" method="post">
    <button type="submit">Place Order</button>
</form>


    <% } else { %>
        <p>Your cart is empty.</p>
    <% } %>

    <br>
    <a href="displayTea">Continue Shopping</a>
</body>
</html>
