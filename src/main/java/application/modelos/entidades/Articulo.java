package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

/**
 * @author KevinCyndaquil
 * Es un artículo dentro de la veterinaria, un objeto que contiene un nombre, un precio y una descripción
 * al usuario, que puede ser vendido, administrado o contado.
 */

public class Articulo extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private Double monto;
    @Getter @Setter
    private String descripcion;

    public Articulo(String nombre, Double monto, String descripcion) {
        this.nombre = nombre;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public Articulo(Integer id, String nombre, Double monto, String descripcion) {
        super(id);
        this.nombre = nombre;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre + ", " + descripcion;
    }
}
