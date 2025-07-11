package com.datapackage.controller;

import com.datapackage.dao.OrderDAO;
import com.datapackage.dao.UserDAO;
import com.datapackage.model.OrderItem;
import com.datapackage.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

    @WebServlet("/checkout")
    public class CheckoutServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private UserDAO userDAO;
        private OrderDAO orderDAO;

        @Override
        public void init() {
            userDAO = new UserDAO();
            orderDAO = new OrderDAO();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String username = (String) session.getAttribute("username");

            try {
                // Get User from DB
                User user = userDAO.getUserByUsername(username); // ← You must implement this method

                // Get Cart
                @SuppressWarnings("unchecked")
                List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

                if (user != null && cart != null && !cart.isEmpty()) {
                    int orderId = orderDAO.createOrder(user, cart);

                    // Clear cart after order
                    session.removeAttribute("cart");

                    request.setAttribute("message", "Order placed successfully. Your Order ID is " + orderId);
                } else {
                    request.setAttribute("message", "Unable to place order. Cart is empty or user not found.");
                }

                request.getRequestDispatcher("view/order_result.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "An error occurred while placing the order.");
                request.getRequestDispatcher("view/order_result.jsp").forward(request, response);
            }
        }
    }

    

