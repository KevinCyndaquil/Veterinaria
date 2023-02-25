package application.modelos.entidades;

import application.modelos.Tabla;

public class Animal extends Tabla<Integer> {
    private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public Animal(Integer integer, String nombre) {
        super(integer);
        this.nombre = nombre;
    }
}
