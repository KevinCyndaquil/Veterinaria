package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

/**
 * An interface that provides a method for create an entry on DB, through an entity class.
 * @param <M> the type of object will be created.
 */
public interface Save<M extends Entity> {

    /**
     * create an object in database.
     * @param model the object with the values to be created.
     * @return the primary key inserted or generated.
     * @throws SQLException if there's an error with the query.
     */
    Object save(@NotNull M model) throws SQLException;


    /**
     * create a list of objects.
     * @param models the list with entities to be created.
     * @return a list of primary key inserted or generated.
     * @throws SQLException if there's an error with the query.
     */
    Iterable<Object> saveAll(@NotNull Iterable<M> models) throws SQLException;
}
