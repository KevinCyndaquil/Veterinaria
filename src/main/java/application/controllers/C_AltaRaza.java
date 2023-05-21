package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_AltaPasar;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Animales;
import application.models.entidades.Razas;
import application.views.alta.AltaRaza;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class C_AltaRaza extends C_AltaPasar<AltaRaza, Razas> {
    private Animales animal;

    public C_AltaRaza() {
        super(new AltaRaza());
    }

    @Override
    public Razas alta() {
        Save<Razas> save = new Repository<>(new Postgres());

        if (animal == null) {
            MessageDialog.stupidMessage(
                    view,
                    "No se ha ingresado el animal");
            return null;
        }

        Razas raza;
        try {
            raza = new Razas(
                view.iNombre.getText(),
                Integer.parseInt(view.iTotalAdopcion.getText()),
                animal);
        } catch (NumberFormatException e) {
            MessageDialog.stupidMessage(
                    view,
                    "El total en adopción debe de ser un número por favor"
            );
            return null;
        }

        try {
            save.save(raza);
            return raza;
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Ocurrió un error al registrar a la raza %s, llamen a Dios"
                            .formatted(raza));
            return null;
        }
    }

    @Override
    public boolean actualizar() {
        return false;
    }

    @Override
    public boolean pasar(@NotNull Object objToPass) {
        if (!objToPass.getClass().equals(Animales.class)) return false;

        animal = (Animales) objToPass;
        view.iAnimal.setText(animal + " [✓]");

        return true;
    }

    @Override
    public void initEvents() {
        view.bAnimal.addActionListener(e -> new C_MostrarAnimal());
        view.bRegistrar.addActionListener(e -> {
            Razas r = alta();
            if(r != null){
                MessageDialog.successMessage(view, "Raza registrada con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar raza");
            }
        });
    }
}
