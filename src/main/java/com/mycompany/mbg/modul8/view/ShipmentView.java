package com.mycompany.mbg.modul8.view;

import com.mycompany.mbg.modul8.model.Shipment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShipmentView extends JFrame {

    private JTextField txtVendor;
    private JTextField txtSchool;
    private JTextField txtPorsi;
    private JTextField txtTanggal;

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnTambah;
    private JButton btnRefresh;
    private JButton btnKirim;
    private JButton btnTerima;

    public ShipmentView() {
        setTitle("e-EMBG | Manajemen Pengiriman");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 172, 240));
        header.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JLabel title = new JLabel("e-EMBG | Manajemen Pengiriman");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = new JPanel(new GridLayout(2, 4, 10, 8));
        form.setBorder(BorderFactory.createTitledBorder("Tambah Pengiriman"));

        txtVendor = new JTextField();
        txtSchool = new JTextField();
        txtPorsi = new JTextField();
        txtTanggal = new JTextField();

        form.add(new JLabel("Nama Vendor"));
        form.add(new JLabel("Nama Sekolah"));
        form.add(new JLabel("Jumlah Porsi"));
        form.add(new JLabel("Tanggal (YYYY-MM-DD)"));

        form.add(txtVendor);
        form.add(txtSchool);
        form.add(txtPorsi);
        form.add(txtTanggal);

        JPanel topPanel = new JPanel(new BorderLayout(0, 10));
        topPanel.add(form, BorderLayout.CENTER);

        btnTambah = new JButton("Tambah Pengiriman");
        topPanel.add(btnTambah, BorderLayout.SOUTH);

        String[] columns = {
            "ID", "Vendor", "Sekolah", "Jumlah Porsi", "Tanggal", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Daftar Pengiriman")
        );

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnRefresh = new JButton("Refresh");
        btnKirim = new JButton("Kirim");
        btnTerima = new JButton("Konfirmasi Diterima");

        actions.add(btnRefresh);
        actions.add(btnKirim);
        actions.add(btnTerima);

        content.add(topPanel, BorderLayout.NORTH);
        content.add(scrollPane, BorderLayout.CENTER);
        content.add(actions, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
    }

    public String getVendorName() {
        return txtVendor.getText().trim();
    }

    public String getSchoolName() {
        return txtSchool.getText().trim();
    }

    public String getJumlahPorsi() {
        return txtPorsi.getText().trim();
    }

    public String getTanggal() {
        return txtTanggal.getText().trim();
    }

    public int getSelectedShipmentId() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            return -1;
        }

        return Integer.parseInt(
                tableModel.getValueAt(selectedRow, 0).toString()
        );
    }

    public void displayShipments(List<Shipment> shipments) {
        tableModel.setRowCount(0);

        for (Shipment shipment : shipments) {
            tableModel.addRow(new Object[]{
                shipment.getId(),
                shipment.getVendorName(),
                shipment.getSchoolName(),
                shipment.getJumlahPorsi(),
                shipment.getTanggal(),
                shipment.getStatus()
            });
        }
    }

    public void clearForm() {
        txtVendor.setText("");
        txtSchool.setText("");
        txtPorsi.setText("");
        txtTanggal.setText("");
    }

    public JButton getBtnTambah() {
        return btnTambah;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JButton getBtnKirim() {
        return btnKirim;
    }

    public JButton getBtnTerima() {
        return btnTerima;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Terjadi Kesalahan",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
