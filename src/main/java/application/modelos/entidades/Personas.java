package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

/**
 * @author KevinCyndaquil
 * Representa a una persona general, con sus atributos más escenciales.
 */

@Getter
@Setter
public class Personas {
    private Integer id_persona;
    private String rfc;
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private String no_cuenta;

    public Personas(Integer id_persona,String nombre, String apellido_p) {
        this.id_persona = id_persona;
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.no_cuenta = no_cuenta;
    }

    public Personas(String nombre, String apellido_p) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.no_cuenta = no_cuenta;
    }
}
