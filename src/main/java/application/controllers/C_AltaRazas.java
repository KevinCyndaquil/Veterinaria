package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.AltaRaza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_AltaRazas extends C_Generic<AltaRaza> implements ActionListener {
    public static Integer id_animal;
    public C_AltaRazas() {
        super(AltaRaza.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.jButton3.addActionListener(this);
        view.jButton1.addActionListener(this);
    }

    public Integer altaRaza(){
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.jButton1)){
            C_MostrarAnimal c_mostrarEspecies = new C_MostrarAnimal();
            c_mostrarEspecies.showView();
            c_mostrarEspecies.datosTabla(view.jTextField1.getText());
        }

        if(e.getSource().equals(view.jButton3)){
            Integer idRaza = altaRaza();
            if(altaRaza() != -1){
                //C_AltaMascota.id_raza = idRaza;
                MessageDialog.successMessage(view, "Raza registrada con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar raza");
            }
        }
    }

}
