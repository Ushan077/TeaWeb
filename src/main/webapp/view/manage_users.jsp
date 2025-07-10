<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.datapackage.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Users</title>
   
</head>
<body>

<h2>User Management</h2>

<%-- Show success or error messages --%>
<% if (request.getAttribute("successMessage") != null) { %>
    <div class="message success"><%= request.getAttribute("successMessage") %></div>
<% } else if (request.getAttribute("errorMessage") != null) { %>
    <div class="message error"><%= request.getAttribute("errorMessage") %></div>
<% } %>

<%-- Add User Form --%>
<h3>Add New User</h3>
<form method="post" action="UserListServlet">
    <input type="hidden" name="action" value="add" />
    <input type="text" name="fullname" placeholder="Full Name" required />
    <input type="text" name="username" placeholder="Username" required />
    <input type="email" name="email" placeholder="Email" required />
    <input type="password" name="password" placeholder="Password" required />
    <input type="text" name="address" placeholder="Address" required />
    <button type="submit">Add User</button>
</form>

<%-- Display all users in a table --%>
<h3>Users List</h3>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Fullname</th>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
        %>
        <tr>
            <form method="post" action="UserListServlet">
                <input type="hidden" name="id" value="<%= user.getId() %>">
                <td><%= user.getId() %></td>
                <td><input type="text" name="fullname" value="<%= user.getFullname() %>" required></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPassword() %></td>
                <td><input type="text" name="address" value="<%= user.getAddress() %>" required></td>
                <td>
                    <button type="submit" name="action" value="update">Update</button>
                    <button type="submit" name="action" value="delete" onclick="return confirm('Delete this user?')">Delete</button>
                </td>
            </form>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="7">No users found.</td></tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
