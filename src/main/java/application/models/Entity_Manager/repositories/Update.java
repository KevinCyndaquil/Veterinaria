package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

/**
 * An interface that provides a method for update an entry on DB, through an entity class.
 * @param <M> the type of object will be updated.
 */
public interface Update <M extends Entity> {

    /**
     * Update an object in database.
     * @param model the object with the values to be updated.
     * @return true if the query has been successfully, else, return false.
     * @throws SQLException if there's an error with the query.
     */
    Boolean updateById(@NotNull M model) throws SQLException;
}
