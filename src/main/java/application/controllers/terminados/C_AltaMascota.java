package application.controllers.terminados;

import application.MessageDialog;
import application.controllers.abstracts.C_AltaPasar;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Mascotas;
import application.models.entidades.Personas;
import application.models.entidades.Razas;
import application.views.alta.AltaMascota;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.SQLException;

public class C_AltaMascota extends C_AltaPasar<AltaMascota, Mascotas> {
    public static Personas propietario;
    public static Razas raza;

    public C_AltaMascota() {
        super(new AltaMascota());
    }

    @Override
    public boolean pasar(@NotNull Object obj) {
        return switch (obj.getClass().getSimpleName()) {
            case "Personas" -> {
                propietario = (Personas) obj;
                view.iPropietario.setText(propietario + " [✓]");

                yield true;
            }
            case "Razas" -> {
                raza = (Razas) obj;
                view.iRaza.setText(raza + " [✓]");

                yield true;
            }
            default -> false;
        };
    }

    @Override
    public Mascotas alta() {
        Save<Mascotas> save = new Repository<>(new Postgres());

        if (propietario == null || raza == null) {
            MessageDialog.stupidMessage(
                    view,
                    "No se ha ingresado el propietario o la raza");
            return null;
        }

        Mascotas mascota = new Mascotas(
                view.iNombre.getText(),
                new Date(view.dFecha.getDate().getTime()),
                view.cSexo.getSelectedItem().toString(),
                propietario,
                raza
        );

        try {
            mascota.setId_mascota((Integer) save.save(mascota));
            return mascota;
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Ocurrió un error al registrar a la mascota %s, llamen a Dios"
                            .formatted(mascota));
            return null;
        }
    }

    @Override
    public void initEvents() {
        view.bPropietario.addActionListener(e -> {
            String name = view.iPropietario.getText();

            Personas propietario = new Personas(
                    null,
                    (name.isEmpty()) ? null : name,
                    null,
                    null,
                    null,
                    null);

            C_MostrarPropietarios c_mostrarPropietarios = new C_MostrarPropietarios(
                    propietario,
                    this);
            c_mostrarPropietarios.mostrar();
        });

        view.bRaza.addActionListener(e -> {
            String name = view.iRaza.getText();

            Razas raza = new Razas(
                    null,
                    (name.isEmpty()) ? null : name,
                    null,
                    null);

            C_MostrarRazas c_mostrarRazas = new C_MostrarRazas(
                    raza,
                    this);
            c_mostrarRazas.mostrar();
        });

        view.bRegistrar.addActionListener(e -> {
            Mascotas m = alta();
            if(m != null){
                //C_AltaCita.mascota = m;
                MessageDialog.successMessage(view, "Mascota registrada con exito");
                view.dispose();
            }else{
                MessageDialog.errorMessage(view, "Error al registrar mascota");
            }
        });
    }
}
