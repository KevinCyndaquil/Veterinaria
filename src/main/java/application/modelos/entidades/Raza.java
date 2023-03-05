package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class Raza extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private int total_adopcion;
    @Getter @Setter
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
