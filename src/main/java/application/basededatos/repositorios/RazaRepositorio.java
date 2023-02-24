package application.basededatos.repositorios;

import application.modelos.entidades.Raza;
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

public class RazaRepositorio implements Create<Raza>, FullRead<Integer, Raza>, Update<Raza>, Hide<Integer, Raza> {
    private static RazaRepositorio instance;

    public static RazaRepositorio getInstance() {
        return instance = (instance == null) ? new RazaRepositorio() : instance;
    }

    @Override
    public String create(Raza raza) {
        if (raza == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO razas VALUES (default, ?, ?, ?, default)")) {

            statement.setString(1, raza.getNombre());
            statement.setInt(2, raza.getTotal_adopcion());
            statement.setInt(3, (int) raza.getAnimal().getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + raza;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Raza hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE razas SET activo = NOT activo WHERE id_raza = ?")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Raza(
                        llave,
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        AnimalRepositorio.getInstance().read(resultSet.getInt(4))
                );
            return null;
        }
    }

    @Override
    public Raza read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM razas WHERE id_raza = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Raza(
                        llave,
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        AnimalRepositorio.getInstance().read(resultSet.getInt(4))
                );
            return null;
        }
    }

    @Override
    public List<Raza> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM razas WHERE activo = true ORDER BY nombre")) {

            List <Raza> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Raza(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        AnimalRepositorio.getInstance().read(resultSet.getInt(4))
                ));
            }

            return list;
        }
    }

    @Override
    public Raza update(Raza raza) throws SQLException {
        if (raza == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE razas SET " +
                        "nombre = ?, " +
                        "total_adopción = ?, " +
                        "id_animal = ? " +
                        "WHERE id_raza = ? AND activo = true")) {

            statement.setString(1, raza.getNombre());
            statement.setInt(2, raza.getTotal_adopcion());
            statement.setInt(3, (int) raza.getAnimal().getLlave());
            statement.setInt(4, raza.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return raza;
            return null;
        }
    }
}
