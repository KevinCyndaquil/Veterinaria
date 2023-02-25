package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NominaRepositorio implements Create<Nomina>, ReadList<Integer, String, Nomina>, ReadAll<Nomina>, Hide<Integer, Nomina>, Update<Nomina> {
    private static NominaRepositorio instance;

    public static NominaRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, NominaRepositorio::new);
    }
    @Override
    public String create(Nomina nomina) {
        if (nomina == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO nominas VALUES (default, ?, ?, ?, ?, ?, default)")) {

            statement.setDate(1, Date.valueOf(nomina.getFecha()));
            statement.setInt(2, nomina.getTotal_horas());
            statement.setDouble(3, nomina.getSalario());
            statement.setDouble(4, nomina.getBono());
            statement.setInt(5, (int) nomina.getEmpleado().getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + nomina;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Nomina hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE nominas SET activo = NOT activo WHERE id_nomina = ?")) {

            statement.setInt(2, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Nomina(
                        llave,
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5),
                        EmpleadoRepositorio.getInstance().read(resultSet.getString(6))
                );
            return null;
        }
    }

    @Override
    public Nomina read(Integer llave_nomina, String llave_empleado) throws SQLException {
        if (llave_nomina == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM nominas WHERE id_nomina = ? AND rfc_empleado = ? AND activo = true")) {

            statement.setInt(1, llave_nomina);
            statement.setString(2, llave_empleado);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Nomina(
                        llave_nomina,
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5),
                        EmpleadoRepositorio.getInstance().read(resultSet.getString(6))
                );
            return null;
        }
    }

    @Override
    public List<Nomina> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM nominas WHERE activo = true ORDER BY rfc_empleado")) {

            List <Nomina> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Nomina(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5),
                        EmpleadoRepositorio.getInstance().read(resultSet.getString(6))
                ));
            }

            return list;
        }
    }

    @Override
    public Nomina update(Nomina nomina) throws SQLException {
        if (nomina == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE nominas SET " +
                        "fecha_factura = ?, " +
                        "total_horas = ?, " +
                        "total_bono = ?, " +
                        "rfc_empleado = ? " +
                        "WHERE id_nomina = ? AND activo = true")) {

            statement.setDate(1, Date.valueOf(nomina.getFecha()));
            statement.setInt(2, nomina.getTotal_horas());
            statement.setDouble(3, nomina.getSalario());
            statement.setDouble(4, nomina.getBono());
            statement.setInt(5, (int) nomina.getEmpleado().getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return nomina;
            return null;
        }
    }
}
