package com.datapackage.controller;

import com.datapackage.dao.TeaDAO;
import com.datapackage.model.Tea;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/TeaServlet")
public class TeaServlet extends HttpServlet {
    private final TeaDAO teaDAO = new TeaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                Tea tea = new Tea();
                tea.setName(request.getParameter("name"));
                tea.setRegion(request.getParameter("region"));
                tea.setGrade(request.getParameter("grade"));
                tea.setWeight(new BigDecimal(request.getParameter("weight")));
                tea.setStrength(request.getParameter("strength"));
                tea.setPrice(new BigDecimal(request.getParameter("price")));
                tea.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                tea.setImageUrl(request.getParameter("image_url"));
                teaDAO.addTea(tea);
                request.setAttribute("successMessage", "Tea added successfully!");
            } else if ("update".equals(action)) {
                Tea tea = new Tea();
                tea.setId(Integer.parseInt(request.getParameter("id")));
                tea.setName(request.getParameter("name"));
                tea.setRegion(request.getParameter("region"));
                tea.setGrade(request.getParameter("grade"));
                tea.setWeight(new BigDecimal(request.getParameter("weight")));
                tea.setStrength(request.getParameter("strength"));
                tea.setPrice(new BigDecimal(request.getParameter("price")));
                tea.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                tea.setImageUrl(request.getParameter("image_url"));
                teaDAO.updateTea(tea);
                request.setAttribute("successMessage", "Tea updated successfully!");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                teaDAO.deleteTea(id);
                request.setAttribute("successMessage", "Tea deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tea> teas = teaDAO.getAllTeas();
        request.setAttribute("teas", teas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/manage_teas.jsp");
        dispatcher.forward(request, response);
    }
}
