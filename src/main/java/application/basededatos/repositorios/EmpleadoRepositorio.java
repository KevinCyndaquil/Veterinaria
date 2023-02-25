package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.FullRead;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositorio implements Create<Empleado>, FullRead<String, Empleado>, Update<Empleado>, Hide<String, Empleado> {
    private static EmpleadoRepositorio instance;

    public static EmpleadoRepositorio getInstance() {
        return instance = (instance == null) ? new EmpleadoRepositorio() : instance;
    }

    @Override
    public String create(Empleado empleado) {
        if (empleado == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO empleados VALUES (default, ?, ?, ?, ?, ?, ?, default)")) {

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido_paterno());
            statement.setString(3, empleado.getApellido_materno());
            statement.setDate(4, Date.valueOf(empleado.getFecha_inicial()));
            statement.setTime(5, Time.valueOf(empleado.getJornada_fin()));
            statement.setTime(6, Time.valueOf(empleado.getJornada_fin()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + empleado;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Empleado hide(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE empleados SET activo = NOT activo WHERE rfc_empleado = ?")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Mascota.Empleado(
                        llave,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getTime(6).toLocalTime(),
                        resultSet.getTime(7).toLocalTime()
                );
            return null;
        }
    }

    @Override
    public Empleado read(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM empleados WHERE rfc_empleado = ? AND activo = true")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Mascota.Empleado(
                        llave,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getTime(6).toLocalTime(),
                        resultSet.getTime(7).toLocalTime()
                );
            return null;
        }
    }

    @Override
    public List<Empleado> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM empleados WHERE activo = true ORDER BY nombre")) {

            List <Empleado> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Mascota.Empleado(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getTime(6).toLocalTime(),
                        resultSet.getTime(7).toLocalTime()
                ));
            }

            return list;
        }
    }

    @Override
    public Empleado update(Empleado empleado) throws SQLException {
        if (empleado == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE empleados SET " +
                        "nombre = ?, " +
                        "apellido_p = ?, " +
                        "apellido_m = ?, " +
                        "fecha_ini = ?, " +
                        "jor_ini = ?, " +
                        "jor_fin = ? " +
                "WHERE rfc_empleado = ? AND activo = true")) {

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido_paterno());
            statement.setString(3, empleado.getApellido_materno());
            statement.setDate(4, Date.valueOf(empleado.getFecha_inicial()));
            statement.setTime(5, Time.valueOf(empleado.getJornada_fin()));
            statement.setTime(6, Time.valueOf(empleado.getJornada_fin()));
            statement.setString(7, empleado.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return empleado;
            return null;
        }
    }
}
