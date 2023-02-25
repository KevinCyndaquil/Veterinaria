package application.modelos;

/**
 * Es un modelo que representa una tabla genérica de la base de datos de la veterinaria.
 * @param <Key> el tipo de dato de la id primaria de la tabla.
 */

public class Tabla <Key>{
    private Key id;

    /**
     * Constructor vacío, id nula.
     */
    public Tabla() {
        id = null;
    }

    /**
     * Constructor con un identificador definido.
     * @param id la id primaria del registro.
     */
    public Tabla(Key id) {
        this.id = id;
    }
}
