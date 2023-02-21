package application.basededatos.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ReadDaoI<Key, Table> {

    /**
     * Lee un registro dentro de la tabla en la base de datos, devolviendo según su key primaria.
     * @param key la key primaria del elemento a buscar.
     * @return el objeto encontrado, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table read(Key key) throws SQLException;

    /**
     * Lee todas las filas dentro de la tabla en la base de datos, devolviendo una lista de los objetos contenidos en la tabla.
     * @return una lista de objetos con los registros de la tabla, de no hallar nada, estará vacía.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    List<Table> readAll() throws SQLException;
}
