package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRep implements
        Create<Animal>,
        ReadFull<Integer, Animal>,
        Hide<Integer>,
        Update<Animal> {
    @Override
    public String create (Animal animal) {
        if (animal == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agranimal(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    animal.getNombre()
            });
            
            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + animal;
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
                     "UPDATE animales SET activo = NOT activo WHERE id_animal = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Animal read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM animales WHERE id_animal = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Animal(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            return null;
        }
    }

    @Override
    public List<Animal> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM animales WHERE activo = true ORDER BY nombre")) {

            List<Animal> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Animal(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
            return list;
        }
    }

    @Override
    public Animal update (Animal animal) throws SQLException {
        if (animal == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actanimal(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    animal.getNombre(),
                    animal.getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return animal;
            return null;
        }
    }
}
