package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class Puesto extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private Double salario;

    public Puesto(String nombre, Double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public Puesto(Integer id, String nombre, Double salario) {
        super(id);
        this.nombre = nombre;
        this.salario = salario;
    }
}
