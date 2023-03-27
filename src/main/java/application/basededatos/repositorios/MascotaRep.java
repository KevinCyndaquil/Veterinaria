package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Mascota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaRep implements
        Create<Mascota>,
        ReadFull<Integer, Mascota>,
        Hide<Integer>,
        Update<Mascota> {

    PropietarioRep propietarioRep;
    RazaRep razaRep;

    @Override
    public String create (Mascota mascota) {
        if (mascota == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrmascota(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    mascota.getNombre(),
                    mascota.getFecha_nacimiento(),
                    mascota.getSexo(),
                    mascota.getPropietario().getId(),
                    mascota.getRaza().getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + mascota;
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
                     "UPDATE mascotas SET activo = NOT activo WHERE id_mascota = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Mascota read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM mascotas WHERE id_mascota = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                propietarioRep = new PropietarioRep();
                razaRep = new RazaRep();

                return new Mascota(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getString(4),
                        propietarioRep.read(new Integer[]{resultSet.getInt(5)}),
                        razaRep.read(new Integer[]{resultSet.getInt(6)})
                        );
            }
            return null;
        }
    }

    @Override
    public List<Mascota> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM mascotas WHERE activo = true ORDER BY nombre")) {

            List<Mascota> list = new ArrayList<>();

            if (resultSet.next()) {
                propietarioRep = new PropietarioRep();
                razaRep = new RazaRep();

                list.add(new Mascota(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getString(4),
                        propietarioRep.read(new Integer[]{resultSet.getInt(5)}),
                        razaRep.read(new Integer[]{resultSet.getInt(6)})
                ));
            }
            return list;
        }
    }

    @Override
    public Mascota update (Mascota mascota) throws SQLException {
        if (mascota == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actmascota(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    mascota.getNombre(),
                    mascota.getFecha_nacimiento(),
                    mascota.getSexo(),
                    mascota.getPropietario().getId(),
                    mascota.getRaza().getId(),
                    mascota.getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return mascota;
            return null;
        }
    }
}
