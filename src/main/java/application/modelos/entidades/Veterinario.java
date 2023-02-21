package application.modelos.entidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Veterinario extends Empleado {
    private List<Especialidad> especialidades;

    public Veterinario(String apellido_paterno, String apellido_materno, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin, List<Especialidad> especialidades) {
        super(apellido_paterno, apellido_materno, fecha_inicial, jornada_inicio, jornada_fin);
        this.especialidades = (especialidades == null) ? new ArrayList<>() : especialidades;
    }

    public Veterinario(String llave, String nombre, String apellido_paterno, String apellido_materno, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin, List<Especialidad> especialidades) {
        super(llave, nombre, apellido_paterno, apellido_materno, fecha_inicial, jornada_inicio, jornada_fin);
        this.especialidades = (especialidades == null) ? new ArrayList<>() : especialidades;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
}
