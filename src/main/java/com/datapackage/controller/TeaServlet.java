package com.datapackage.controller;

import com.datapackage.dao.TeaDAO;
import com.datapackage.model.Tea;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,   // 1MB
    maxFileSize = 5 * 1024 * 1024,     // 5MB
    maxRequestSize = 10 * 1024 * 1024  // 10MB
)
@WebServlet("/TeaServlet")
public class TeaServlet extends HttpServlet {
    private final TeaDAO teaDAO = new TeaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                tea.setSeller(request.getParameter("seller"));


                // Handle image upload
                Part imagePart = request.getPart("image");
                String fileName = null;

                if (imagePart != null && imagePart.getSize() > 0) {
                    fileName = System.currentTimeMillis() + "_" + imagePart.getSubmittedFileName();
                    String uploadPath = getServletContext().getRealPath("") + "images";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir(); // create /images if not exists
                    imagePart.write(uploadPath + File.separator + fileName);
                }

                tea.setImageUrl(fileName != null ? "images/" + fileName : null);
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
                tea.setImageUrl(request.getParameter("image_url")); // No upload on update for now

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tea> teas = teaDAO.getAllTeas();
        request.setAttribute("teas", teas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/manage_teas.jsp");
        dispatcher.forward(request, response);
    }
}
