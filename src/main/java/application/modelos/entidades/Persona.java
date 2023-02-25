package application.modelos.entidades;

import application.modelos.Tabla;

/**
 * @author KevinCyndaquil
 * Representa a una persona general, con sus atributos más escenciales.
 */

public class Persona extends Tabla<Integer> {
    private String rfc;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;

    public Persona(String rfc, String nombre, String apellido_paterno, String apellido_materno) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }

    public Persona(Integer id, String rfc, String nombre, String apellido_paterno, String apellido_materno) {
        super(id);
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }
}
