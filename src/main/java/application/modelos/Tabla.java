package application.modelos;

import lombok.Getter;
import lombok.Setter;

/**
 * Es un modelo que representa una tabla genérica de la base de datos de la veterinaria.
 */

public class Tabla {
    @Getter @Setter
    private Integer id;

    /**
     * Constructor vacío, key nula.
     */
    public Tabla() {
        id = null;
    }

    /**
     * Constructor con un identificador definido.
     * @param id la key primaria del registro.
     */
    public Tabla(Integer id) {
        this.id = id;
    }
}
