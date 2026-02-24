package com.hospitalink.servlet;

import com.hospitalink.model.Report;
import com.hospitalink.model.Patient;
import com.hospitalink.model.Doctor;
import com.hospitalink.service.ReportService;
import com.hospitalink.service.PatientService;
import com.hospitalink.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReportService reportService;
    private PatientService patientService;
    private DoctorService doctorService;

    @Override
    public void init() throws ServletException {
        reportService = new ReportService();
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
                // List all reports
                request.setAttribute("reports", reportService.getAllReports());
                request.getRequestDispatcher("hospitalink/report/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                // Show create form
                List<Patient> patients = patientService.getAllPatients();
                List<Doctor> doctors = doctorService.getAllDoctors();
                request.setAttribute("patients", patients);
                request.setAttribute("doctors", doctors);
                request.getRequestDispatcher("hospitalink/report/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Report report = reportService.getReportFromMainTable(id);
                if (report != null) {
                    List<Patient> patients = patientService.getAllPatients();
                    List<Doctor> doctors = doctorService.getAllDoctors();
                    request.setAttribute("patients", patients);
                    request.setAttribute("doctors", doctors);
                    request.setAttribute("report", report);
                    request.getRequestDispatcher("hospitalink/report/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Report not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                reportService.deleteReport(id);
                response.sendRedirect(request.getContextPath() + "/report");

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
                String reportTitle = request.getParameter("reportTitle");
                String reportDetails = request.getParameter("reportDetails");

                Report report = new Report();
                report.setPatientId(patientId);
                report.setDoctorId(doctorId);
                report.setReportTitle(reportTitle);
                report.setReportDetails(reportDetails);

                if (reportService.createReport(report)) {
                    response.sendRedirect(request.getContextPath() + "/report");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create report.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int patientId = Integer.parseInt(request.getParameter("patientId"));
                int doctorId = Integer.parseInt(request.getParameter("doctorId"));
                String reportTitle = request.getParameter("reportTitle");
                String reportDetails = request.getParameter("reportDetails");

                Report report = new Report();
                report.setReportId(id);
                report.setPatientId(patientId);
                report.setDoctorId(doctorId);
                report.setReportTitle(reportTitle);
                report.setReportDetails(reportDetails);

                if (reportService.updateReport(report)) {
                    response.sendRedirect(request.getContextPath() + "/report");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update report.");
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