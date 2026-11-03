package com.mycompany.mbg.modul8;

import com.mycompany.mbg.modul8.controller.ShipmentController;
import com.mycompany.mbg.modul8.repository.JdbcShipmentRepository;
import com.mycompany.mbg.modul8.service.ShipmentService;
import com.mycompany.mbg.modul8.service.ShipmentServiceImpl;
import com.mycompany.mbg.modul8.view.ShipmentView;

import javax.swing.SwingUtilities;

public class MbgModul8 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JdbcShipmentRepository repository
                    = new JdbcShipmentRepository();

            ShipmentService service
                    = new ShipmentServiceImpl(repository);

            ShipmentView view = new ShipmentView();

            new ShipmentController(view, service);

            view.setVisible(true);
        });
    }
}
