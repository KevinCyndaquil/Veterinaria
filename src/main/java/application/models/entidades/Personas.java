package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import lombok.Getter;
import lombok.Setter;

/**
 * @author KevinCyndaquil
 * Representa a una persona general, con sus atributos más escenciales.
 */

@Getter
@Setter
@SqlEntity("personas")
public class Personas implements Entity {
    @SqlAttribute
    @SqlKey(type = SqlKey.SERIAL)
    private Integer id_persona;
    @SqlAttribute
    private String rfc;
    @SqlAttribute
    private String nombre;
    @SqlAttribute
    private String apellido_p;
    @SqlAttribute
    private String apellido_m;
    @SqlAttribute
    private String no_cuenta;

    @SqlInstance
    public Personas(Integer id_persona, String rfc, String nombre, String apellido_p, String apellido_m, String no_cuenta) {
        this.id_persona = id_persona;
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.no_cuenta = no_cuenta;
    }

    public Personas(String rfc, String nombre, String apellido_p, String apellido_m, String no_cuenta) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.no_cuenta = no_cuenta;
    }

    public Personas() {

    }

    public Personas(Integer id_persona) {
        this.id_persona = id_persona;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
