package application.basededatos.interfaces;

import java.sql.SQLException;

/**
 * Implementa una interfaz o modelo para desactivar registros de una base de datos de postgresql.
 * Accede al atributo activo de las tablas e invierte su valor.
 * @param <Key> el tipo de dato de la llave primaria.
 * @param <Table> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Hide<Key, Table> {

    /**
     * Cambia el estatus de un registro como false -oculto para las demás consultas- dentro de la tabla en la base de datos, según un objeto del mismo tipo.
     * @param key la llave primaria del registro a ocultar o a hacer visible.
     * @return el objeto que ha sido ocultado de la base de datos, de no hallarlo, devolverá null.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    Table hide(Key key) throws SQLException;
}
