package application.basededatos.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementa una interfaz o modelo para leer todos los registros de una base de datos de postgresql.
 * @param <Table> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface ReadAll<Table> {

    /**
     * Lee todas las filas dentro de la tabla en la base de datos, devolviendo una lista de los objetos contenidos en la tabla.
     * @return una lista de objetos con los registros de la tabla, de no hallar nada, estará vacía.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    List<Table> readAll() throws SQLException;
}
