package application.modelos.entidades;

import application.modelos.Tabla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Empleado extends Tabla<String> {
    private String apellidos;
    private LocalDate fecha_inicial;
    private LocalTime jornada_inicio;
    private LocalTime Jornada_fin;

    public Empleado(String apellidos, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin) {
        this.apellidos = apellidos;
        this.fecha_inicial = fecha_inicial;
        this.jornada_inicio = jornada_inicio;
        Jornada_fin = jornada_fin;
    }

    public Empleado(String llave, String nombre, String apellidos, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin) {
        super(llave, nombre);
        this.apellidos = apellidos;
        this.fecha_inicial = fecha_inicial;
        this.jornada_inicio = jornada_inicio;
        Jornada_fin = jornada_fin;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
        return Jornada_fin;
    }

    public void setJornada_fin(LocalTime jornada_fin) {
        Jornada_fin = jornada_fin;
    }
}
