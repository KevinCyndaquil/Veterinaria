package application.modelos.entidades;

import application.modelos.Tabla;

public class Proveedor extends Tabla <Integer> {
    private String direccion;
    private String telefono;
    private String descripcion;

    public Proveedor(String direccion, String telefono, String descripcion) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public Proveedor(Integer llave, String nombre, String direccion, String telefono, String descripcion) {
        super(llave, nombre);
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
