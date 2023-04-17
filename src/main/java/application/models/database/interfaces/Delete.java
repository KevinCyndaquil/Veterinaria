package application.models.database.interfaces;

import java.sql.SQLException;

/**
 * Implementa una interfaz o modelo para borrar definitivamente registros de una base de datos de postgresql.
 * @param <Llave> el tipo de dato del identificador de la tabla que corresponde a un modelo en el sistema.
 */

public interface Delete<Entity> {
    /**
     * Elimina permanentemente un registro dentro de la base de datos, según un conjunto de llaves.
     * @param id            es el identificador del registro a ser eliminado.
     * @return              un mensaje con el resultado de la operación.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    String delete(Entity entity) throws SQLException;
}
