package application.database.repository;

import application.models.Entity_Manager.repositories.*;
import application.models.detalles.DetalleFactura;
import application.models.finanzas.Articulos;
import application.models.finanzas.FacturasProveedor;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository extends Repository<FacturasProveedor> {
    public FacturaRepository(GetConn conn) {
        super(conn);
    }

    private void findArticles(@NotNull FacturasProveedor factura) throws SQLException {
        Find<DetalleFactura> find = new Repository<>(conn);

        factura.eliminarArticulos();
        find.find(new DetalleFactura(factura.getId_factura())).stream()
                .map(DetalleFactura.class::cast)
                .forEach(factura::agregarArticulo);
    }

    @Override
    public List<Object> find(FacturasProveedor factura) throws SQLException {
        List<Object> facturas = super.find(factura);

        for (Object obj : facturas) {
            FacturasProveedor f = (FacturasProveedor) obj;
            findArticles(f);
        }

        return facturas;
    }

    @Override
    public Object findById(@NotNull FacturasProveedor facturaConId) throws SQLException {
        FacturasProveedor factura = (FacturasProveedor) super.findById(facturaConId);
        findArticles(factura);

        return factura;
    }

    @Override
    public Object save(@NotNull FacturasProveedor factura) throws SQLException {
        super.save(factura);

        Save<DetalleFactura> save = new Repository<>(conn);

        for (DetalleFactura detalleFactura : factura.getDetalleArticulos()) {
            save.save(detalleFactura);
        }

        return factura.getId_factura();
    }

    public List<DetalleFactura> findArticlesAsTable(int id_factura, int id_proveedor) throws SQLException, IndexOutOfBoundsException{
        List<DetalleFactura> detalleArticulos = new ArrayList<>();
        Find<Articulos> findArticles = new Repository<>(conn);

        try (Connection connection = conn.get();
             CallableStatement call = connection.prepareCall(
                     "{? = CALL obtArticulos_factura(?, ?)}")) {

            connection.setAutoCommit(false);

            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setInt(2, id_factura);
            call.setInt(3, id_proveedor);
            call.execute();

            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {
                while (resultSet.next()) {
                    Articulos articulo = (Articulos) findArticles.findById(
                            new Articulos(resultSet.getInt("id_articulo")));
                    articulo.setTipo(resultSet.getString("tipo"));

                    detalleArticulos.add(new DetalleFactura(
                            articulo,
                            id_factura,
                            resultSet.getInt("cns"),
                            resultSet.getInt("cantidad")));
                }

            }
            connection.setAutoCommit(true);
            return detalleArticulos;
        }
    }

    public Boolean insertDetalleArticulos(@NotNull List<DetalleFactura> detalleArticulos)
            throws SQLException {
        boolean result = false;
        Save<DetalleFactura> save = new Repository<>(conn);

        for (DetalleFactura d : detalleArticulos) {
            result = save.save(d) != null;
        }

        return result;
    }

    public Boolean updateDetalleArticulos(@NotNull List<DetalleFactura> detalleArticulos)
            throws SQLException {
        boolean result = false;
        Update<DetalleFactura> update = new Repository<>(conn);

        for (DetalleFactura d : detalleArticulos) {
            result = update.updateById(d);
        }

        return result;
    }
}
