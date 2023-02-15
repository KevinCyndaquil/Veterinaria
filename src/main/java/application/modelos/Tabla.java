package application.modelos;

/**
 * Es un modelo que representa una tabla generica de la base de datos de la veterinaria.
 * @param <Key> el tipo de dato de la llave primaria de la tabla.
 */

public class Tabla <Key>{
    private Key llave;

    /**
     * Constructor vacio, llave nula.
     */

    public Tabla() {
        llave = null;
    }

    /**
     * Constructor con una llave definida.
     * @param key la llave primaria del registro.
     */

    public Tabla(Key key) {
        this.llave = key;
    }

    public Key getLlave() {
        return llave;
    }

    public void setLlave(Key llave) {
        this.llave = llave;
    }
}
