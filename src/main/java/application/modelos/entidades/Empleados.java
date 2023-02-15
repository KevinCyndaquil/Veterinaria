package application.modelos.entidades;

import application.modelos.Tabla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Empleados extends Tabla<String> {
    private String nombre;
    private String apellidos;
    private LocalDate fecha_inicial;
    private LocalTime jornada_inicio;
    private LocalTime Jornada_fin;

    public Empleados(String nombre, String apellidos, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_inicial = fecha_inicial;
        this.jornada_inicio = jornada_inicio;
        Jornada_fin = jornada_fin;
    }

    public Empleados(String s, String nombre, String apellidos, LocalDate fecha_inicial, LocalTime jornada_inicio, LocalTime jornada_fin) {
        super(s);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_inicial = fecha_inicial;
        this.jornada_inicio = jornada_inicio;
        Jornada_fin = jornada_fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
