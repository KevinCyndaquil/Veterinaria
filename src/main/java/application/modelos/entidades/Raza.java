package application.modelos.entidades;

import application.modelos.Tabla;

public class Raza extends Tabla<Integer> {
    private Integer total_adopcion;

    public Raza(Integer total_adopcion) {
        this.total_adopcion = total_adopcion;
    }

    public Raza(Integer llave, String nombre, Integer total_adopcion) {
        super(llave, nombre);
        this.total_adopcion = total_adopcion;
    }

    public Integer getTotal_adopcion() {
        return total_adopcion;
    }

    public void setTotal_adopcion(Integer total_adopcion) {
        this.total_adopcion = total_adopcion;
    }
}
