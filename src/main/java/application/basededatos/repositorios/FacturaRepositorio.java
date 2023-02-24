package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.FullRead;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.Update;
import application.modelos.entregas.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacturaRepositorio  implements Create<Factura>, FullRead<Integer, Factura>, Hide<Integer, Factura>, Update<Factura> {
    private static FacturaRepositorio instance;

    public static FacturaRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, FacturaRepositorio::new);
    }
    @Override
    public String create(Factura factura) {
        if (factura == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO facturas VALUES (default, ?, ?, ?, default)")) {

            statement.setDate(1, Date.valueOf(factura.getFecha_factura()));
            statement.setDouble(2, factura.getMonto_total());
            statement.setInt(3, (int) factura.getProveedor().getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + factura;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Factura hide(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE facturas SET activo = NOT activo WHERE id_factura = ?")) {

            statement.setInt(2, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Factura(
                        llave,
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDouble(3),
                        ProveedorRepositorio.getInstance().read(resultSet.getInt(4))
                );
            return null;
        }
    }

    @Override
    public Factura read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM facturas WHERE id_factura = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Factura(
                        llave,
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDouble(3),
                        ProveedorRepositorio.getInstance().read(resultSet.getInt(4))
                );
            return null;
        }
    }

    @Override
    public List<Factura> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM facturas WHERE activo = true ORDER BY nombre")) {

            List<Factura> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Factura(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDouble(3),
                        ProveedorRepositorio.getInstance().read(resultSet.getInt(4))
                ));
            }

            return list;
        }
    }

    @Override
    public Factura update(Factura factura) throws SQLException {
        if (factura == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE facturas SET " +
                        "fecha_factura = ?, " +
                        "monto_total = ?, " +
                        "id_proveedor = ? " +
                        "WHERE id_factura = ? AND activo = true")) {

            statement.setDate(1, Date.valueOf(factura.getFecha_factura()));
            statement.setDouble(2, factura.getMonto_total());
            statement.setInt(3, (int) factura.getProveedor().getLlave());
            statement.setInt(4, factura.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return factura;
            return null;
        }
    }
}
