package application.basededatos.interfaces;

/**
 * Implementa una interfaz o modelo para insertar registros a una base de datos de postgresql.
 * @param <Tabla> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Create<Tabla> {

    /**
     * Inserta un objeto como un registro dentro de una tabla en la base de datos.
     * @param tabla el objeto a insertar.
     * @return el resultado de la operación.
     */

    String create(Tabla tabla);
}
