package application.modelos.entidades;

import application.modelos.Tabla;

public class Alimentos extends Tabla<Integer> {
    private String nombre;
    private Double costo;
    private Double gramaje;
    private String descripcion;

    public Alimentos(String nombre, Double costo, Double gramaje, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.gramaje = gramaje;
        this.descripcion = descripcion;
    }

    public Alimentos(Integer integer, String nombre, Double costo, Double gramaje, String descripcion) {
        super(integer);
        this.nombre = nombre;
        this.costo = costo;
        this.gramaje = gramaje;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
