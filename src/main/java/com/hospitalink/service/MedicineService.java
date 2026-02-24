package com.hospitalink.service;

import com.hospitalink.model.Medicine;
import com.hospitalink.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {

    // Create Medicine
    public boolean createMedicine(Medicine medicine) {
        String query = "INSERT INTO medicines (name, description, stock_quantity, expiry_date, created_at) " +
                       "VALUES (?, ?, ?, ?, NOW())";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDescription());
            stmt.setInt(3, medicine.getStockQuantity());
            stmt.setString(4, medicine.getExpiryDate());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Single Medicine by ID
    public Medicine getMedicine(int id) {
        String query = "SELECT * FROM medicines WHERE medicine_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(rs.getInt("medicine_id"));
                medicine.setName(rs.getString("name"));
                medicine.setDescription(rs.getString("description"));
                medicine.setStockQuantity(rs.getInt("stock_quantity"));
                medicine.setExpiryDate(rs.getString("expiry_date"));
                medicine.setCreatedAt(rs.getString("created_at"));
                return medicine;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Medicines
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String query = "SELECT * FROM medicines ORDER BY medicine_id";

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(rs.getInt("medicine_id"));
                medicine.setName(rs.getString("name"));
                medicine.setDescription(rs.getString("description"));
                medicine.setStockQuantity(rs.getInt("stock_quantity"));
                medicine.setExpiryDate(rs.getString("expiry_date"));
                medicine.setCreatedAt(rs.getString("created_at"));
                medicines.add(medicine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    // Update Medicine
    public boolean updateMedicine(Medicine medicine) {
        String query = "UPDATE medicines SET name = ?, description = ?, stock_quantity = ?, expiry_date = ? WHERE medicine_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDescription());
            stmt.setInt(3, medicine.getStockQuantity());
            stmt.setString(4, medicine.getExpiryDate());
            stmt.setInt(5, medicine.getMedicineId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Medicine
    public boolean deleteMedicine(int id) {
        String query = "DELETE FROM medicines WHERE medicine_id = ?";

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