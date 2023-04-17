package application.models.database.interfaces;

import application.Main;

import java.sql.SQLException;
import java.util.Map;

/**
 * Implementa una interfaz o modelo para desactivar registros de una base de datos de postgresql. Accede al
 * atributo activo de las tablas e invierte su valor.
 * @param <Entity>> el tipo de la tabla que corresponde a un modelo en el sistema.
 */

public interface Hide<Entity> {

    String hide(Entity entity);

    boolean isHidden(Entity entity);

    Map<Entity, Boolean> areHidden(Iterable<Entity> entities);
}
