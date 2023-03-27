package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

/**
 * @author KevinCyndaquil
 * Representa a una persona general, con sus atributos más escenciales.
 */

public class Persona extends Tabla {
    @Getter @Setter
    private String rfc;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String apellido_paterno;
    @Getter @Setter
    private String apellido_materno;
    @Getter @Setter
    private String no_cuenta;

    public Persona(String rfc, String nombre, String apellido_paterno, String apellido_materno, String noCuenta) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        no_cuenta = noCuenta;
    }

    public Persona(Integer id, String rfc, String nombre, String apellido_paterno, String apellido_materno, String noCuenta) {
        super(id);
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        no_cuenta = noCuenta;
    }
}
