package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.views.terminadas.ShowerTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class C_Mostrar <M extends Entity> extends ViewerController<ShowerTable> {
    protected M m;

    public C_Mostrar(Object[] columns, M m) {
        super(new ShowerTable(columns));

        view.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.m = m;
    }

    public abstract void mostrar();
    public abstract void obtener(ActionEvent event);
    public abstract void lanzarAlta(ActionEvent event);

    @Override
    public void initEvents() {
        view.btnSeleccionar.addActionListener(this::obtener);
        view.btnRegistrar.addActionListener(this::lanzarAlta);
    }
}
