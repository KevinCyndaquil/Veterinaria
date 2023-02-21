package application.modelos.entidades;

import application.modelos.Tabla;

public class Raza extends Tabla<Integer> {
    private Integer total_adopcion;
    private Tabla<?> animal;

    public Raza(Integer total_adopcion, Tabla<?> animal) {
        this.total_adopcion = total_adopcion;
        this.animal = animal;
    }

    public Raza(Integer llave, String nombre, Integer total_adopcion, Tabla<?> animal) {
        super(llave, nombre);
        this.total_adopcion = total_adopcion;
        this.animal = animal;
    }

    public Integer getTotal_adopcion() {
        return total_adopcion;
    }

    public void setTotal_adopcion(Integer total_adopcion) {
        this.total_adopcion = total_adopcion;
    }

    public Tabla<?> getAnimal() {
        return animal;
    }

    public void setAnimal(Tabla<?> animal) {
        this.animal = animal;
    }
}
