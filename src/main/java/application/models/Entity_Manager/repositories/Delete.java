package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

/**
 * An interface that provides a method for delete an entry on DB, through an entity class.
 * @param <M> the type of object will be deleted.
 */
public interface Delete <M extends Entity> {

    /**
     * This method delete all coincidences with the object provides.
     * @param model the object with the values to be deleted.
     * @return true if the query has been successfully, else, return false.
     * @throws SQLException if there's an error with the query.
     */
    Boolean delete(@NotNull M model) throws SQLException;


    /**
     * This method delete a unique coincidence with the object provides.
     * @param model the object with the primary keys to found and to be deleted.
     * @return true if the query has been successfully, else, return false.
     * @throws SQLException if there's an error with the query.
     */
    Boolean deleteById(@NotNull M model) throws SQLException;
}
