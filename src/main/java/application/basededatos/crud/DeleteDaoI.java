package application.basededatos.crud;

import java.sql.SQLException;

public interface DeleteDaoI <Key, Table> {

    /**
     * Cambia el estatus de un registro como false -invisible para las demás consultas- dentro de la tabla en la base de datos, según un objeto del mismo tipo.
     * @param key la llave primaria del registro a borrar.
     * @return el objeto que ha sido ocultado de la base de datos, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table desactivate(Key key) throws SQLException;

    /**
     * Elimina permanentemente un registro dentro de la base de datos, según una llave.
     * @param key la llave del registro a ser borrado.
     * @return el registro eliminado, en caso de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table delete(Key key) throws SQLException;
}
