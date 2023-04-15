package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter @Setter
public class Raza  {
    private Integer id_raza;
    private String nombre;
    private Integer total_adopcion;

    public Raza(Integer id_raza, String nombre, Integer total_adopcion) {
        this.id_raza = id_raza;
        this.nombre = nombre;
        this.total_adopcion = total_adopcion;
    }

    public Raza(String nombre, Integer total_adopcion) {
        this.nombre = nombre;
        this.total_adopcion = total_adopcion;
    }
}
