package com.hospitalink.servlet;

import com.hospitalink.model.Doctor;
import com.hospitalink.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DoctorService doctorService;

    @Override
    public void init() throws ServletException {
        doctorService = new DoctorService();
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
                // List all doctors
                request.setAttribute("doctors", doctorService.getAllDoctors());
                request.getRequestDispatcher("hospitalink/doctor/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                // Show create form
                request.getRequestDispatcher("hospitalink/doctor/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Doctor doctor = doctorService.getDoctor(id);
                if (doctor != null) {
                    request.setAttribute("doctor", doctor);
                    request.getRequestDispatcher("hospitalink/doctor/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                doctorService.deleteDoctor(id);
                response.sendRedirect(request.getContextPath() + "/doctor");

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
                String fullName = request.getParameter("fullName");
                String specialty = request.getParameter("specialty");
                String contactNumber = request.getParameter("contactNumber");
                String email = request.getParameter("email");

                Doctor doctor = new Doctor();
                doctor.setFullName(fullName);
                doctor.setSpecialty(specialty);
                doctor.setContactNumber(contactNumber);
                doctor.setEmail(email);

                if (doctorService.createDoctor(doctor)) {
                    response.sendRedirect(request.getContextPath() + "/doctor");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create doctor.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String fullName = request.getParameter("fullName");
                String specialty = request.getParameter("specialty");
                String contactNumber = request.getParameter("contactNumber");
                String email = request.getParameter("email");

                Doctor doctor = new Doctor();
                doctor.setDoctorId(id);
                doctor.setFullName(fullName);
                doctor.setSpecialty(specialty);
                doctor.setContactNumber(contactNumber);
                doctor.setEmail(email);

                if (doctorService.updateDoctor(doctor)) {
                    response.sendRedirect(request.getContextPath() + "/doctor");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update doctor.");
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