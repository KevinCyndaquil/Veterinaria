package application.modelos.entidades;

import application.modelos.Tabla;

public class Medicamentos extends Tabla<Integer> {
    private String nombre;
    private Double costo;
    private Double gramaje;
    private String laboratorio;
    private String descripcion;

    public Medicamentos(String nombre, Double costo, Double gramaje, String laboratorio, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.descripcion = descripcion;
    }

    public Medicamentos(Integer integer, String nombre, Double costo, Double gramaje, String laboratorio, String descripcion) {
        super(integer);
        this.nombre = nombre;
        this.costo = costo;
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getGramaje() {
        return gramaje;
    }

    public void setGramaje(Double gramaje) {
        this.gramaje = gramaje;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
