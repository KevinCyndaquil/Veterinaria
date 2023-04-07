package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.ArticuloProveedor;
import application.modelos.entregas.ArticuloFactura;
import application.modelos.entregas.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRep implements
        Create<Factura>,
        ReadFull<Integer, Factura>,
        Hide<Integer>,
        Update<Factura> {
    @Override
    public String create(Factura factura) {
        if (factura == null)
            return "ALIMENTO DOESN'T EXIST";

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrfactura_proveedor(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    factura.getFecha_factura(),
                    factura.getProveedor().getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + factura;
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
                     "UPDATE facturas_proveedor SET activo = NOT activo WHERE id_factura = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Factura read(Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM facturas_proveedor WHERE id_factura = ? AND activo = TRUE")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer[] id_art_pro = new Integer[]{resultSet.getInt(1)};

                ProveedorRep proRep = new ProveedorRep();
                List<Read<Integer, List<ArticuloFactura>>> art_rep = List.of(
                        new AlimentoFacturaRep(),
                        new ProductoFacturaRep(),
                        new MedicamentoFacturaRep());

                Factura factura = new Factura(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        proRep.read(new Integer[]{resultSet.getInt(4)}));

                art_rep.forEach(ar -> {
                    try { ar.read(id_art_pro); }
                    catch (SQLException ex) { throw new RuntimeException(); }
                });

                return factura;
            }
            return null;
        }
    }

    @Override
    public List<Factura> readAll() throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM facturas_proveedor ORDER BY fecha_factura")){

            List<Factura> facturas = new ArrayList<>();
            ProveedorRep proRep = new ProveedorRep();
            List<Read<Integer, List<ArticuloFactura>>> art_rep = List.of(
                    new AlimentoFacturaRep(),
                    new ProductoFacturaRep(),
                    new MedicamentoFacturaRep());

            while (resultSet.next()) {
                Integer[] id_art_pro = new Integer[]{resultSet.getInt(5)};

                Factura factura = new Factura(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        proRep.read(new Integer[]{resultSet.getInt(4)}));

                art_rep.forEach(ar -> {
                    try { ar.read(id_art_pro); }
                    catch (SQLException ex) { throw new RuntimeException(); }
                });

                facturas.add(factura);
            }

            return facturas;
        }
    }

    @Override
    public Factura update(Factura factura) throws SQLException {
        if (factura == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT actfactura_proveedor(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    factura.getFecha_factura(),
                    factura.getProveedor().getId(),
                    factura.getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return factura;
            return null;
        }
    }
}
