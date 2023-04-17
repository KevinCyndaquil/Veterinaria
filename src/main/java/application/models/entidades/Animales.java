package application.models.entidades;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Animales {

    private Integer id_animal;
    private String nombre_animal;

    public Animales(Integer id_animal, String nombre_animal) {
        this.id_animal = id_animal;
        this.nombre_animal = nombre_animal;
    }

    public Animales(String nombre_animal) {
        this.nombre_animal = nombre_animal;
    }
}
