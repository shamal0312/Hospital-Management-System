package com.hospitalink.servlet;

import com.hospitalink.model.Appointment;
import com.hospitalink.model.Doctor;
import com.hospitalink.model.Patient;
import com.hospitalink.service.AppointmentService;
import com.hospitalink.service.DoctorService;
import com.hospitalink.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AppointmentService appointmentService;
    private PatientService patientService;
    private DoctorService doctorService;

    @Override
    public void init() throws ServletException {
        appointmentService = new AppointmentService();
        patientService = new PatientService();
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
                // List all appointments
                request.setAttribute("appointments", appointmentService.getAllAppointments());
                request.getRequestDispatcher("hospitalink/appointment/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                // Load patients and doctors for create form
                List<Patient> patients = patientService.getAllPatients();
                List<Doctor> doctors = doctorService.getAllDoctors();
                request.setAttribute("patients", patients);
                request.setAttribute("doctors", doctors);
                request.getRequestDispatcher("hospitalink/appointment/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Appointment appointment = appointmentService.getAppointment(id);

                if (appointment != null) {
                    // Now load patientId and doctorId properly
                    Appointment realData = appointmentService.getAppointmentFromMainTable(id); 
                    if (realData != null) {
                        appointment.setPatientId(realData.getPatientId());
                        appointment.setDoctorId(realData.getDoctorId());
                    }

                    List<Patient> patients = patientService.getAllPatients();
                    List<Doctor> doctors = doctorService.getAllDoctors();

                    request.setAttribute("appointment", appointment);
                    request.setAttribute("patients", patients);
                    request.setAttribute("doctors", doctors);

                    request.getRequestDispatcher("hospitalink/appointment/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Appointment not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                appointmentService.deleteAppointment(id);
                response.sendRedirect(request.getContextPath() + "/appointment");

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
                int patientId = Integer.parseInt(request.getParameter("patientId"));
                int doctorId = Integer.parseInt(request.getParameter("doctorId"));
                String appointmentDate = request.getParameter("appointmentDate");
                String status = request.getParameter("status");

                Appointment appointment = new Appointment();
                appointment.setPatientId(patientId);
                appointment.setDoctorId(doctorId);
                appointment.setAppointmentDate(appointmentDate);
                appointment.setStatus(status);

                if (appointmentService.createAppointment(appointment)) {
                    response.sendRedirect(request.getContextPath() + "/appointment");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create appointment.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int patientId = Integer.parseInt(request.getParameter("patientId"));
                int doctorId = Integer.parseInt(request.getParameter("doctorId"));
                String appointmentDate = request.getParameter("appointmentDate");
                String status = request.getParameter("status");

                Appointment appointment = new Appointment();
                appointment.setAppointmentId(id);
                appointment.setPatientId(patientId);
                appointment.setDoctorId(doctorId);
                appointment.setAppointmentDate(appointmentDate);
                appointment.setStatus(status);

                if (appointmentService.updateAppointment(appointment)) {
                    response.sendRedirect(request.getContextPath() + "/appointment");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update appointment.");
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