package application.modelos.entidades;

import application.modelos.Tabla;

/**
 * @author KevinCyndaquil
 * Es un artículo dentro de la veterinaria, un objeto que contiene un nombre, un precio y una descripción
 * al usuario, que puede ser vendido, administrado o contado.
 */

public class Articulo extends Tabla<Integer> {
    private String nombre;
    private Double monto;
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
}
