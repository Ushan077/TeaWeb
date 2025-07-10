package com.datapackage.controller;



import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.datapackage.dao.UserDAO;
import com.datapackage.model.User;
import com.datapackage.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO(); // updated to default constructor

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                User user = new User();
                user.setFullname(request.getParameter("fullname"));
                user.setUsername(request.getParameter("username"));
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("password"));
                user.setAddress(request.getParameter("address"));
                userDAO.addUser(user);
                request.setAttribute("successMessage", "User added successfully!");
            } else if ("update".equals(action)) {
                User user = new User();
                user.setId(Integer.parseInt(request.getParameter("id")));
                user.setFullname(request.getParameter("fullname"));
                user.setAddress(request.getParameter("address"));
                userDAO.updateUser(user);
                request.setAttribute("successMessage", "User updated successfully!");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                userDAO.deleteUser(id);
                request.setAttribute("successMessage", "User deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to load users.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/manage_users.jsp");
        dispatcher.forward(request, response);
    }
}
