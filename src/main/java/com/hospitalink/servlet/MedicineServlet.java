package com.hospitalink.servlet;

import com.hospitalink.model.Medicine;
import com.hospitalink.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/medicine")
public class MedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MedicineService medicineService;

    @Override
    public void init() throws ServletException {
        medicineService = new MedicineService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            if (action == null) {
                // List all medicines
                request.setAttribute("medicines", medicineService.getAllMedicines());
                request.getRequestDispatcher("hospitalink/medicine/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                // Show create form
                request.getRequestDispatcher("hospitalink/medicine/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Medicine medicine = medicineService.getMedicine(id);
                if (medicine != null) {
                    request.setAttribute("medicine", medicine);
                    request.getRequestDispatcher("hospitalink/medicine/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Medicine not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                medicineService.deleteMedicine(id);
                response.sendRedirect(request.getContextPath() + "/medicine");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
                String expiryDate = request.getParameter("expiryDate");

                Medicine medicine = new Medicine();
                medicine.setName(name);
                medicine.setDescription(description);
                medicine.setStockQuantity(stockQuantity);
                medicine.setExpiryDate(expiryDate);

                if (medicineService.createMedicine(medicine)) {
                    response.sendRedirect(request.getContextPath() + "/medicine");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create medicine.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
                String expiryDate = request.getParameter("expiryDate");

                Medicine medicine = new Medicine();
                medicine.setMedicineId(id);
                medicine.setName(name);
                medicine.setDescription(description);
                medicine.setStockQuantity(stockQuantity);
                medicine.setExpiryDate(expiryDate);

                if (medicineService.updateMedicine(medicine)) {
                    response.sendRedirect(request.getContextPath() + "/medicine");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update medicine.");
                }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}