package application.basededatos.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementa una interfaz o modelo para los CRUD de una base de datos de postgresql.
 * @param <Key> el tipo de dato de la llave primaria.
 * @param <Table> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface PostgresDAOI<Key, Table> {

    /**
     * Inserta un objeto como un registro dentro de una tabla en la base de datos.
     * @param table el objeto a insertar.
     * @return el resultado de la operación.
     */
    String create(Table table);

    /**
     * Lee un registro dentro de la tabla en la base de datos, devolviendo según su llave primaria.
     * @param key la llave primaria del elemento a buscar.
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

    /**
     * Actualiza un registro dentro de la tabla en la base de datos, según un objeto del mismo tipo.
     * @param table un objeto con los datos a actualizar y la matrícula del registro a cambiar.
     * @return el objeto dado por parámetro en caso de ser exitoso. Null en caso de no encontrar resultados.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */
    Table update(Table table) throws SQLException;

    /**
     * Cambia el estatus de un registro como false -invisible para las demás consultas- dentro de la tabla en la base de datos, según un objeto del mismo tipo.
     * @param key la llave primaria del registro a borrar.
     * @return el objeto que ha sido ocultado de la base de datos, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */
    Table lock(Key key) throws SQLException;

    /**
     * Elimina permanentemente un registro dentro de la base de datos, según una llave.
     * @param key la llave del registro a ser borrado.
     * @return el registro eliminado, en caso de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */
    Table delete(Key key) throws SQLException;
}
