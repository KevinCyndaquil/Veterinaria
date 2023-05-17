package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Personas;
import application.views.MostrarPropietario;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class C_MostrarPropietarios extends C_Generic<MostrarPropietario> implements ActionListener {
    public C_MostrarPropietarios() {
        super(MostrarPropietario.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.jButton1.addActionListener(this);
        view.jButton2.addActionListener(this);
    }

    public void datosTabla(String name){

        if(name.isEmpty()) name = null;
        DefaultTableModel modelo = (DefaultTableModel) view.jTable1.getModel();
        modelo.setRowCount(0);

        Find<Personas> findPersona = new Repository<>(new Postgres());


        try {
            for(Object obj : findPersona.find(new Personas(null,name,null,null,null))){
                Personas p = (Personas)  obj;

                modelo.addRow(new Object[]{p.getId_persona(),p.getRfc(),p.getNombre(),p.getApellido_p(),p.getApellido_m(),p.getNo_cuenta()});
            }
            //view.jTable1.setModel(modelo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //seleccionar
        if(e.getSource().equals(view.jButton1)){
            int id = Integer.parseInt(view.jTable1.getValueAt(view.jTable1.getSelectedRow(), 0).toString());
            C_AltaMascota.id_propietario = id;
            MessageDialog.successMessage(view, "Propietario seleccionado: " + id);
            view.dispose();
        }
        //registrar
        if(e.getSource().equals(view.jButton2)){
            C_AltaPropietarios cAltaPropietarios = new C_AltaPropietarios();
            cAltaPropietarios.showView();
            view.dispose();
        }

    }
}
