package com.datapackage.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.datapackage.dao.UserDAO;
import com.datapackage.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        User user = new User();
        user.setFullname(fullname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);

        UserDAO dao = new UserDAO();
        boolean result = dao.registerUser(user);

        if (result) {
            request.setAttribute("message", "Sign Up Successful!");
        } else {
            request.setAttribute("message", "Sign Up Failed. Please try again.");
        }

        request.getRequestDispatcher("/view/signup.jsp").forward(request, response);

    }
}