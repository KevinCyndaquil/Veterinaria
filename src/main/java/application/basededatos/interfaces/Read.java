package application.basededatos.interfaces;

import java.sql.SQLException;

/**
 * Implementa una interfaz o modelo para leer un registro de una base de datos de postgresql.
 * @param <Llave> el tipo de dato de la llave primaria.
 * @param <Tabla> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Read<Llave, Tabla> {

    /**
     * Lee un registro dentro de la tabla en la base de datos según sus identificadores en la base de datos,
     * devolviendo el registro encontrado.
     * @param id            las ids del elemento a buscar.
     * @return              el objeto encontrado, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Tabla read(Llave[] id) throws SQLException;
}
