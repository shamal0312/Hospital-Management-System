package com.hospitalink.servlet;

import com.hospitalink.model.Patient;
import com.hospitalink.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/patient")
public class PatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PatientService patientService;

    @Override
    public void init() throws ServletException {
        patientService = new PatientService();
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
                // List all patients
                request.setAttribute("patients", patientService.getAllPatients());
                request.getRequestDispatcher("hospitalink/patient/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                // Show create form
                request.getRequestDispatcher("hospitalink/patient/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Patient patient = patientService.getPatient(id);
                if (patient != null) {
                    request.setAttribute("patient", patient);
                    request.getRequestDispatcher("hospitalink/patient/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Patient not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                patientService.deletePatient(id);
                response.sendRedirect(request.getContextPath() + "/patient");

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
                String gender = request.getParameter("gender");
                String birthDate = request.getParameter("birthDate");
                String contactNumber = request.getParameter("contactNumber");
                String address = request.getParameter("address");

                Patient patient = new Patient();
                patient.setFullName(fullName);
                patient.setGender(gender);
                patient.setBirthDate(birthDate);
                patient.setContactNumber(contactNumber);
                patient.setAddress(address);

                if (patientService.createPatient(patient)) {
                    response.sendRedirect(request.getContextPath() + "/patient");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create patient.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String fullName = request.getParameter("fullName");
                String gender = request.getParameter("gender");
                String birthDate = request.getParameter("birthDate");
                String contactNumber = request.getParameter("contactNumber");
                String address = request.getParameter("address");

                Patient patient = new Patient();
                patient.setPatientId(id);
                patient.setFullName(fullName);
                patient.setGender(gender);
                patient.setBirthDate(birthDate);
                patient.setContactNumber(contactNumber);
                patient.setAddress(address);

                if (patientService.updatePatient(patient)) {
                    response.sendRedirect(request.getContextPath() + "/patient");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update patient.");
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