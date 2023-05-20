package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.controllers.abstracts.EventsController;
import application.controllers.abstracts.PanelController;
import application.views.MenuCitas;
import application.views.panels.MenuCitaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class C_MenuCitas extends PanelController<MenuCitaPanel> implements EventsController {

    public C_MenuCitas() {
        super(new MenuCitaPanel());
    }

    @Override
    public void initEvents() {
        panel.btnRegresar.addActionListener(e -> {
            MessageDialog.successMessage(null, "Todo bien");
        });

        panel.btnaltacitas.addActionListener(e -> {
            new C_AltaCita();
        });

        panel.btnModificaciones.addActionListener(e -> {});

        panel.btnReportes.addActionListener(e -> {});
    }
}
