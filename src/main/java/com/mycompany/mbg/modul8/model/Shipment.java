package com.mycompany.mbg.modul8.model;

import java.time.LocalDate;

public class Shipment {

    private int id;
    private String vendorName;
    private String schoolName;
    private int jumlahPorsi;
    private LocalDate tanggal;
    private String status;

    public Shipment(int id, String vendorName, String schoolName,
            int jumlahPorsi, LocalDate tanggal, String status) {
        this.id = id;
        this.vendorName = vendorName;
        this.schoolName = schoolName;
        this.jumlahPorsi = jumlahPorsi;
        this.tanggal = tanggal;
        this.status = status;
    }

    public Shipment(String vendorName, String schoolName,
            int jumlahPorsi, LocalDate tanggal, String status) {
        this(0, vendorName, schoolName, jumlahPorsi, tanggal, status);
    }

    public int getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getJumlahPorsi() {
        return jumlahPorsi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
