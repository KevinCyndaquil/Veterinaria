package application.modelos.entidades;

import application.modelos.Tabla;

public class Medicamento extends Tabla<Integer> {
    private Double costo;
    private Double gramaje;
    private String laboratorio;
    private String descripcion;
    private String tipo;

    public Medicamento(Double costo, Double gramaje, String laboratorio, String descripcion, String tipo) {
        this.costo = costo;
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Medicamento(Integer llave, String nombre, Double costo, Double gramaje, String laboratorio, String descripcion, String tipo) {
        super(llave, nombre);
        this.costo = costo;
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
