package application.controllers;

import application.controllers.abstracts.C_Generic;
import application.views.MenuCitas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_MenuCitas extends C_Generic<MenuCitas> implements ActionListener {
    public C_MenuCitas() {
        super(MenuCitas.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.btnRegresar.addActionListener(this);
        view.btnaltacitas.addActionListener(this);
        view.btnReportes.addActionListener(this);
        view.btnModificaciones.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnRegresar){
            C_Menu c_menu = new C_Menu();
            view.dispose();
            c_menu.showView();

        }

        if(e.getSource() == view.btnaltacitas){
            C_AltaCita c_altaCita = new C_AltaCita();
            view.dispose();
            c_altaCita.showView();
        }

        if(e.getSource() == view.btnReportes){

        }

        if(e.getSource() == view.btnModificaciones){

        }
    }
}
