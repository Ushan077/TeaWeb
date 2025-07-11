package com.datapackage.controller;



import java.io.IOException;
import java.util.List;

import com.datapackage.dao.TeaDAO;
import com.datapackage.model.Tea;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/displayTea")
public class DisplayTeaServlet extends HttpServlet {

    private TeaDAO teaDAO;

    @Override
    public void init() throws ServletException {
        teaDAO = new TeaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tea> teaList = teaDAO.getAllTeas();
        request.setAttribute("teaList", teaList);
        request.getRequestDispatcher("view/buy_tea.jsp").forward(request, response); // You'll need to create this JSP
    }
}
