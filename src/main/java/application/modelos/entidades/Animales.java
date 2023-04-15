package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Animales extends Raza {

    private Integer id_animal;
    private String nombre;


    public Animales(Integer id_animal,String nombre_animal, Integer id_raza, String nombre_raza, Integer total_adopcion) {
        super(id_raza, nombre_raza, total_adopcion);
        this.id_animal = id_animal;
        this.nombre = nombre_animal;
    }

    public Animales(String nombre_animal, String nombre_raza, Integer total_adopcion) {
        super(nombre_raza, total_adopcion);
        this.nombre = nombre_animal;
    }
}
