package application.controllers;

import application.controllers.abstracts.C_Generic;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Mascotas;
import application.views.AltaCita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_AltaCita extends C_Generic<AltaCita> implements ActionListener {
    public static Integer idMascota;
    public C_AltaCita() {
        super(AltaCita.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.btnRegresar.addActionListener(this);
        view.btnqrynombre.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.btnRegresar)){
            C_MenuCitas c_menuCitas = new C_MenuCitas();
            view = null;
            c_menuCitas.showView();
        }

        if(e.getSource().equals(view.btnqrynombre)){
            C_MostrarMascotas c_mostrarMascotas = new C_MostrarMascotas();
            c_mostrarMascotas.showView();
            c_mostrarMascotas.datosTabla(view.inombremascota.getText());
        }
    }

    public static void main(String[] args) {
        C_AltaCita c_altaCita = new C_AltaCita();
        c_altaCita.showView();
    }
}
