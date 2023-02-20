package application.basededatos.crud;

import java.sql.SQLException;

public interface UpdateDaoI <Table> {

    /**
     * Actualiza un registro dentro de la tabla en la base de datos, según un objeto del mismo tipo.
     * @param table un objeto con los datos a actualizar y la matrícula del registro a cambiar.
     * @return el objeto dado por parámetro en caso de ser exitoso. Null en caso de no encontrar resultados.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table update(Table table) throws SQLException;

}
