package com.mycompany.mbg.modul8;

import com.mycompany.mbg.modul8.view.ShipmentView;
import javax.swing.SwingUtilities;

public class MbgModul8 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ShipmentView view = new ShipmentView();
            view.setVisible(true);
        });

    }
}
