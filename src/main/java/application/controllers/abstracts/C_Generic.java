package application.controllers.abstracts;

import lombok.NonNull;

import javax.swing.*;

import java.lang.reflect.InvocationTargetException;

public abstract class C_Generic <T extends JFrame>{

    protected T view;

    public C_Generic(@NonNull Class<T> clazz) {
            //instanciar la vista
            try {
                view = clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
    }
    public abstract void showView();

}
