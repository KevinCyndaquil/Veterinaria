package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Empleado;
import application.modelos.entidades.Puestos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosRep implements
        Create<Empleado>,
        ReadFull<Integer, Empleado>,
        Hide<Integer>,
        Update<Empleado> {

    @Override
    public String create (Empleado empleado) {
        if (empleado == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrempleado(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    empleado.getRfc(),
                    empleado.getNombre(),
                    empleado.getApellido_paterno(),
                    empleado.getApellido_materno(),
                    empleado.getNo_cuenta(),
                    empleado.getFecha_inicio(),
                    empleado.getHora_inicio(),
                    empleado.getHora_fin(),
                    empleado.getPuesto().getSalario(),
                    empleado.getPuesto().getPuesto()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + empleado;
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
    public Empleado read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM personas INNER JOIN empleados " +
                             "ON id_persona = id_empleado " +
                             "WHERE id_empleado = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Empleado(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getTime(7).toLocalTime(),
                        resultSet.getTime(8).toLocalTime(),
                        Puestos.valueOf(resultSet.getString(9))
                        );
            return null;
        }
    }

    @Override
    public List<Empleado> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM personas INNER JOIN empleados e " +
                             "ON personas.id_persona = e.id_empleado " +
                             "WHERE activo = true ORDER BY nombre")) {

            List<Empleado> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Empleado(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getTime(7).toLocalTime(),
                        resultSet.getTime(8).toLocalTime(),
                        Puestos.valueOf(resultSet.getString(9))));
            }
            return list;
        }
    }

    @Override
    public Empleado update (Empleado empleado) throws SQLException {
        if (empleado == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actempleado(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    empleado.getRfc(),
                    empleado.getNombre(),
                    empleado.getApellido_paterno(),
                    empleado.getApellido_materno(),
                    empleado.getFecha_inicio(),
                    empleado.getHora_inicio(),
                    empleado.getHora_fin(),
                    empleado.getPuesto().getSalario(),
                    empleado.getPuesto().getPuesto(),
                    empleado.getId()
            });

            ResultSet resultSet = statement.getResultSet();

            statement.setArray(1, array);

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return empleado;
            return null;
        }
    }
}
