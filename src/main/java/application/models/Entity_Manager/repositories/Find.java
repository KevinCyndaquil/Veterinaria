package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

/**
 * An interface that provides a method for read a table on DB, through an entity class.
 * @param <M> the type of object will be updated.
 */
public interface Find <M extends Entity> {


    /**
     * Read on table in database checking the attributes in an object. The query is a OR WHERE clause, then,
     * The method returns a lot of objects.
     * @param model the object with values to be found.
     * @return a list of object found.
     * @throws SQLException if there was an SQL error.
     */
    List<Object> find(M model) throws SQLException;


    /**
     * Read on table in database checking the primary key attributes in an object. The query only put in where
     * clause the primary keys, hence, only return one object.
     * @param model the object with attributes to be found.
     * @return the object found.
     * @throws SQLException if there was an SQL error.
     */
    Object findById(@NotNull M model) throws SQLException;

}
