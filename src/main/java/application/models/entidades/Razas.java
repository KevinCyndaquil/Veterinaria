package application.models.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Razas {
    private Integer id_raza;
    private String nombre;

    public Razas(Integer id_raza, String nombre) {
        this.id_raza = id_raza;
        this.nombre = nombre;

    }

    public Razas(String nombre) {
        this.nombre = nombre;
    }

}
