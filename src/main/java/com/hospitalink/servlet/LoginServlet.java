package com.hospitalink.servlet;

import com.hospitalink.model.Admin;
import com.hospitalink.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if session already exists
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        // Authenticate admin
        Admin admin = authenticateAdmin(username, password);

        if (admin != null) {
            // Create a new session for the authenticated admin
            session = request.getSession(true);
            session.setAttribute("admin", admin);
            session.setAttribute("adminId", admin.getAdminId());
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("fullname", admin.getFullName());
            session.setAttribute("role", admin.getRole());
            session.setMaxInactiveInterval(30 * 60); // Session expires after 30 minutes

            // Redirect to admin dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Authentication failed, redirect back to login page with error
            request.setAttribute("error", "Incorrect username or password!");
            request.getRequestDispatcher("/hospitalink/login.jsp").forward(request, response);
        }
    }

    private Admin authenticateAdmin(String username, String password) {
        for (Admin admin : adminService.getAllAdmins()) {
            if (admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check for existing session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            // Redirect authenticated admin to dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Redirect to login page
            request.getRequestDispatcher("/hospitalink/login.jsp").forward(request, response);
        }
    }
}