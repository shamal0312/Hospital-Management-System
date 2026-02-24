package com.hospitalink.service;

import com.hospitalink.model.Doctor;
import com.hospitalink.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    // Create Doctor
    public boolean createDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (full_name, specialty, contact_number, email, created_at) " +
                       "VALUES (?, ?, ?, ?, NOW())";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, doctor.getFullName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setString(3, doctor.getContactNumber());
            stmt.setString(4, doctor.getEmail());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Single Doctor by ID
    public Doctor getDoctor(int id) {
        String query = "SELECT * FROM doctors WHERE doctor_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setContactNumber(rs.getString("contact_number"));
                doctor.setEmail(rs.getString("email"));
                doctor.setCreatedAt(rs.getString("created_at"));
                return doctor;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Doctors
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors ORDER BY doctor_id";

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setContactNumber(rs.getString("contact_number"));
                doctor.setEmail(rs.getString("email"));
                doctor.setCreatedAt(rs.getString("created_at"));
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    // Update Doctor
    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET full_name = ?, specialty = ?, contact_number = ?, email = ? WHERE doctor_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, doctor.getFullName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setString(3, doctor.getContactNumber());
            stmt.setString(4, doctor.getEmail());
            stmt.setInt(5, doctor.getDoctorId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Doctor
    public boolean deleteDoctor(int id) {
        String query = "DELETE FROM doctors WHERE doctor_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}