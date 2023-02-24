package application.basededatos.interfaces;

import java.sql.SQLException;

/**
 * Implementa una interfaz o modelo para borrar definitivamente registros de una base de datos de postgresql.
 * @param <Table> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Delete<Table> {

    /**
     * Elimina permanentemente un registro dentro de la base de datos, según una llave.
     * @param table es el registro a ser eliminado.
     * @return el registro eliminado, en caso de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table delete(Table table) throws SQLException;
}
