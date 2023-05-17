package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.views.AltaMascota;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class C_AltaMascota extends C_Generic<AltaMascota>  implements ActionListener {
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

    public Integer altaMascota(){
        return 0;
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
            Integer idMascota = altaMascota();
            if(altaMascota() != -1){
                C_AltaCita.idMascota = idMascota;
                MessageDialog.successMessage(view, "Mascota registrada con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar mascota");
            }
        }


    }
}
