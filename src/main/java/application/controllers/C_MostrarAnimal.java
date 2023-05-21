package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.MostrarAnimal;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_MostrarAnimal extends C_Generic<MostrarAnimal> implements ActionListener {
    public C_MostrarAnimal() {
        super(MostrarAnimal.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.jButton1.addActionListener(this);
        view.jButton2.addActionListener(this);
    }

    public void datosTabla(String nombre){
        DefaultTableModel modelo = (DefaultTableModel) view.jTable1.getModel();
        modelo.setRowCount(0);

        modelo.addRow(new Object[]{"32", "PERRO"});
        modelo.addRow(new Object[]{"33", "GATO"});

        view.jTable1.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.jButton1)){
            int id = Integer.parseInt(view.jTable1.getValueAt(view.jTable1.getSelectedRow(), 0).toString());
            //C_AltaRaza.id_animal = id;
            MessageDialog.successMessage(view, "Animal seleccionado: " + id);
            view.dispose();
        }
        if(e.getSource().equals(view.jButton2)){
            C_AltaAnimal cAltaAnimal = new C_AltaAnimal();
            cAltaAnimal.showView();
            view.dispose();
        }
    }
}
