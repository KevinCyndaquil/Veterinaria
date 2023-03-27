package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Producto;
import application.modelos.entidades.TiposProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRep implements
        Create<Producto>,
        ReadFull<Integer, Producto>,
        Hide<Integer>,
        Update<Producto> {
    @Override
    public String create (Producto producto) {
        if (producto == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrarticulo_proveedor(?, 2)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    producto.getNombre(),
                    producto.getMontoCompra(),
                    producto.getProveedor().getId(),
                    producto.getTipo().getDescripcion()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + producto;
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
                     "UPDATE articulos_proveedor SET activo = NOT activo WHERE id_articulo_proveedor = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Producto read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM articulos_proveedor INNER JOIN productos p " +
                             "ON id_articulo_proveedor = p.id_articulo_producto " +
                             "WHERE id_articulo_producto = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ProveedorRep proRep = new ProveedorRep();
                return new Producto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        proRep.read(new Integer[]{resultSet.getInt(4)}),
                        TiposProducto.getValueFrom(resultSet.getString(7))
                );
            }
            return null;
        }
    }

    @Override
    public List<Producto> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM articulos_proveedor INNER JOIN productos p " +
                             "ON id_articulo_proveedor = p.id_articulo_producto " +
                             "WHERE activo = true ORDER BY nombre")) {

            List<Producto> list = new ArrayList<>();

            while (resultSet.next()) {
                ProveedorRep proRep = new ProveedorRep();
                list.add(new Producto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        proRep.read(new Integer[]{resultSet.getInt(4)}),
                        TiposProducto.getValueFrom(resultSet.getString(7))));
            }
            return list;
        }
    }

    @Override
    public Producto update (Producto producto) throws SQLException {
        if (producto == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actarticulo_proveedor(?, 2)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    producto.getNombre(),
                    producto.getMontoCompra(),
                    producto.getProveedor().getId(),
                    producto.getTipo().getDescripcion(),
                    producto.getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return producto;
            return null;
        }
    }
}
