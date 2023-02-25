package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.FullRead;
import application.basededatos.interfaces.Update;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropietarioRepositorio implements Create<Propietario>, FullRead<String, Propietario>, Update<Propietario>, Hide<String, Propietario> {
    private static PropietarioRepositorio instance;

    public static PropietarioRepositorio getInstance() {
        return instance = (instance == null) ? new PropietarioRepositorio() : instance;
    }

    @Override
    public String create(Propietario propietario) {
        if (propietario == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO propietarios VALUES (default, ?, ?, ?, default)")) {

            statement.setString(1, propietario.getNombre());
            statement.setString(2, propietario.getApellido_paterno());
            statement.setString(3, propietario.getApellido_materno());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + propietario;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Propietario hide(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE propietarios SET activo = NOT activo WHERE rfc_dueño = ?")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Propietario(
                        llave,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            return null;
        }
    }

    @Override
    public Propietario read(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM propietarios WHERE rfc_dueño = ? AND activo = true")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Propietario(
                        llave,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            return null;
        }
    }

    @Override
    public List<Propietario> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM propietarios WHERE activo = true ORDER BY nombre")) {

            List <Propietario> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Propietario(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }

            return list;
        }
    }

    @Override
    public Propietario update(Propietario propietario) throws SQLException {
        if (propietario == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE propietarios SET " +
                        "nombre = ?, " +
                        "apellido_p = ?, " +
                        "apellido_m = ? " +
                        "WHERE rfc_dueño = ? AND activo = true")) {

            statement.setString(1, propietario.getNombre());
            statement.setString(2, propietario.getApellido_paterno());
            statement.setString(3, propietario.getApellido_materno());
            statement.setString(4, propietario.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return propietario;
            return null;
        }
    }
}

