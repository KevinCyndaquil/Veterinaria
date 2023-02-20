package application.basededatos.crud;

import application.basededatos.Postgres;
import application.modelos.entidades.Alimento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImpAlimentosCRUD implements PostgresDAOI<Integer, Alimento> {
    private static ImpAlimentosCRUD instance;

    public static ImpAlimentosCRUD getInstance() {
        return instance = (instance == null) ? new ImpAlimentosCRUD() : instance;
    }

    @Override
    public String create(Alimento alimento) {
        if (alimento == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO alimentos VALUES (default, ?, ?, ?, ?, default)")) {

            statement.setString(2, alimento.getNombre());
            statement.setDouble(3, alimento.getCosto());
            statement.setDouble(4, alimento.getGramaje());
            statement.setString(5, alimento.getDescripcion());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + alimento;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Alimento read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM alimentos WHERE idal = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Alimento(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
        }

        return null;
    }

    @Override
    public List<Alimento> readAll() throws SQLException {

        try (Statement statement = Postgres.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM alimentos WHERE activo = true ORDER BY nombre")) {

            List <Alimento> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Alimento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }

            return list;
        }
    }

    @Override
    public Alimento update(Alimento alimento) throws SQLException {

        if (alimento == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE alimentos SET nombre = ?, costo = ?, gramaje = ?, descripcion = ? WHERE idal = ? AND activo = true")) {

            statement.setString(1, alimento.getNombre());
            statement.setDouble(2, alimento.getCosto());
            statement.setDouble(3, alimento.getGramaje());
            statement.setString(4, alimento.getDescripcion());
            statement.setInt(4, alimento.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return alimento;
        }

        return null;
    }

    @Override
    public Alimento desactivate(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE alimentos SET activo = false WHERE idal = ?")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Alimento(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
        }

        return null;
    }
}
