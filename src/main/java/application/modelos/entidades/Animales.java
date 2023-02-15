package application.modelos.entidades;

import application.modelos.Tabla;

public class Animales extends Tabla<Integer> {
    public String nombre;
    public String nombre_cientifico;

    public Animales(String nombre, String nombre_cientifico) {
        this.nombre = nombre;
        this.nombre_cientifico = nombre_cientifico;
    }

    public Animales(Integer llave, String nombre, String nombre_cientifico) {
        super(llave);
        this.nombre = nombre;
        this.nombre_cientifico = nombre_cientifico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }
}
