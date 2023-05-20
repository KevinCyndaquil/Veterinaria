package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.PanelController;
import application.views.Menu;
import application.controllers.abstracts.ViewerController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class C_Menu extends ViewerController<Menu> {
    private final List<PanelController<?>> panels;
    private C_Compras c_compras;

    public C_Menu() {
        super(new Menu());

        panels = List.of(
                new C_MenuCitas());
    }

    public boolean validarPanel(@NotNull Component component) {
        Component actualComponent = view.switchPanel.getComponent(0);

        //verificamos que se está añadiendo el mismo component, entonces añadimos un fondo
        if (!component.getClass().isInstance(actualComponent)) return false;

        view.switchPanel.removeAll();
        view.switchPanel.add(view.fondo);

        //repintamos lo necesario
        view.repaint();
        view.switchPanel.repaint();
        component.repaint();


        return true;
    }

    public void switchPanel(@NotNull Component component) {
        if (validarPanel(component)) return;

        //removemos todo
        view.switchPanel.removeAll();

        //lo redimensionamos
        component.setBounds(0, 0, view.switchPanel.getWidth(), view.switchPanel.getHeight());
        //lo añadimos
        view.switchPanel.add(component);

        //repintamos lo necesario
        view.repaint();
        view.switchPanel.repaint();
        component.repaint();
    }

    @Override
    public void initEvents() {
        view.barLeft.btncompras.addActionListener(e ->  {
                if(c_compras == null) {
                    c_compras = new C_Compras();
                }
                view = null;
                c_compras.showView();
        });

        view.barLeft.btncitas
                .addActionListener(e -> switchPanel(panels.get(0).initPanel()));

        view.barLeft.btnreportes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageDialog.successMessage(view, "Boton 3");
            }
        });

        view.barLeft.btnadmins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageDialog.successMessage(view, "Boton 4");
            }
        });
    }

    public static void main(String[] args) {
        new C_Menu();
    }
}
