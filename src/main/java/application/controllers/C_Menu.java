package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class C_Menu extends C_Generic<Menu> {
    private C_Compras c_compras;
    public C_Menu() {
        super(Menu.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        events();
    }

    public void events(){
        view.barLeft.button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(c_compras == null) {
                    c_compras = new C_Compras();
                }
                c_compras.showView();
            }
        });

        view.barLeft.button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageDialog.successMessage(view, "Boton 2");
            }
        });

        view.barLeft.button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageDialog.successMessage(view, "Boton 3");
            }
        });

        view.barLeft.button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageDialog.successMessage(view, "Boton 4");
            }
        });
    }


}
