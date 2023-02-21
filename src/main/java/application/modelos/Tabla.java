package application.modelos;

/**
 * Es un modelo que representa una tabla genérica de la base de datos de la veterinaria.
 * @param <Key> el tipo de dato de la llave primaria de la tabla.
 */

public class Tabla <Key>{
    private Key llave;
    private String nombre;

    /**
     * Constructor vacío, llave nula.
     */

    public Tabla() {
        llave = null;
        nombre = null;
    }

    /**
     * Constructor con una llave definida.
     *
     * @param key    la llave primaria del registro.
     * @param nombre el nombre que identifica al registro.
     */

    public Tabla(Key key, String nombre) {
        this.llave = key;
        this.nombre = nombre;
    }

    public Key getLlave() {
        return llave;
    }

    public void setLlave(Key llave) {
        this.llave = llave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Tabla<?> tabla = (Tabla<?>) obj;

            return tabla.nombre.equals(nombre) && tabla.getLlave().equals(llave);
        } catch (ClassCastException ex) {
            return false;
        }
    }

}
