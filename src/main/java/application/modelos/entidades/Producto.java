package application.modelos.entidades;

import application.modelos.Tabla;

public class Producto extends Tabla<Integer> {
    private String nombre;
    private Double costo;
    private String descripcion;
    private TipoProducto tipo;

    public Producto(String nombre, Double costo, String descripcion, TipoProducto tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Producto(Integer llave, String nombre, Double costo, String descripcion, TipoProducto tipo) {
        super(llave, nombre);
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
}
