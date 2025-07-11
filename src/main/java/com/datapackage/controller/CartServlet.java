package com.datapackage.controller;

import com.datapackage.dao.TeaDAO;
import com.datapackage.model.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private TeaDAO teaDAO = new TeaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teaId = Integer.parseInt(request.getParameter("teaId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Tea tea = teaDAO.getTeaById(teaId);
        boolean exists = false;
        for (OrderItem item : cart) {
            if (item.getTea().getId() == teaId) {
                item.setQuantity(item.getQuantity() + quantity);
                exists = true;
                break;
            }
        }

        if (!exists) {
            OrderItem item = new OrderItem();
            item.setTea(tea);
            item.setQuantity(quantity);
            cart.add(item);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("view/view_cart.jsp");
    }
}
