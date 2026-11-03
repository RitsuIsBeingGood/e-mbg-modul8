package com.mycompany.mbg.modul8.controller;

import com.mycompany.mbg.modul8.model.Shipment;
import com.mycompany.mbg.modul8.service.ShipmentService;
import com.mycompany.mbg.modul8.view.ShipmentView;

import java.sql.SQLException;
import java.time.LocalDate;

public class ShipmentController {

    private final ShipmentView view;
    private final ShipmentService service;

    public ShipmentController(ShipmentView view, ShipmentService service) {
        this.view = view;
        this.service = service;

        initListeners();
        loadShipments();
    }

    private void initListeners() {
        view.getBtnTambah().addActionListener(e -> createShipment());

        view.getBtnRefresh().addActionListener(e -> loadShipments());

        view.getBtnKirim().addActionListener(e -> sendShipment());

        view.getBtnTerima().addActionListener(e -> confirmReceipt());
    }

    private void loadShipments() {
        try {
            view.displayShipments(service.getAllShipments());
        } catch (SQLException e) {
            view.showError("Gagal mengambil data: " + e.getMessage());
        }
    }

    private void createShipment() {
        try {
            String vendor = view.getVendorName();
            String school = view.getSchoolName();
            int porsi = Integer.parseInt(view.getJumlahPorsi());
            LocalDate tanggal = LocalDate.parse(view.getTanggal());

            Shipment shipment = new Shipment(
                    vendor,
                    school,
                    porsi,
                    tanggal,
                    "DIPROSES"
            );

            service.createShipment(shipment);

            view.showMessage("Pengiriman berhasil ditambahkan!");
            view.clearForm();
            loadShipments();

        } catch (NumberFormatException e) {
            view.showError("Jumlah porsi harus berupa angka.");
        } catch (java.time.format.DateTimeParseException e) {
            view.showError("Format tanggal harus YYYY-MM-DD.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        } catch (SQLException e) {
            view.showError("Gagal menyimpan data: " + e.getMessage());
        }
    }

    private void sendShipment() {
        int id = view.getSelectedShipmentId();

        if (id == -1) {
            view.showMessage("Pilih data pengiriman terlebih dahulu.");
            return;
        }

        try {
            service.sendShipment(id);
            view.showMessage("Status pengiriman berhasil diubah menjadi DIKIRIM.");
            loadShipments();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        } catch (SQLException e) {
            view.showError("Gagal mengubah status: " + e.getMessage());
        }
    }

    private void confirmReceipt() {
        int id = view.getSelectedShipmentId();

        if (id == -1) {
            view.showMessage("Pilih data pengiriman terlebih dahulu.");
            return;
        }

        try {
            service.confirmReceipt(id);
            view.showMessage("Pengiriman berhasil dikonfirmasi DITERIMA.");
            loadShipments();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        } catch (SQLException e) {
            view.showError("Gagal mengubah status: " + e.getMessage());
        }
    }
}
