package application.modelos.entidades;

import application.modelos.Tabla;

public class Animal extends Tabla<Integer> {

    public Animal() {
    }

    public Animal(Integer llave, String nombre) {
        super(llave, nombre);
    }
}
