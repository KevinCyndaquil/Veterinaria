package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.PanelController;
import application.views.terminadas.Menu;
import application.controllers.abstracts.ViewerController;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class C_Menu extends ViewerController<Menu> {
    private List<PanelController<?>> panels;
    private C_AltaCompra c_Alta_compras;

    public C_Menu() {
        super(new Menu());
    }

    public boolean validarPanel(Component component) {
        Component actualComponent = view.switchPanel.getComponent(0);

        //verificamos que se está añadiendo el mismo component, entonces añadimos un fondo
        if (component != null)
            if (!component.getClass().isInstance(actualComponent)) return false;

        view.switchPanel.removeAll();
        view.switchPanel.add(view.fondo);

        //repintamos lo necesario
        view.repaint();
        view.switchPanel.repaint();

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

        view.barLeft.btncompras.addActionListener(e -> {
            try {
                switchPanel((new C_AltaCompra()).initPanel());
            } catch (TimeoutException ex) {
                MessageDialog.outMessage(
                        view,
                        "No se puede realizar una compra fuera de la hora de servicio");
            }
        });

        view.barLeft.btncitas.addActionListener(e -> switchPanel(Objects.requireNonNull(panels.get(0).initPanel())));

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
