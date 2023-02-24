package application.basededatos.interfaces;

import java.sql.SQLException;

public interface ReadList <Key, SubordinateKey, Table> {
    /**
     * Lee un registro dentro de la tabla en la base de datos, devolviendo según su key primaria.
     * @param key la key primaria del elemento a buscar.
     * @param subordinateKey la llave primara del elemento subordinado.
     * @return el objeto encontrado, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table read(Key key, SubordinateKey subordinateKey) throws SQLException;
}
