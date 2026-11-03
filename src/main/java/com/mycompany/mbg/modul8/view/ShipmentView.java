package com.mycompany.mbg.modul8.view;

import javax.swing.*;
import java.awt.*;

public class ShipmentView extends JFrame {

    public ShipmentView() {
        setTitle("e-EMBG - Manajemen Pengiriman");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(45, 172, 240));
        headerPanel.setPreferredSize(new Dimension(1000, 70));

        JLabel titleLabel = new JLabel(
                "  e-EMBG | Manajemen Pengiriman"
        );

        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel(
                "Selamat Datang di Sistem Manajemen Pengiriman",
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
