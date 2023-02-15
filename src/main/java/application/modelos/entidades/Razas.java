package application.modelos.entidades;

import application.modelos.Tabla;

public class Razas extends Tabla<Integer> {
    private String nombre;
    private Integer total_adopcion;

    public Razas(String nombre, Integer total_adopcion) {
        this.nombre = nombre;
        this.total_adopcion = total_adopcion;
    }

    public Razas(Integer integer, String nombre, Integer total_adopcion) {
        super(integer);
        this.nombre = nombre;
        this.total_adopcion = total_adopcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotal_adopcion() {
        return total_adopcion;
    }

    public void setTotal_adopcion(Integer total_adopcion) {
        this.total_adopcion = total_adopcion;
    }
}
