package application.modelos.entidades;

import application.modelos.Tabla;
import org.jetbrains.annotations.NotNull;

public class Veterinarios extends Tabla<String> {
    private String especialidad;
    private Empleados empleado;

    public Veterinarios(String especialidad, @NotNull Empleados empleado) {
        super(empleado.getLlave());
        this.especialidad = especialidad;
        this.empleado = empleado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
}
