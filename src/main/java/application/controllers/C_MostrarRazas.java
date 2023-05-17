package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.MostrarRaza;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_MostrarRazas extends C_Generic<MostrarRaza> implements ActionListener {
    public C_MostrarRazas() {
        super(MostrarRaza.class);
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

        modelo.addRow(new Object[]{"32", "dada", "JOSE", "13", "PERRO"});
        modelo.addRow(new Object[]{"33", "dada", "JOSE", "13", "PERRO"});

        view.jTable1.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.jButton1)){
            int id = Integer.parseInt(view.jTable1.getValueAt(view.jTable1.getSelectedRow(), 0).toString());
            C_AltaMascota.id_raza = id;
            MessageDialog.successMessage(view, "Raza seleccionada: " + id);
            view.dispose();
        }

        if(e.getSource().equals(view.jButton2)){
            C_AltaRazas cAltaRazas = new C_AltaRazas();
            cAltaRazas.showView();
            view.dispose();
        }
    }
}
