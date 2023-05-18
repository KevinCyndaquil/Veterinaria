package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Alta;
import application.controllers.abstracts.C_Generic;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.GetConn;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Mascotas;
import application.models.entidades.Personas;
import application.models.entidades.Razas;
import application.views.AltaMascota;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;


public class C_AltaMascota extends C_Alta<AltaMascota, Mascotas> implements ActionListener {
    public static Integer id_propietario;
    public static Integer id_raza;
    public C_AltaMascota() {
        super(AltaMascota.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.jButton1.addActionListener(this);
        view.jButton3.addActionListener(this);
        view.jButton2.addActionListener(this);
    }

    @Override
    public Mascotas alta() {
        Save<Mascotas> save = new Repository<>(new Postgres());

        Find<Personas> findPersona = new Repository<>(new Postgres());
        Personas personas;
        try {
            personas = (Personas) findPersona.findById(new Personas(id_propietario));
        } catch (SQLException e) {
            return null;
        }

        Find<Razas> findRazas = new Repository<>(new Postgres());
        Razas raza;

        try {
            raza = (Razas) findRazas.findById(new Razas(id_raza));
        } catch (SQLException e) {
            return null;
        }


        Mascotas mascota = new Mascotas(view.jTextField2.getText(),
                Date.valueOf(view.jTextField1.getText()),
                view.jTextField3.getText(),
                personas,
                raza
        );

        try {
            mascota.setId_mascota((Integer) save.save(mascota));
            return mascota;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.jButton1)){
            C_MostrarPropietarios c_mostrarPropietarios = new C_MostrarPropietarios();
            c_mostrarPropietarios.showView();
            c_mostrarPropietarios.datosTabla(view.jTextField4.getText());
        }

        if(e.getSource().equals(view.jButton2)){
            C_MostrarRazas c_mostrarRazas = new C_MostrarRazas();
            c_mostrarRazas.showView();
            c_mostrarRazas.datosTabla(view.jTextField5.getText());
        }

        if(e.getSource().equals(view.jButton3)){
            Mascotas m = alta();
            if(m != null){
                C_AltaCita.mascota = m;
                MessageDialog.successMessage(view, "Mascota registrada con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar mascota");
            }
        }


    }


}
