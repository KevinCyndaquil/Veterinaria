package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class C_Alta<T extends JFrame, M extends Entity> extends ViewerController<T>{

    public C_Alta(T view) {
        super(view);
    }

    public abstract boolean pasar(@NotNull Object obj);
    public abstract M alta();
}
