<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.datapackage.model.Tea" %>
<%@ page import="java.util.List" %>

<%
    List<Tea> teas = (List<Tea>) request.getAttribute("teas");
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<html>
<head>
    <title>Manage Teas</title>
</head>
<body>
<h1>Tea Management</h1>

<!-- Display success or error message -->
<% if (successMessage != null) { %>
    <p style="color: green;"><%= successMessage %></p>
<% } %>
<% if (errorMessage != null) { %>
    <p style="color: red;"><%= errorMessage %></p>
<% } %>

<!-- Add Tea Form -->
<h2>Add New Tea</h2>
<form action="<%= request.getContextPath() %>/TeaServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="add" />
    Name: <input type="text" name="name" required /><br/>
    Region: <input type="text" name="region" /><br/>
    Grade: <input type="text" name="grade" /><br/>
    Weight: <input type="text" name="weight" /><br/>
    Strength: <input type="text" name="strength" /><br/>
    Price: <input type="text" name="price" required /><br/>
    Quantity: <input type="number" name="quantity" /><br/>
     Image: <input type="file" name="image" accept="image/*" /><br/>
    Seller: <input type="text" name="seller" required /><br/>

    <input type="submit" value="Add Tea" />
</form>

<hr/>

<!-- List of Teas -->
<h2>Tea List</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Region</th>
        <th>Grade</th>
        <th>Weight</th>
        <th>Strength</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Image</th>
        <th>Seller</th>
        <th>Created At</th>
        <th>Actions</th>
    </tr>
    <%
        if (teas != null) {
            for (Tea tea : teas) {
    %>
    <tr>
        <form action="TeaServlet" method="post">
            <td><input type="hidden" name="id" value="<%= tea.getId() %>" /><%= tea.getId() %></td>
            <td><input type="text" name="name" value="<%= tea.getName() %>" /></td>
            <td><input type="text" name="region" value="<%= tea.getRegion() %>" /></td>
            <td><input type="text" name="grade" value="<%= tea.getGrade() %>" /></td>
            <td><input type="text" name="weight" value="<%= tea.getWeight() %>" /></td>
            <td><input type="text" name="strength" value="<%= tea.getStrength() %>" /></td>
            <td><input type="text" name="price" value="<%= tea.getPrice() %>" /></td>
            <td><input type="number" name="quantity" value="<%= tea.getQuantity() %>" /></td>
            <td><input type="text" name="image_url" value="<%= tea.getImageUrl() %>" /></td>
            <td><input type="text" name="seller" value="<%= tea.getSeller() %>" /></td>    
            <td><%= tea.getCreatedAt() %></td>
            <td>
                <input type="hidden" name="action" value="update" />
                <input type="submit" value="Update" />
        </form>
        <form action="TeaServlet" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= tea.getId() %>" />
            <input type="hidden" name="action" value="delete" />
            <input type="submit" value="Delete" onclick="return confirm('Are you sure?');" />
        </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
