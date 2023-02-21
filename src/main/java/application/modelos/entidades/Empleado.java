package application.modelos.entidades;

import application.modelos.Tabla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Empleado extends Tabla<String> {
    private String apellido_paterno;
    private String apellido_materno;
    private LocalDate fecha_inicial;
    private LocalTime jornada_inicio;
    private LocalTime jornada_fin;

    public Empleado(String apellido_paterno, String apellido_materno, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin) {
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_inicial = fecha_inicial;
        this.jornada_inicio = jornada_inicio;
        this.jornada_fin = jornada_fin;
    }

    public Empleado(String llave, String nombre, String apellido_paterno, String apellido_materno, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin) {
        super(llave, nombre);
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_inicial = fecha_inicial;
        this.jornada_inicio = jornada_inicio;
        this.jornada_fin = jornada_fin;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public LocalDate getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(LocalDate fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public LocalTime getJornada_inicio() {
        return jornada_inicio;
    }

    public void setJornada_inicio(LocalTime jornada_inicio) {
        this.jornada_inicio = jornada_inicio;
    }

    public LocalTime getJornada_fin() {
        return jornada_fin;
    }

    public void setJornada_fin(LocalTime jornada_fin) {
        this.jornada_fin = jornada_fin;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }
}
