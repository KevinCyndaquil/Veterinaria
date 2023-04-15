package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Propietarios extends Personas {

    public Propietarios(Integer id_persona, String nombre, String apellido_p) {
        super(id_persona, nombre, apellido_p);

    }

    public Propietarios(String nombre, String apellido_p) {
        super(nombre, apellido_p);
    }
}
