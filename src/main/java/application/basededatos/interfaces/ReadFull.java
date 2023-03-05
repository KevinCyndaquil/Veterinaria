package application.basededatos.interfaces;

/**
 * Implementa una interfaz o modelo para leer registros de una base de datos de postgresql.
 * Es una herencia de las otras dos interfaces de lectura de registros.
 * @param <Llave> el tipo de dato de la llave primaria.
 * @param <Tabla> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface ReadFull<Llave, Tabla> extends Read<Llave, Tabla>, ReadAll<Tabla> {

}
