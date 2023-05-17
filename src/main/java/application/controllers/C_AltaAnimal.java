package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.AltaAnimal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_AltaAnimal extends C_Generic<AltaAnimal> implements ActionListener {
    public C_AltaAnimal() {
        super(AltaAnimal.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.jButton3.addActionListener(this);
    }

    public Integer altaAnimal(){
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.jButton3)){
            Integer idAnimal = altaAnimal();
            if(altaAnimal() != -1){
                C_AltaRazas.id_animal = idAnimal;
                MessageDialog.successMessage(view, "Animal registrado con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar animal");
            }
        }
    }
}
