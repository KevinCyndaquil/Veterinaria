package application.models.database.interfaces;

import application.Main;

import java.sql.SQLException;
import java.util.Map;

/**
 * Implementa una interfaz o modelo para desactivar registros de una base de datos de postgresql. Accede al
 * atributo activo de las tablas e invierte su valor.
 * @param <Llave> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Hide<Entity> {
    /**
     * Cambia el estatus de ser visualizado de un registro según sus identificadores en la base de datos.
     * @param id            son los identificadores del registro a ser ocultado en la base de datos.
     * @return              un mensaje con el resultado de la operación.
     * @throws SQLException en caso de haber ocurrido un problema con la consulta.
     */

    String hide(Entity entity);

    boolean isHidden(Entity entity);

    Map<Entity, Boolean> areHidden(Iterable<Entity> entities);
}
