package application.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author KevinCyndaquil
 * @version 1.0
 * Es una clase que se conecta a postgres.
 */

public class Postgres {
    private final String url = "jdbc:postgresql://localhost:5433/veterinaria";
    private final String user = "postgres";
    private final String password = "qw6xdg7sB!";

    private static Connection connection;

    /**
     * Constructor llama al método connectTo.
     */

    public Postgres() {
        connectTo();
    }

    /**
     * Se conecta a postgres llamando al constructor de la clase.
     * @return Regresa la conexión en caso de ser exitosa, si no, regresa null.
     */

    public static Connection getConnection() {
        new Postgres();

        return connection;
    }

    /**
     * Se conecta a postgres, en caso de no halla el driver de postgres o de existir un error de sql, tira una RunTimeException;
     * en caso contrario, se conecta a postgres.
     */

    public void connectTo() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = (connection == null) ? DriverManager.getConnection(url, user, password) : connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver de postgresql no instalado.");
        } catch (SQLException e) {
            throw new RuntimeException("SQL ERROR: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Postgres();

        System.out.println("Connection with postgresql successfully");
    }


}
