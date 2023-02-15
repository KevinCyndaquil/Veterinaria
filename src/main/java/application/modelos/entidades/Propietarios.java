package application.modelos.entidades;

import application.modelos.Tabla;

public class Propietarios extends Tabla<String> {
    private String nombre;
    private String apellidos;

    public Propietarios(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Propietarios(String s, String nombre, String apellidos) {
        super(s);
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
