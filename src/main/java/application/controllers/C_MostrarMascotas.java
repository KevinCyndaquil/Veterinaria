package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Mascotas;
import application.views.MostrarMascotas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class C_MostrarMascotas extends C_Generic<MostrarMascotas> implements ActionListener {
    public C_MostrarMascotas() {
        super(MostrarMascotas.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.btnSeleccionar.addActionListener(this);
        view.btnRegistrar.addActionListener(this);
    }

    public void datosTabla(String name){
        Find<Mascotas> find = new Repository<>(new Postgres());
        Mascotas mascota = new Mascotas(name, null, null, null, null);
        try {
           List<Object> mascotas = find.find(mascota);
            System.out.println(mascotas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.btnSeleccionar)){
            int row = view.table1.getSelectedRow();
            C_AltaCita.idMascota = Integer.parseInt(view.table1.getValueAt(row, 0).toString());
            MessageDialog.successMessage(view, "Mascota seleccionada: " + Integer.parseInt(view.table1.getValueAt(row, 0).toString()));
            view.dispose();
        }

        if(e.getSource().equals(view.btnRegistrar)){
            C_AltaMascota c_altaMascota = new C_AltaMascota();
            c_altaMascota.showView();
            view.dispose();
        }

    }
}
