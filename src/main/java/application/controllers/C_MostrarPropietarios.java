package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Mostrar;
import application.models.entidades.Personas;
import application.views.components.ShowerTable;

public class C_MostrarPropietarios extends C_Mostrar<ShowerTable, Personas> {
    public C_MostrarPropietarios(Personas propietario) {
        super(new ShowerTable(new Object[]{
                "ID",
                "RFC",
                "Nombre",
                "Apellido paterno",
                "Apellido materno",
                "No Cuenta"
        }), propietario);
    }


    public void datosTabla(String name){


    }

    @Override
    public void mostrar() {

    }

    @Override
    public void initEvents() {
        view.btnSeleccionar.addActionListener(e -> {
            int id = Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString());
            MessageDialog.successMessage(view, "Propietario seleccionado: " + id);
            view.dispose();
        });

        view.btnRegistrar.addActionListener(e -> {
            C_AltaPropietarios cAltaPropietarios = new C_AltaPropietarios();
            cAltaPropietarios.showView();
            view.dispose();
        });
    }
}
