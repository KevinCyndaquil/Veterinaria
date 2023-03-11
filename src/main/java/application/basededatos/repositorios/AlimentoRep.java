package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.Alimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoRep implements
        Create<Alimento>,
        ReadFull<Integer, Alimento>,
        Update<Alimento>,
        Hide<Integer> {
    @Override
    public String create (Alimento alimento) {
        if (alimento == null)
            return "ALIMENTO DOESN'T EXIST";

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                "SELECT agrAlimento(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    alimento.getNombre(),
                    alimento.getMonto(),
                    alimento.getGramaje(),
                    alimento.getDescripcion()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + alimento;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public String hide (Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "UPDATE alimentos SET activo = NOT activo WHERE id_alimento = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Alimento read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                "SELECT * FROM alimentos WHERE id_alimento = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Alimento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(5),
                        resultSet.getDouble(4)
                );
            return null;
        }
    }

    @Override
    public List<Alimento> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM alimentos WHERE activo = true ORDER BY nombre")) {

            List<Alimento> list = new ArrayList<>();

            if (resultSet.next())
                list.add(new Alimento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(5),
                        resultSet.getDouble(4)
                ));
            return list;
        }
    }

    @Override
    public Alimento update (Alimento alimento) throws SQLException {
        if (alimento == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                "UPDATE alimentos SET " +
                        "nombre = ?, " +
                        "monto = ?, " +
                        "gramaje = ?, " +
                        "descripcion = ? " +
                        "WHERE id_alimento = ? AND activo = true")) {

            statement.setString(1, alimento.getNombre());
            statement.setDouble(2, alimento.getMonto());
            statement.setDouble(3, alimento.getGramaje());
            statement.setString(4, alimento.getDescripcion());
            statement.setInt(5, alimento.getId());

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return alimento;
            return null;
        }
    }
}
