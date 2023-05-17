package application.controllers;

import application.controllers.abstracts.C_Generic;
import application.views.CompraView;

public class C_Compras extends C_Generic<CompraView> {
    public C_Compras() {
        super(CompraView.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
    }
}
