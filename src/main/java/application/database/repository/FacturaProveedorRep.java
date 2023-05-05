package application.database.repository;

import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.GetConn;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Proveedores;
import application.models.finanzas.Articulos;
import application.models.finanzas.FacturasProveedor;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FacturaProveedorRep extends Repository<FacturasProveedor> {
    public FacturaProveedorRep(GetConn conn) {
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

    public FacturasProveedor findFacturaWithArticles(@NotNull Date fecha, @NotNull Proveedores proveedor) throws SQLException, IndexOutOfBoundsException{
        FacturasProveedor f = new FacturasProveedor(fecha, proveedor);
        var facturas = super.find(f);
        FacturasProveedor factura = (FacturasProveedor) facturas.get(0);

        proveedor = factura.getProveedor();
        //System.out.println(proveedor.id_proveedor());

        try (Connection connection = conn.get();
             CallableStatement call = connection.prepareCall(
                     "{? = CALL obtarticulos_factura(?, ?)}")) {

            connection.setAutoCommit(false);

            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setDate(2, fecha);
            call.setInt(3, proveedor.id_proveedor());

            call.execute();

            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {
                while (resultSet.next()) {
                    Articulos articulo = new Articulos(
                            resultSet.getInt("id_articulo"),
                            proveedor,
                            resultSet.getString("nombre"),
                            resultSet.getBigDecimal("monto_compra"),
                            resultSet.getString("descripcion"));
                    articulo.setTipo(resultSet.getString("tipo"));

                    factura.agregarArticulo(
                            articulo,
                            resultSet.getInt("cantidad"));
                }

            }
            connection.setAutoCommit(true);
            return factura;
        }
    }

    public void findArticles(@NotNull FacturasProveedor factura) throws SQLException {
        Find<Articulos> findA = new Repository<>(conn);

        try (Connection connection = conn.get();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM detalle_factura WHERE id_factura = ?")) {

            statement.setInt(1, factura.getId_factura());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Articulos articulo = new Articulos(resultSet.getInt("id_articulo"));
                articulo = (Articulos) findA.findById(articulo);
                factura.agregarArticulo(articulo, resultSet.getInt("cantidad"));
            }
        }
    }

    @Override
    public Object save(@NotNull FacturasProveedor factura) throws SQLException {

        super.save(factura);

        try (Connection connection = conn.get();
             CallableStatement call = connection.prepareCall(
                     "CALL agrdetalle_factura(?, ?)")) {

            factura.getArticulos().forEach((a, can) -> {
                try {
                    Array array = connection.createArrayOf("VARCHAR", new Object[]{
                            can,
                            a.getId_articulo(),
                            factura.getId_factura()
                    });

                    call.setArray(1, array);
                    call.registerOutParameter(2, Types.INTEGER);
                    call.execute();

                    System.out.println(call.getInt(2));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            return factura.getId_factura();
        }

    }

    @Override
    public Boolean updateById(@NotNull FacturasProveedor factura) throws SQLException {
        AtomicReference<Boolean> res = new AtomicReference<>(super.updateById(factura));

        try (Connection connection = conn.get();
             CallableStatement call = connection.prepareCall(
                     "CALL actdetalle_factura(?, ?)")) {
            factura.getArticulos().forEach((a, can) -> {
                try {
                    Array array = connection.createArrayOf("VARCHAR", new Object[]{
                            can,
                            a.getId_articulo(),
                            factura.getId_factura(),
                            factura.consultarArticulo(a)
                    });

                    call.setArray(1, array);
                    call.registerOutParameter(2, Types.BOOLEAN);
                    call.execute();

                    System.out.println(call.getInt(2));
                    res.set(call.getBoolean(2));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return res.get();
    }
}
