package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.Menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class C_Menu extends C_Generic<Menu> {
    private C_Compras c_compras;
    private C_MenuCitas c_menuCitas;
    public C_Menu() {
        super(Menu.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        events();
    }

    public void events(){
        view.barLeft.btncompras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(c_compras == null) {
                    c_compras = new C_Compras();
                }
                view = null;
                c_compras.showView();
            }
        });

        view.barLeft.btncitas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(c_menuCitas == null) {
                    c_menuCitas = new C_MenuCitas();
                }
                view.dispose();
                c_menuCitas.showView();
            }
        });

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


}
