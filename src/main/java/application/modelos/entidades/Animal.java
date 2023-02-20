package application.modelos.entidades;

import application.modelos.Tabla;

public class Animal extends Tabla<Integer> {
    public String nombre_cientifico;

    public Animal(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public Animal(Integer llave, String nombre, String nombre_cientifico) {
        super(llave, nombre);
        this.nombre_cientifico = nombre_cientifico;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }
}
