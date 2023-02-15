package application.modelos.finanzas;

import application.modelos.Tabla;

public class Servicios extends Tabla <Integer> {
    private String nombre;

    public Servicios(String nombre) {
        this.nombre = nombre;
    }

    public Servicios(Integer integer, String nombre) {
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
