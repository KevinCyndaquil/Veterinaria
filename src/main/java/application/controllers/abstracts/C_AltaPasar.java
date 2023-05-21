package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class C_AltaPasar<T extends JFrame, M extends Entity> extends C_Alta<T, M> {

    public C_AltaPasar(T view) {
        super(view);
    }

    public abstract boolean pasar(@NotNull Object objToPass);
}
