package application.repositorios.interfaces;

public interface CreateDaoI<Tabla> {

    /**
     * Inserta un objeto como un registro dentro de una tabla en la base de datos.
     * @param tabla el objeto a insertar.
     * @return el resultado de la operación.
     */

    String create(Tabla tabla);
}
