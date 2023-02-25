package application.modelos.entidades;

import application.modelos.Tabla;

public class Propietario extends Persona {
    public Propietario(String rfc, String nombre, String apellido_paterno, String apellido_materno) {
        super(rfc, nombre, apellido_paterno, apellido_materno);
    }

    public Propietario(Integer id, String rfc, String nombre, String apellido_paterno, String apellido_materno) {
        super(id, rfc, nombre, apellido_paterno, apellido_materno);
    }
}
