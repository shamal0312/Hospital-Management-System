package com.hospitalink.service;

import com.hospitalink.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardService {

    public int getTotalPatients() {
        return getCount("SELECT COUNT(*) FROM patients");
    }

    public int getTotalDoctors() {
        return getCount("SELECT COUNT(*) FROM doctors");
    }

    public int getTotalAppointments() {
        return getCount("SELECT COUNT(*) FROM appointments");
    }

    public int getTotalMedicines() {
        return getCount("SELECT COUNT(*) FROM medicines");
    }

    public int getTotalReports() {
        return getCount("SELECT COUNT(*) FROM medical_reports");
    }

    public int getTotalAdmins() {
        return getCount("SELECT COUNT(*) FROM admins");
    }

    private int getCount(String query) {
        int count = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}