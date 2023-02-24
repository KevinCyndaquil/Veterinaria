package application.basededatos.interfaces;

/**
 * Implementa una interfaz o modelo para los CRUD de una base de datos de postgresql.
 * Es una clase que junta todos los modelos posibles.
 * @param <Key> el tipo de dato de la llave primaria.
 * @param <Table> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public abstract class PostgresCRUD<Key, Table> implements Create<Table>, FullRead<Key, Table>, Update<Table>, Hide<Key, Table>, Delete<Table> {

}
