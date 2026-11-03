package com.mycompany.mbg.modul8.repository;

import com.mycompany.mbg.modul8.config.DatabaseConnection;
import com.mycompany.mbg.modul8.model.Shipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcShipmentRepository implements ShipmentRepository {

    @Override
    public List<Shipment> findAll() throws SQLException {
        List<Shipment> shipments = new ArrayList<>();

        String sql = "SELECT * FROM shipments ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                Shipment shipment = new Shipment(
                        result.getInt("id"),
                        result.getString("vendor_name"),
                        result.getString("school_name"),
                        result.getInt("jumlah_porsi"),
                        result.getDate("tanggal").toLocalDate(),
                        result.getString("status")
                );

                shipments.add(shipment);
            }
        }

        return shipments;
    }

    @Override
    public void save(Shipment shipment) throws SQLException {
        String sql = "INSERT INTO shipments "
                + "(vendor_name, school_name, jumlah_porsi, tanggal, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, shipment.getVendorName());
            statement.setString(2, shipment.getSchoolName());
            statement.setInt(3, shipment.getJumlahPorsi());
            statement.setDate(4, java.sql.Date.valueOf(shipment.getTanggal()));
            statement.setString(5, shipment.getStatus());

            statement.executeUpdate();
        }
    }

    @Override
    public boolean updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE shipments SET status = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;
        }
    }
}
