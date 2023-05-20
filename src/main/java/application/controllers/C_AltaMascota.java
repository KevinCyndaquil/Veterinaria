package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Alta;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Empleados;
import application.models.entidades.Mascotas;
import application.models.entidades.Personas;
import application.models.entidades.Razas;
import application.views.AltaMascota;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class C_AltaMascota extends C_Alta<AltaMascota, Mascotas>{
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
                view.jTextField5.setText(propietario + " [✓]");

                yield true;
            }
            case "Razas" -> {
                raza = (Razas) obj;
                //view.iNombreVeterinario.setText(veterinario.toString());

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

        /*
        Find<Personas> findPersona = new Repository<>(new Postgres());
        Personas personas;
        try {
            personas = (Personas) findPersona.findById(new Personas(id_propietario));
        } catch (SQLException e) {
            return null;
        }

        Find<Razas> findRazas = new Repository<>(new Postgres());
        Razas raza;

        try {
            raza = (Razas) findRazas.findById(new Razas(id_raza));
        } catch (SQLException e) {
            return null;
        }*/


        Mascotas mascota = new Mascotas(view.jTextField2.getText(),
                Date.valueOf(view.jTextField1.getText()),
                view.jTextField3.getText(),
                propietario,
                raza
        );

        try {
            mascota.setId_mascota((Integer) save.save(mascota));
            return mascota;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void initEvents() {
        view.jButton1.addActionListener(e -> {
            String name = view.jTextField4.getText();

            Personas propietario = new Personas(
                    null,
                    (name.isEmpty()) ? null : name,
                    null,
                    null,
                    null,
                    null);

            C_MostrarPropietarios c_mostrarPropietarios = new C_MostrarPropietarios(propietario);
            c_mostrarPropietarios.datosTabla(view.jTextField4.getText());
        });

        view.jButton2.addActionListener(e -> {
            C_MostrarRazas c_mostrarRazas = new C_MostrarRazas();
            c_mostrarRazas.showView();
            c_mostrarRazas.datosTabla(view.jTextField5.getText());
        });

        view.jButton3.addActionListener(e -> {
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
