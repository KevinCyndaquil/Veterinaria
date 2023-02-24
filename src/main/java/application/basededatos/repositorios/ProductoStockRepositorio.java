package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.inventario.ProductoStock;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoStockRepositorio  implements Create<ProductoStock>, ReadList<Integer, LocalDate, ProductoStock>, ReadAll<ProductoStock>, Update<ProductoStock>, Delete<ProductoStock> {
    private static ProductoStockRepositorio instance;

    public static ProductoStockRepositorio getInstance() {
        return instance = (instance == null) ? new ProductoStockRepositorio() : instance;
    }
    @Override
    public String create(ProductoStock producto_stock) {
        if (producto_stock == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO productos_stock VALUES (?, ?, ?)")) {

            statement.setDate(1, Date.valueOf(producto_stock.getLlave()));
            statement.setInt(2, producto_stock.getCantidad());
            statement.setInt(3, (int) producto_stock.getProducto().getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY " + producto_stock;
            return "SQL MISSING " + producto_stock;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public ProductoStock delete(ProductoStock producto_stock) throws SQLException {
        if (producto_stock == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "DELETE FROM productos_stock WHERE id_producto = ? AND caducidad = ?")) {

            statement.setInt(1, (int) producto_stock.getProducto().getLlave());
            statement.setDate(2, Date.valueOf(producto_stock.getLlave()));

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                return producto_stock;
            return null;
        }
    }

    @Override
    public List<ProductoStock> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM productos_stock")) {

            List <ProductoStock> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new ProductoStock(
                        resultSet.getDate(1).toLocalDate(),
                        resultSet.getInt(2),
                        ProductoRepositorio.getInstance().read(resultSet.getInt(3))
                ));
            }

            return list;
        }
    }

    @Override
    public ProductoStock read(Integer llave_alimento, LocalDate caducidad) throws SQLException {
        if (llave_alimento == null || caducidad == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM productos_stock WHERE id_alimento = ? AND caducidad = ?")) {

            statement.setInt(1, llave_alimento);
            statement.setDate(2, Date.valueOf(caducidad));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new ProductoStock(
                        resultSet.getDate(1).toLocalDate(),
                        resultSet.getInt(2),
                        ProductoRepositorio.getInstance().read(resultSet.getInt(3))
                );
            return null;
        }
    }

    @Override
    public ProductoStock update(ProductoStock producto_stock) throws SQLException {
        if (producto_stock == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE productos_stock SET cantidad = ? WHERE id_alimento = ? AND caducidad = ?")) {

            statement.setInt(1, producto_stock.getCantidad());
            statement.setInt(2, (int) producto_stock.getProducto().getLlave());
            statement.setDate(3, Date.valueOf(producto_stock.getLlave()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return producto_stock;
            return null;
        }
    }
}
