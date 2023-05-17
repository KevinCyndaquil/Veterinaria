package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.MostrarPropietario;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        DefaultTableModel modelo = (DefaultTableModel) view.jTable1.getModel();
        modelo.setRowCount(0);
        modelo.addRow(new Object[]{"32", "3232D", "JOSE", "CHAVEZ", "Perez", "1234567890"});
        modelo.addRow(new Object[]{"33", "3232D", "JOSE", "CHAVEZ", "Perez", "1234567890"});


        view.jTable1.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.jButton1)){
            int id = Integer.parseInt(view.jTable1.getValueAt(view.jTable1.getSelectedRow(), 0).toString());
            C_AltaMascota.id_propietario = id;
            MessageDialog.successMessage(view, "Propietario seleccionado: " + id);
            view.dispose();
        }
        if(e.getSource().equals(view.jButton2)){
            C_AltaPropietarios cAltaPropietarios = new C_AltaPropietarios();
            cAltaPropietarios.showView();
            view.dispose();
        }

    }
}
