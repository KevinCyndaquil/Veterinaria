package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class C_Mostrar <T extends JFrame, M extends Entity> extends C_Generic<T> {
    protected M m;
    public C_Mostrar(@NotNull Class<T> clazz, M m) {
        super(clazz);

        this.m = m;
    }

    public abstract void mostrar();
}
