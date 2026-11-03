package com.mycompany.mbg.modul8.service;

import com.mycompany.mbg.modul8.model.Shipment;
import java.sql.SQLException;
import java.util.List;

public interface ShipmentService {

    List<Shipment> getAllShipments() throws SQLException;

    void createShipment(Shipment shipment) throws SQLException;

    void sendShipment(int id) throws SQLException;

    void confirmReceipt(int id) throws SQLException;
}
