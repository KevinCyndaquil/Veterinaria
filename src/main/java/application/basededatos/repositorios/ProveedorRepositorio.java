package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.FullRead;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.Update;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProveedorRepositorio implements Create<Proveedor>, FullRead<Integer, Proveedor>, Hide<Integer, Proveedor>, Update<Proveedor> {
    private static ProveedorRepositorio instance;

    public static ProveedorRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, ProveedorRepositorio::new);
    }
    @Override
    public String create(Proveedor proveedor) {
        if (proveedor == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO proveedores VALUES (default, ?, ?, ?, ?, default)")) {

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getDescripcion());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + proveedor;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Proveedor hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE proveedores SET activo = NOT activo WHERE id_proveedor = ?")) {

            statement.setInt(2, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Proveedor(
                        llave,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            return null;
        }
    }

    @Override
    public Proveedor read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM proveedores WHERE id_proveedor = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Proveedor(
                        llave,
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
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM proveedores WHERE activo = true ORDER BY nombre")) {

            List <Proveedor> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Proveedor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }

            return list;
        }
    }

    @Override
    public Proveedor update(Proveedor proveedor) throws SQLException {
        if (proveedor == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE proveedores SET " +
                        "nombre = ?, " +
                        "direccion = ?, " +
                        "telefono = ?, " +
                        "descripcion = ? " +
                        "WHERE id_proveedor = ? AND activo = true")) {

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getDescripcion());
            statement.setInt(5, proveedor.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return proveedor;
            return null;
        }
    }
}
