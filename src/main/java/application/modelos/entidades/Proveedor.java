package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class Proveedor extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String direccion;
    @Getter @Setter
    private String telefono;
    @Getter @Setter
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
