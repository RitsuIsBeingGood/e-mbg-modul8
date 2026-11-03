package com.mycompany.mbg.modul8.repository;

import com.mycompany.mbg.modul8.model.Shipment;
import java.sql.SQLException;
import java.util.List;

public interface ShipmentRepository {

    List<Shipment> findAll() throws SQLException;

    void save(Shipment shipment) throws SQLException;

    boolean updateStatus(int id, String status) throws SQLException;
}
