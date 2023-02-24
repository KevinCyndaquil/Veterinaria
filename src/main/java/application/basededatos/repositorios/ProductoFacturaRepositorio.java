package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.ReadAll;
import application.basededatos.interfaces.ReadList;
import application.modelos.entregas.Factura;
import application.modelos.entregas.ProductoFactura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoFacturaRepositorio implements Create<ProductoFactura>, ReadList<Integer, Integer, ProductoFactura>, ReadAll<ProductoFactura> {
    private static ProductoFacturaRepositorio instance;

    public static ProductoFacturaRepositorio getInstance() {
        return instance = (instance == null) ? new ProductoFacturaRepositorio() : instance;
    }

    @Override
    public String create(ProductoFactura producto_factura) {
        if (producto_factura == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO productos_factura VALUES (?, ?, ?, ?)")) {

            statement.setInt(1, producto_factura.getCantidad());
            statement.setDouble(2, producto_factura.getMonto_total());
            statement.setInt(3, (int) producto_factura.getProducto().getLlave());
            statement.setInt(4, producto_factura.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY " + producto_factura;
            return "SQL MISSING: " + producto_factura;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        } catch (RuntimeException ex) {
            return "SUB SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public List<ProductoFactura> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM productos_factura")) {

            List<ProductoFactura> list = new ArrayList<>();

            while (resultSet.next()) {
                Factura factura = FacturaRepositorio.getInstance().read(resultSet.getInt(4));

                list.add(new ProductoFactura(
                        factura.getLlave(),
                        factura.getFecha_factura(),
                        factura.getMonto_total(),
                        factura.getProveedor(),
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        ProductoRepositorio.getInstance().read(resultSet.getInt(3))
                ));
            }

            return list;
        }
    }

    @Override
    public ProductoFactura read(Integer llave_factura, Integer llave_medicamento) throws SQLException {
        if (llave_factura == null || llave_medicamento == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM productos_factura WHERE id_factura = ? AND id_producto = ?")) {

            statement.setInt(1, llave_factura);
            statement.setInt(2, llave_medicamento);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Factura factura = FacturaRepositorio.getInstance().read(llave_factura);

                return new ProductoFactura(
                        factura.getLlave(),
                        factura.getFecha_factura(),
                        factura.getMonto_total(),
                        factura.getProveedor(),
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        ProductoRepositorio.getInstance().read(resultSet.getInt(3))
                );
            }
            return null;
        }
    }
}
