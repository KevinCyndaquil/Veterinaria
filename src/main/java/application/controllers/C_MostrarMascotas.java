package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Mascotas;
import application.views.MostrarMascotas;

import javax.swing.table.DefaultTableModel;
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

        if(name.isEmpty()){
            name = null;
        }
        Find<Mascotas> find = new Repository<>(new Postgres());
        Mascotas mascota = new Mascotas(name, null, null, null, null);
        try {
            DefaultTableModel mod = (DefaultTableModel) view.table1.getModel();
            mod.setRowCount(0);
            for(Object modelo : find.find(mascota)){
                Mascotas m = (Mascotas) modelo;



                mod.addRow(new Object[]{m, m.getNombre(), m.getFecha_nacimiento(), m.getSexo(), m.getPropietario().getNombre(), m.getRaza().nombre()});
            }
            view.table1.setModel(mod);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.btnSeleccionar)){
            int row = view.table1.getSelectedRow();
            C_AltaCita.mascota = (Mascotas) view.table1.getModel().getValueAt(row, 0);
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
