package application.modelos.entidades;

import application.modelos.Tabla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Empleado extends Persona{
    private LocalDate fecha_inicio;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private Puesto puesto;

    public Empleado(String rfc, String nombre, String apellido_paterno, String apellido_materno, LocalDate fecha_inicio, LocalTime hora_inicio, LocalTime hora_fin, Puesto puesto) {
        super(rfc, nombre, apellido_paterno, apellido_materno);
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.puesto = puesto;
    }

    public Empleado(Integer id, String rfc, String nombre, String apellido_paterno, String apellido_materno, LocalDate fecha_inicio, LocalTime hora_inicio, LocalTime hora_fin, Puesto puesto) {
        super(id, rfc, nombre, apellido_paterno, apellido_materno);
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.puesto = puesto;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
}
