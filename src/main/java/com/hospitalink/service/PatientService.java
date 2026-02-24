package com.hospitalink.service;

import com.hospitalink.model.Patient;
import com.hospitalink.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    // Create Patient
    public boolean createPatient(Patient patient) {
        String query = "INSERT INTO patients (full_name, gender, birth_date, contact_number, address, created_at) " +
                       "VALUES (?, ?, ?, ?, ?, NOW())";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, patient.getFullName());
            stmt.setString(2, patient.getGender());
            stmt.setString(3, patient.getBirthDate());
            stmt.setString(4, patient.getContactNumber());
            stmt.setString(5, patient.getAddress());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Single Patient by ID
    public Patient getPatient(int id) {
        String query = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setFullName(rs.getString("full_name"));
                patient.setGender(rs.getString("gender"));
                patient.setBirthDate(rs.getString("birth_date"));
                patient.setContactNumber(rs.getString("contact_number"));
                patient.setAddress(rs.getString("address"));
                patient.setCreatedAt(rs.getString("created_at"));
                return patient;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients ORDER BY patient_id";

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setFullName(rs.getString("full_name"));
                patient.setGender(rs.getString("gender"));
                patient.setBirthDate(rs.getString("birth_date"));
                patient.setContactNumber(rs.getString("contact_number"));
                patient.setAddress(rs.getString("address"));
                patient.setCreatedAt(rs.getString("created_at"));
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    // Update Patient
    public boolean updatePatient(Patient patient) {
        String query = "UPDATE patients SET full_name = ?, gender = ?, birth_date = ?, contact_number = ?, address = ? WHERE patient_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, patient.getFullName());
            stmt.setString(2, patient.getGender());
            stmt.setString(3, patient.getBirthDate());
            stmt.setString(4, patient.getContactNumber());
            stmt.setString(5, patient.getAddress());
            stmt.setInt(6, patient.getPatientId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Patient
    public boolean deletePatient(int id) {
        String query = "DELETE FROM patients WHERE patient_id = ?";

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