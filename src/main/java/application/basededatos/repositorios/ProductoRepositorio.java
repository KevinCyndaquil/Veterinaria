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
import java.util.Objects;

public class ProductoRepositorio implements Create<Producto>, FullRead<Integer, Producto>, Update<Producto>, Hide<Integer, Producto> {
    private static ProductoRepositorio instance;

    public static ProductoRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, ProductoRepositorio::new);
    }

    @Override
    public String create(Producto producto) {
        if (producto == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO productos VALUES (default, ?, ?, ?, ?, default)")) {

            statement.setInt(1, producto.getTipo().getLlave());
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getCosto());
            statement.setString(4, producto.getDescripcion());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + producto;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Producto read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM alimentos WHERE id_alimento = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Producto(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        TipoProductoRepositorio.getInstance().read(resultSet.getInt(5))
                );
        }

        return null;
    }

    @Override
    public List<Producto> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM productos WHERE activo = true ORDER BY nombre")) {

            List <Producto> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Producto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        TipoProductoRepositorio.getInstance().read(resultSet.getInt(5))
                ));
            }

            return list;
        }
    }

    @Override
    public Producto update(Producto producto) throws SQLException {
        if (producto == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE productos SET " +
                        "id_tipo_producto = ?, " +
                        "nombre = ?, " +
                        "monto = ?, " +
                        "descripcion = ? " +
                "WHERE id_producto = ? AND activo = true")) {

            statement.setInt(1, producto.getTipo().getLlave());
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getCosto());
            statement.setString(4, producto.getDescripcion());
            statement.setInt(5, producto.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return producto;
        }

        return null;
    }

    @Override
    public Producto hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE productos SET activo = NOT activo WHERE id_producto = ?")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Producto(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        TipoProductoRepositorio.getInstance().read(resultSet.getInt(5))
                );

            return null;
        }
    }
}
