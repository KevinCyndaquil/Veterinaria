package application.modelos.entidades;

import application.modelos.Tabla;
import org.jetbrains.annotations.NotNull;

public class Veterinario extends Tabla<String> {
    private Empleado empleado;

    public Veterinario(@NotNull Empleado empleado, String nombre) {
        super(empleado.getLlave(), nombre);
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
