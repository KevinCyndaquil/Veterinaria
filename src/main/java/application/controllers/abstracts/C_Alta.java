package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;

import javax.swing.*;

public abstract class C_Alta <F extends JFrame, M extends Entity> extends ViewerController<F> {

    public C_Alta(F view) {
        super(view);
    }

    public abstract M alta();
}
