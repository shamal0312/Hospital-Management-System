package com.hospitalink.servlet;

import com.hospitalink.service.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        dashboardService = new DashboardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            request.setAttribute("totalPatients", dashboardService.getTotalPatients());
            request.setAttribute("totalDoctors", dashboardService.getTotalDoctors());
            request.setAttribute("totalAppointments", dashboardService.getTotalAppointments());
            request.setAttribute("totalMedicines", dashboardService.getTotalMedicines());
            request.setAttribute("totalReports", dashboardService.getTotalReports());
            request.setAttribute("totalAdmins", dashboardService.getTotalAdmins());

            request.getRequestDispatcher("/hospitalink/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while loading dashboard.");
        }
    }
}