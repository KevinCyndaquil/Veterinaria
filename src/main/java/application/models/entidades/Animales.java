package application.models.entidades;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Animales extends Razas {

    private Integer id_animal;
    private String nombre_animal;
    private Integer total_adopcion;


    public Animales(Integer id_raza, String nombre_raza) {
        super(id_raza, nombre_raza);
    }

    public Animales(String nombre_raza) {
        super(nombre_raza);
    }
}
