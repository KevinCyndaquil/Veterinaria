package application.modelos.entidades;

import application.modelos.Tabla;

public class Puesto extends Tabla<Integer> {
    private String nombre;
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
