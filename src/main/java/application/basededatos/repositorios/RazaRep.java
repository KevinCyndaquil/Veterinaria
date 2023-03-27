package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Raza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RazaRep implements
        Create<Raza>,
        ReadFull<Integer, Raza>,
        Hide<Integer>,
        Update<Raza> {

    private AnimalRep animalRep;
    @Override
    public String create (Raza raza) {
        if (raza == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrRaza(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    raza.getNombre(),
                    raza.getTotal_adopcion(),
                    raza.getAnimal().getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + raza;
        }  catch (SQLException ex) {
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
                     "UPDATE razas SET activo = NOT activo WHERE id_raza = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Raza read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM razas WHERE id_raza = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                animalRep = new AnimalRep();

                return new Raza(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        animalRep.read(new Integer[]{resultSet.getInt(4)})
                );
            }
            return null;
        }
    }

    @Override
    public List<Raza> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM razas WHERE activo = true ORDER BY nombre")) {

            List<Raza> list = new ArrayList<>();

            if (resultSet.next()) {
                animalRep = new AnimalRep();

                list.add(new Raza(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        animalRep.read(new Integer[]{resultSet.getInt(4)})
                ));
            }
            return list;
        }
    }

    @Override
    public Raza update (Raza raza) throws SQLException {
        if (raza == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actraza(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    raza.getNombre(),
                    raza.getTotal_adopcion(),
                    raza.getAnimal().getId(),
                    raza.getId()
            });

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return raza;
            return null;
        }
    }
}
