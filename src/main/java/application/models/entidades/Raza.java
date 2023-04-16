package application.models.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Raza  {
    private Integer id_raza;
    private String nombre;

    public Raza(Integer id_raza, String nombre) {
        this.id_raza = id_raza;
        this.nombre = nombre;

    }

    public Raza(String nombre) {
        this.nombre = nombre;
    }
}
