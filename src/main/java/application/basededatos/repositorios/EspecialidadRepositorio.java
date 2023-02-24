package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.FullRead;
import application.modelos.entidades.Especialidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EspecialidadRepositorio implements Create<Especialidad>, FullRead<Integer, Especialidad>, Hide<Integer, Especialidad> {
    private static EspecialidadRepositorio instance;

    public static EspecialidadRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, EspecialidadRepositorio::new);
    }
    @Override
    public String create(Especialidad especialidad) {
        if (especialidad == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO especialidades VALUES (default, ?, default)")) {

            statement.setString(1, especialidad.getNombre());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + especialidad;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Especialidad hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE especialidades SET activo = NOT activo WHERE id_especialidad = ?")) {

            statement.setInt(2, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Especialidad(
                        llave,
                        resultSet.getString(2)
                );
            return null;
        }
    }

    @Override
    public Especialidad read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM especialidades WHERE id_especialidad = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Especialidad(
                        llave,
                        resultSet.getString(2)
                );
            return null;
        }
    }

    @Override
    public List<Especialidad> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM especialidades WHERE activo = true ORDER BY nombre")) {

            List <Especialidad> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Especialidad(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }

            return list;
        }
    }
}
