package application.modelos.entidades;

import application.modelos.Tabla;

public class Alimento extends Tabla<Integer> {
    private Double costo;
    private Double gramaje;
    private String descripcion;

    public Alimento(Double costo, Double gramaje, String descripcion) {
        this.costo = costo;
        this.gramaje = gramaje;
        this.descripcion = descripcion;
    }

    public Alimento(Integer llave, String nombre, Double costo, Double gramaje, String descripcion) {
        super(llave, nombre);
        this.costo = costo;
        this.gramaje = gramaje;
        this.descripcion = descripcion;
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
