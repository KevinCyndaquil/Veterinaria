package application.modelos.entidades;

import application.modelos.Tabla;

public class Proveedor extends Tabla<Integer> {
    private String nombre;
    private String direccion;
    private String telefono;
    private String descripcion;

    public Proveedor(String nombre, String direccion, String telefono, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public Proveedor(Integer id, String nombre, String direccion, String telefono, String descripcion) {
        super(id);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }
}
