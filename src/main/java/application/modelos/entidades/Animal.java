package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class Animal extends Tabla {
    @Getter @Setter
    private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public Animal(Integer integer, String nombre) {
        super(integer);
        this.nombre = nombre;
    }
}
