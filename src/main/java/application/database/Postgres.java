package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author KevinCyndaquil
 * @version 1.0
 * Es una clase que se conecta a postgres.
 */

public class Postgres extends ConnectDB {
    public Postgres() {
        super("jdbc:postgresql://localhost:5433/veterinaria");
    }

    /**
     * Asigna un usuario y una contraseña para conectarse a la base de datos.
     *
     * @param user     un usuario válido.
     * @param password su respectiva contraseña.
     */

    public void setUser(String user, String password) {
        Postgres.user = user;
        Postgres.password = password;
    }

    /**
     * Se conecta a postgres llamando al constructor de la clase.
     *
     * @return Regresa la conexión en caso de ser exitosa, si no, regresa null.
     */

    public Connection getConnection() {
        connectTo();

        return connection;
    }

    /**
     * Se conecta a postgres, en caso de no halla el driver de postgres o de existir un error de sql, tira una RunTimeException;
     * necesita un usuario y una contraseña, puede ser asignada con el método setUser, de no asignarse, se usará un usuario genérico.
     * En caso contrario, se conecta a postgres.
     */

    public void connectTo() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = (connection == null) ? DriverManager.getConnection(url, (user == null) ? "kevincyndaquil" : user, (password == null) ? "qw6xdg7sB!" : password) : connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver de postgresql no instalado.");
        } catch (SQLException e) {
            throw new RuntimeException("SQL ERROR: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Postgres postgres = new Postgres()) {
            postgres.setUser("admin", "0804");
            postgres.connectTo();
            System.out.println("Connection with postgresql successfully");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Cerramos la conexión de PostgreSQL usada en un bloque try-with-catch.
     *
     * @throws SQLException regresa una excepción SQL en caso de ocurrir un error con el cierre de la conexión.
     */
    @Override
    public void close() throws SQLException {
        this.connection.close();
    }

    @Override
    public Connection get() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, (user == null) ? "kevincyndaquil" : user, (password == null) ? "qw6xdg7sB!" : password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver de postgresql no instalado.");
        } catch (SQLException e) {
            throw new RuntimeException("SQL ERROR: " + e.getMessage());
        }
        return connection;
    }
}
