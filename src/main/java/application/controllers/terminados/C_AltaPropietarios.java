package application.controllers.terminados;

import application.MessageDialog;
import application.controllers.abstracts.C_Alta;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Personas;
import application.views.alta.AltaPropietario;

import java.sql.SQLException;

public class C_AltaPropietarios extends C_Alta<AltaPropietario, Personas> {
    public C_AltaPropietarios() {
        super(new AltaPropietario());
    }

    @Override
    public Personas alta() {
        Save<Personas> save = new Repository<>(new Postgres());

        Personas persona = new Personas(
                view.iRfc.getText(),
                view.iNombre.getText(),
                view.iApellido_p.getText(),
                view.iApellido_m.getText(),
                view.iNoCuenta.getText());

        try {
            persona.setId_persona((Integer) save.save(persona));
            return persona;
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Ocurrió un error al registrar a la persona %s, llamen a Dios"
                            .formatted(persona));
            return null;
        }
    }

    @Override
    public void initEvents() {
        view.bRegistrar.addActionListener(e -> {
            Personas p = alta();

            if(p != null){
                //C_AltaCita.mascota = m;
                MessageDialog.successMessage(view, "Propietario registrado con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar propietario");
            }
        });
    }
}

