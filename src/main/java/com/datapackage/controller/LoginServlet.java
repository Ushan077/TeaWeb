package com.datapackage.controller;

import java.io.IOException;

import com.datapackage.dao.UserDAO;
import com.datapackage.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);
        UserDAO dao = new UserDAO();

        if (dao.validateUser(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            if ("admin".equalsIgnoreCase(username)) {
                response.sendRedirect("view/adminhome.jsp");
            } else {
                response.sendRedirect("view/homepage.jsp");
            }


        } else {
            // Login failed
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}
