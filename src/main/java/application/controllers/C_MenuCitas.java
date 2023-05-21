package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.EventsController;
import application.controllers.abstracts.PanelController;
import application.controllers.terminados.C_HistorialMascota;
import application.views.panels.MenuCitaPanel;

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

        panel.btnReportes.addActionListener(e -> {
            new C_HistorialMascota();
        });
    }
}
