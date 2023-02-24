package application.basededatos.interfaces;

import java.sql.SQLException;

/**
 * Implementa una interfaz o modelo para leer un registro de una base de datos de postgresql.
 * @param <Key> el tipo de dato de la llave primaria.
 * @param <Table> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Read<Key, Table> {

    /**
     * Lee un registro dentro de la tabla en la base de datos, devolviendo según su key primaria.
     * @param key la key primaria del elemento a buscar.
     * @return el objeto encontrado, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table read(Key key) throws SQLException;
}
