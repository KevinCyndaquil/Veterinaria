package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRep implements
        Create<Proveedor>,
        ReadFull<Integer, Proveedor>,
        Hide<Integer>,
        Update<Proveedor> {
    @Override
    public String create(Proveedor proveedor) {
        if (proveedor == null)
            return "ALIMENTO DOESN'T EXIST";

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrproveedor(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    proveedor.getNombre(),
                    proveedor.getDireccion(),
                    proveedor.getTelefono(),
                    proveedor.getDescripcion()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + proveedor;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "UPDATE proveedores SET activo = NOT activo WHERE id_proveedor = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Proveedor read(Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM proveedores WHERE id_proveedor = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Proveedor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            return null;
        }
    }

    @Override
    public List<Proveedor> readAll() throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM proveedores WHERE activo = true ORDER BY nombre")) {

            List<Proveedor> list = new ArrayList<>();

            if (resultSet.next())
                list.add(new Proveedor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            return list;
        }
    }

    @Override
    public Proveedor update(Proveedor proveedor) throws SQLException {
        if (proveedor == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actproveedor(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    proveedor.getNombre(),
                    proveedor.getDireccion(),
                    proveedor.getTelefono(),
                    proveedor.getDescripcion(),
                    proveedor.getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return proveedor;
            return null;
        }
    }
}
