package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

/**
 * An interface that provides a method for hide an entry on DB, through an entity class.
 * @param <M> the type of object will be deleted.
 */
public interface Hide <M extends Entity> {

    /**
     * This method hide a unique coincidence with the object provides. The attribute in database must be named
     * 'active'.
     * @param model the object with the primary keys to found and to be deleted.
     * @return true if the query has been successfully, else, return false.
     * @throws SQLException if there's an error with the query.
     */
    Boolean hideById(@NotNull M model) throws SQLException;
}
