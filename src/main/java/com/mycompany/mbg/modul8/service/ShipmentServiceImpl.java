package com.mycompany.mbg.modul8.service;

import com.mycompany.mbg.modul8.model.Shipment;
import com.mycompany.mbg.modul8.repository.ShipmentRepository;

import java.sql.SQLException;
import java.util.List;

public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public List<Shipment> getAllShipments() throws SQLException {
        return shipmentRepository.findAll();
    }

    @Override
    public void createShipment(Shipment shipment) throws SQLException {
        if (shipment == null) {
            throw new IllegalArgumentException("Data pengiriman tidak boleh kosong.");
        }

        if (shipment.getVendorName() == null || shipment.getVendorName().isBlank()
                || shipment.getSchoolName() == null || shipment.getSchoolName().isBlank()) {
            throw new IllegalArgumentException("Nama vendor dan sekolah wajib diisi.");
        }

        if (shipment.getJumlahPorsi() <= 0) {
            throw new IllegalArgumentException("Jumlah porsi harus lebih dari 0.");
        }

        if (shipment.getTanggal() == null) {
            throw new IllegalArgumentException("Tanggal pengiriman wajib diisi.");
        }

        Shipment newShipment = new Shipment(
                shipment.getVendorName(),
                shipment.getSchoolName(),
                shipment.getJumlahPorsi(),
                shipment.getTanggal(),
                "DIPROSES"
        );

        shipmentRepository.save(newShipment);
    }

    @Override
    public void sendShipment(int id) throws SQLException {
        updateStatus(id, "DIPROSES", "DIKIRIM");
    }

    @Override
    public void confirmReceipt(int id) throws SQLException {
        updateStatus(id, "DIKIRIM", "DITERIMA");
    }

    private void updateStatus(int id, String currentStatus, String newStatus)
            throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID pengiriman tidak valid.");
        }

        Shipment shipment = shipmentRepository.findAll()
                .stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                "Data pengiriman tidak ditemukan."
        ));

        if (!shipment.getStatus().equals(currentStatus)) {
            throw new IllegalStateException(
                    "Status harus " + currentStatus + " untuk melakukan perubahan."
            );
        }

        shipmentRepository.updateStatus(id, newStatus);
    }
}
