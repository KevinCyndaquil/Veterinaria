package application.modelos.entidades;

import application.modelos.Tabla;

public class Proveedores extends Tabla <Integer> {
    private String nombre;

    public Proveedores(String nombre) {
        this.nombre = nombre;
    }

    public Proveedores(Integer integer, String nombre) {
        super(integer);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
