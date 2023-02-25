package application.modelos.entidades;

import application.modelos.Tabla;

public class Raza extends Tabla<Integer> {
    private String nombre;
    private int total_adopcion;
    private Animal animal;

    public Raza(String nombre, int total_adopcion, Animal animal) {
        this.nombre = nombre;
        this.total_adopcion = total_adopcion;
        this.animal = animal;
    }

    public Raza(Integer id, String nombre, int total_adopcion, Animal animal) {
        super(id);
        this.nombre = nombre;
        this.total_adopcion = total_adopcion;
        this.animal = animal;
    }
}
