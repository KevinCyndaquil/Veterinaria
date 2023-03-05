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
    private static String user;
    private static String password;

    private Connection connection; //la conexión

    /**
     * Asigna un usuario y una contraseña para conectarse a la base de datos.
     * @param user un usuario valida.
     * @param password su respectiva contraseña.
     */

    public void setUser(String user, String password) {
        Postgres.user = user;
        Postgres.password = password;
    }

    /**
     * Se conecta a postgres llamando al constructor de la clase.
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

    private void connectTo() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = (connection == null) ? DriverManager.getConnection(url, (user == null) ? "postgres" : user, (password == null) ? "qw6xdg7sB!" : password) : connection;
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
