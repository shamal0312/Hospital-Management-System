package com.hospitalink.service;

import com.hospitalink.model.Report;
import com.hospitalink.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    // Get all reports (using the view)
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM medical_reports_view ORDER BY report_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Report report = new Report();
                report.setReportId(rs.getInt("report_id"));
                report.setPatientName(rs.getString("patient_name"));
                report.setDoctorName(rs.getString("doctor_name"));
                report.setReportTitle(rs.getString("report_title"));
                report.setReportDetails(rs.getString("report_details"));
                report.setCreatedAt(rs.getString("created_at"));
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reports;
    }

    // Get a single report by ID (using the view)
    public Report getReportById(int id) {
        String sql = "SELECT * FROM medical_reports_view WHERE report_id = ?";
        Report report = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report = new Report();
                    report.setReportId(rs.getInt("report_id"));
                    report.setPatientName(rs.getString("patient_name"));
                    report.setDoctorName(rs.getString("doctor_name"));
                    report.setReportTitle(rs.getString("report_title"));
                    report.setReportDetails(rs.getString("report_details"));
                    report.setCreatedAt(rs.getString("created_at"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }

    // Special: Get report from main table (medical_reports) (for update purpose)
    public Report getReportFromMainTable(int id) {
        String sql = "SELECT * FROM medical_reports WHERE report_id = ?";
        Report report = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report = new Report();
                    report.setReportId(rs.getInt("report_id"));
                    report.setPatientId(rs.getInt("patient_id"));
                    report.setDoctorId(rs.getInt("doctor_id"));
                    report.setReportTitle(rs.getString("report_title"));
                    report.setReportDetails(rs.getString("report_details"));
                    report.setCreatedAt(rs.getString("created_at"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }

    // Create a new report (insert into main table)
    public boolean createReport(Report report) {
        String sql = "INSERT INTO medical_reports (patient_id, doctor_id, report_title, report_details) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, report.getPatientId());
            stmt.setInt(2, report.getDoctorId());
            stmt.setString(3, report.getReportTitle());
            stmt.setString(4, report.getReportDetails());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing report (update main table)
    public boolean updateReport(Report report) {
        String sql = "UPDATE medical_reports SET patient_id = ?, doctor_id = ?, report_title = ?, report_details = ? WHERE report_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, report.getPatientId());
            stmt.setInt(2, report.getDoctorId());
            stmt.setString(3, report.getReportTitle());
            stmt.setString(4, report.getReportDetails());
            stmt.setInt(5, report.getReportId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a report (from main table)
    public boolean deleteReport(int id) {
        String sql = "DELETE FROM medical_reports WHERE report_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}