package application.modelos.entidades;

import application.modelos.Tabla;

public class Propietario extends Tabla<String> {
    private String nombre;
    private String apellidos;

    public Propietario(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Propietario(String llave, String nombre, String apellidos) {
        super(llave, nombre);
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
