package application.modelos.entidades;

import application.modelos.Tabla;

public class Propietario extends Tabla<String> {
    private String apellido_paterno;
    private String apellido_materno;

    public Propietario(String apellido_paterno, String apellido_materno) {
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }

    public Propietario(String llave, String nombre, String apellido_paterno, String apellido_materno) {
        super(llave, nombre);
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }
}
