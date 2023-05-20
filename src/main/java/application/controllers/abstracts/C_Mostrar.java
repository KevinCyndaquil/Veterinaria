package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;

import javax.swing.*;

public abstract class C_Mostrar <T extends JFrame, M extends Entity> extends ViewerController<T> {
    protected M m;

    public C_Mostrar(T view, M m) {
        super(view);
        view.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.m = m;
    }

    public abstract void mostrar();
}
