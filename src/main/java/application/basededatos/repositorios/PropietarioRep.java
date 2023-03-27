package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.Propietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropietarioRep implements
        Create<Propietario>,
        ReadFull<Integer, Propietario>,
        Hide<Integer>,
        Update<Propietario> {
    @Override
    public String create (Propietario propietario) {
        if (propietario == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrpropietario(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    propietario.getRfc(),
                    propietario.getNombre(),
                    propietario.getApellido_paterno(),
                    propietario.getApellido_materno(),
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + propietario;
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
                     "UPDATE personas SET activo = NOT activo WHERE id_persona = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Propietario read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM personas INNER JOIN propietarios p " +
                             "ON id_persona = p.id_propietario " +
                             "WHERE id_propietario = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Propietario(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            return null;
        }
    }

    @Override
    public List<Propietario> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM personas INNER JOIN propietarios p ON " +
                             "id_persona = p.id_propietario " +
                             "WHERE activo = true ORDER BY nombre")) {

            List<Propietario> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Propietario(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }
            return list;
        }
    }

    @Override
    public Propietario update (Propietario propietario) throws SQLException {
        if (propietario == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actpropietario(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    propietario.getRfc(),
                    propietario.getNombre(),
                    propietario.getApellido_paterno(),
                    propietario.getApellido_materno(),
                    propietario.getId()
            });

            ResultSet resultSet = statement.getResultSet();

            statement.setArray(1, array);

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return propietario;
            return null;
        }
    }
}
