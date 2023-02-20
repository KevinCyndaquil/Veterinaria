package application.modelos;

/**
 * Es un modelo que representa una tabla subordinada -una lista- de la base de datos de la veterinaria.
 * @param <Key> el tipo de dato de la llave primaria de la tabla.
 */

public class Lista <Key>{
    private Key llave;

    /**
     * Constructor vacío, llave nula.
     */

    public Lista() {
        llave = null;
    }

    /**
     * Constructor con una llave primaria definida.
     * @param llave el valor de la llave primaria.
     */

    public Lista(Key llave) {
        this.llave = llave;
    }

    public Key getLlave() {
        return llave;
    }

    public void setLlave(Key llave) {
        this.llave = llave;
    }
}
