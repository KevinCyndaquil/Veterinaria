package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class C_Alta<T extends JFrame, M extends Entity> extends C_Generic<T>{
    public C_Alta(@NotNull Class<T> clazz) {
        super(clazz);
    }

    public abstract M alta();
}
