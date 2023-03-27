package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Read;
import application.modelos.entregas.ArticuloFactura;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProductoFacturaRep implements
        Read<Integer, List<ArticuloFactura>> {
    @Override
    public List<ArticuloFactura> read(Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length < 1)
            return null;

        try (Postgres postgres = new Postgres();
             CallableStatement call = postgres.getConnection().prepareCall(
                     "{ ? = CALL obtalimentos_factura(?, 2) }")) {
            postgres.getConnection().setAutoCommit(false); //evitamos el cierre de cursores con commit

            //especificamos el tipo de dato que vamos a obtener del CallableStatement
            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setInt(2, id[0]);
            //ejecutamos el procedimiento
            call.execute();

            List<ArticuloFactura> list = new ArrayList<>();
            ProductoRep productoRep = new ProductoRep();

            //obtenemos la salida del objeto CallableStatement y lo casteamos a ResultSet
            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {
                while (resultSet.next()) {
                    list.add(new ArticuloFactura(
                            productoRep.read(new Integer[]{resultSet.getInt("id_articulo_proveedor")}),
                            resultSet.getInt("cantidad")));
                }
            }

            return list;
        }
    }
}
