package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Proveedores {
    private Integer id_proveedor;
    private String nombre;
    private String direccion;
    private String telefono;
    private String descripcion;

    public Proveedores(Integer id_proveedor, String nombre, String telefono, String descripcion) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public Proveedores(String nombre, String telefono, String descripcion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }
}
