package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.FullRead;
import application.basededatos.interfaces.Update;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaRepositorio implements Create<Mascota>, FullRead<String, Mascota>, Update<Mascota>, Hide<String, Mascota> {
    private static MascotaRepositorio instance;

    public static MascotaRepositorio getInstance() {
        return instance = (instance == null) ? new MascotaRepositorio() : instance;
    }

    @Override
    public String create(Mascota mascota) {
        if (mascota == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO mascotas VALUES (default, ?, ?, ?, ?, ?, ?, default)")) {

            statement.setString(1, mascota.getNombre());
            statement.setDate(2, Date.valueOf(mascota.getFecha_nacimiento()));
            statement.setInt(3, mascota.getEdad());
            statement.setBoolean(4, mascota.getSexo());
            statement.setString(5, (String) mascota.getPropietario().getLlave());
            statement.setInt(6, (int) mascota.getRaza().getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + mascota;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Mascota hide(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE mascotas SET activo = NOT activo WHERE curp_mascota = ?")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Mascota(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        PropietarioRepositorio.getInstance().read(resultSet.getString(6)),
                        RazaRepositorio.getInstance().read(resultSet.getInt(7))
                );
            return null;
        }
    }

    @Override
    public Mascota read(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM mascotas WHERE curp_mascota = ? AND activo = true")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Mascota(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        PropietarioRepositorio.getInstance().read(resultSet.getString(6)),
                        RazaRepositorio.getInstance().read(resultSet.getInt(7))
                );
            return null;
        }
    }

    @Override
    public List<Mascota> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM mascotas WHERE activo = true ORDER BY nombre")) {

            List <Mascota> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Mascota(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        PropietarioRepositorio.getInstance().read(resultSet.getString(6)),
                        RazaRepositorio.getInstance().read(resultSet.getInt(7))
                ));
            }

            return list;
        }
    }

    @Override
    public Mascota update(Mascota mascota) throws SQLException {
        if (mascota == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE mascotas SET " +
                        "nombre = ?, " +
                        "fecha_nacimiento = ?, " +
                        "edad = ?, " +
                        "sexo = ?, " +
                        "rfc_dueño = ?, " +
                        "id_raza = ? " +
                "WHERE curp_mascota = ? AND activo = true")) {

            statement.setString(1, mascota.getNombre());
            statement.setDate(2, Date.valueOf(mascota.getFecha_nacimiento()));
            statement.setInt(3, mascota.getEdad());
            statement.setBoolean(4, mascota.getSexo());
            statement.setString(5, (String) mascota.getPropietario().getLlave());
            statement.setInt(6, (int) mascota.getRaza().getLlave());
            statement.setString(7, mascota.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return mascota;
            return null;
        }
    }
}
