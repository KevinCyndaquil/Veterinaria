package application.database.repository;

import application.models.Entity_Manager.repositories.*;
import application.models.detalles.DetalleFactura;
import application.models.finanzas.Articulos;
import application.models.finanzas.FacturasProveedor;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;

public class FacturaRepository extends Repository<FacturasProveedor> {
    public FacturaRepository(GetConn conn) {
        super(conn);
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

    public FacturasProveedor findByIdAsTable(@NotNull FacturasProveedor facturaConId) throws SQLException, IndexOutOfBoundsException{
        var factura = (FacturasProveedor) super.findById(facturaConId);

        try (Connection connection = conn.get();
             CallableStatement call = connection.prepareCall(
                     "{? = CALL obtArticulos_factura(?)}")) {

            connection.setAutoCommit(false);

            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setInt(2, factura.getId_factura());
            call.execute();

            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {
                while (resultSet.next()) {
                    Articulos articulo = new Articulos(
                            resultSet.getInt("id_articulo"),
                            factura.getProveedor(),
                            resultSet.getString("nombre"),
                            resultSet.getBigDecimal("monto_compra"),
                            resultSet.getString("descripcion"));
                    articulo.setTipo(resultSet.getString("tipo"));

                    DetalleFactura detalleFactura = new DetalleFactura(
                            articulo,
                            facturaConId.getId_factura(),
                            resultSet.getInt("cns"),
                            resultSet.getInt("cantidad"));

                    factura.agregarArticulo(detalleFactura);
                }

            }
            connection.setAutoCommit(true);
            return factura;
        }
    }

    public void findArticles(@NotNull FacturasProveedor factura) throws SQLException {
        factura.eliminarArticulos();

        Find<DetalleFactura> find = new Repository<>(conn);
        find.find(new DetalleFactura(factura.getId_factura())).stream()
                .map(DetalleFactura.class::cast)
                .forEach(factura::agregarArticulo);
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

    public Boolean updateDetalleArticulos(@NotNull FacturasProveedor factura) throws SQLException {
        Update<DetalleFactura> update = new Repository<>(conn);

        for (DetalleFactura detalleFactura : factura.getDetalleArticulos()) {
            update.updateById(detalleFactura);
        }

        findArticles(factura);

        return true;
    }
}
