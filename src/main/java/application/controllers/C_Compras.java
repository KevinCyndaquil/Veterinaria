package application.controllers;

import application.controllers.abstracts.C_Alta;
import application.controllers.abstracts.C_Generic;
import application.controllers.abstracts.PanelController;
import application.controllers.abstracts.ViewerController;
import application.models.finanzas.Tickets;
import application.views.CompraView;
import application.views.panels.AltaCompra;

public class C_Compras extends PanelController<AltaCompra> {
    public C_Compras() {
        super(new AltaCompra());
    }

    @Override
    public void initEvents() {

    }
}
