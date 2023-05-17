package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.AltaPropietario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_AltaPropietarios extends C_Generic<AltaPropietario> implements ActionListener {
    public C_AltaPropietarios() {
        super(AltaPropietario.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.jButton3.addActionListener(this);
    }

    public Integer altaPropietario(){
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.jButton3)){
            Integer idPropietario = altaPropietario();
            if(altaPropietario() != -1){
                C_AltaMascota.id_propietario = idPropietario;
                MessageDialog.successMessage(view, "Propietario registrado con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar propietario");
            }
        }
    }
}
