package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_AltaPasar;
import application.controllers.terminados.C_MostrarMascotas;
import application.models.entidades.Citas;
import application.models.entidades.Empleados;
import application.models.entidades.Mascotas;
import application.views.terminadas.AltaCita;
import org.jetbrains.annotations.NotNull;

public class C_AltaCita extends C_AltaPasar<AltaCita, Citas> {
    private Mascotas mascota;
    private Empleados veterinario;

    public C_AltaCita() {
        super(new AltaCita());
    }

    @Override
    public boolean pasar(@NotNull Object obj) {
        return switch (obj.getClass().getSimpleName()) {
            case "Mascotas" -> {
                mascota = (Mascotas) obj;
                view.iNombreMascota.setText(mascota + ", " + mascota.getPropietario() + " [✓]");

                yield true;
            }
            case "Veterinario" -> {
                veterinario = (Empleados) obj;
                view.iNombreVeterinario.setText(veterinario + " [✓]");

                yield true;
            }
            default -> false;
        };
    }

    @Override
    public void initEvents() {
        view.btnRegresar.addActionListener(e -> {
            MessageDialog.successMessage(null, "Todo bien");
        });

        view.btnQryNombre.addActionListener(e -> {
            String name = view.iNombreMascota.getText();

            Mascotas mascota = new Mascotas(
                    (name.isEmpty()) ? null : name,
                    null,
                    null,
                    null,
                    null);
            C_MostrarMascotas c_mostrarMascotas = new C_MostrarMascotas(mascota, this);
            c_mostrarMascotas.mostrar();
        });

        view.btnQryVeterinario.addActionListener(e -> {
            String name = view.iNombreMascota.getText();

            Empleados veterianrio = new Empleados(
                    null,
                    (name.isEmpty()) ? null : name,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            //aqui tiene que llamar a mostrar veterinarios
        });

        view.btnAgendar.addActionListener(e -> alta());
    }

    @Override
    public Citas alta() {
        return null;
    }

    @Override
    public boolean actualizar() {
        return false;
    }
}
