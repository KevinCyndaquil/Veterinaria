package application.models.Entity_Manager.repositories;

import java.sql.Connection;

/**
 * This is a functional interface for any method to get a database connection. The form to access to database
 * is different for each client.
 */

@FunctionalInterface
public interface GetConn {

    /**
     * The method returns the Database Connection.
     * @return the connection.
     */

    Connection get ();
}
