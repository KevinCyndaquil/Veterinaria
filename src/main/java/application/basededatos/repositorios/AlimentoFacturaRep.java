package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Read;
import application.modelos.entregas.ArticuloFactura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoFacturaRep implements
        Read<Integer, List<ArticuloFactura>> {
    @Override
    public List<ArticuloFactura> read(Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length < 1)
            return null;

        try (Postgres postgres = new Postgres();
             CallableStatement call = postgres.getConnection().prepareCall(
                     "{ ? = CALL obtalimentos_factura(?, 1) }")) {
            postgres.getConnection().setAutoCommit(false); //evitamos el cierre de cursores con commit

            //especificamos el tipo de dato que vamos a obtener del CallableStatement
            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setInt(2, id[0]);
            //ejecutamos el procedimiento
            call.execute();

            List<ArticuloFactura> list = new ArrayList<>();
            AlimentoRep alimentoRep = new AlimentoRep();

            //obtenemos la salida del objeto CallableStatement y lo casteamos a ResultSet
            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {
                while (resultSet.next()) {
                    list.add(new ArticuloFactura(
                            alimentoRep.read(new Integer[]{resultSet.getInt("id_articulo_proveedor")}),
                            resultSet.getInt("cantidad")));
                }
            }

            return list;
        }
    }
}
