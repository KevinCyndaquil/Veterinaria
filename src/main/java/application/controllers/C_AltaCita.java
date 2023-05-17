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
    public static Mascotas mascota;
    public static Integer idVeterinario;
    public C_AltaCita() {
        super(AltaCita.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.btnRegresar.addActionListener(this);
        view.btnqrynombre.addActionListener(this);
        view.btnqryveterinario.addActionListener(this);
        view.btnagendar.addActionListener(this);
    }

    public Integer altaCita(){
        System.out.println(mascota.getNombre());
        return 1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //regresar
        if(e.getSource().equals(view.btnRegresar)){
            C_MenuCitas c_menuCitas = new C_MenuCitas();
            view = null;
            c_menuCitas.showView();
        }

        //buscarmascota
        if(e.getSource().equals(view.btnqrynombre)){
            C_MostrarMascotas c_mostrarMascotas = new C_MostrarMascotas();
            c_mostrarMascotas.showView();
            c_mostrarMascotas.datosTabla(view.inombremascota.getText());
        }

        //buscarvete
        if(e.getSource().equals(view.btnqryveterinario)){
            C_MostrarPropietarios c_mostrarpro = new C_MostrarPropietarios();
            c_mostrarpro.showView();
            c_mostrarpro.datosTabla(view.inombreveterinario.getText());
        }

        //agendar
        if(e.getSource().equals(view.btnagendar)){
            altaCita();
        }
    }

    public static void main(String[] args) {
        C_AltaCita c_altaCita = new C_AltaCita();
        c_altaCita.showView();
    }
}
