package application.models.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Razas extends Animales{

    private Integer id_raza;
    private String nombre_raza;
    private Integer total_adopcion;


    public Razas(Integer id_animal, String nombre_animal, String nombre_raza, Integer total_adopcion) {
        super(id_animal, nombre_animal);
        this.id_raza = id_raza;
        this.nombre_raza = nombre_raza;
        this.total_adopcion = total_adopcion;
    }

    public Razas(String nombre_animal, Integer id_raza, String nombre_raza, Integer total_adopcion) {
        super(nombre_animal);
        this.id_raza = id_raza;
        this.nombre_raza = nombre_raza;
        this.total_adopcion = total_adopcion;
    }
}
