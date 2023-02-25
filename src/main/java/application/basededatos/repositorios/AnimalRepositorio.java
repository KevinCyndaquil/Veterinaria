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

public class AnimalRepositorio implements Create<Animal>, FullRead<Integer, Animal>, Update<Animal>, Hide<Integer, Animal> {
    private static AnimalRepositorio instance;

    public static AnimalRepositorio getInstance() {
        return instance = (instance == null) ? new AnimalRepositorio() : instance;
    }

    @Override
    public String create(Animal animal) {
        if (animal == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO animales VALUES (default, ?, default)")) {

            statement.setString(1, animal.getNombre());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + animal;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Animal hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE animales SET activo = NOT activo WHERE id_animal = ?")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Animal(
                        llave,
                        resultSet.getString(2)
                );
            return null;
        }
    }

    @Override
    public Animal read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM animales WHERE id_animal = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Animal(
                        llave,
                        resultSet.getString(2)
                );
            return null;
        }
    }

    @Override
    public List<Animal> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM animales WHERE activo = true ORDER BY nombre")) {

            List <Animal> list = new ArrayList<>();

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
    public Animal update(Animal animal) throws SQLException {
        if (animal == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE animales SET " +
                        "nombre = ? " +
                "WHERE id_animal = ? AND activo = true")) {

            statement.setString(1, animal.getNombre());
            statement.setInt(2, animal.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return animal;
            return null;
        }
    }
}
