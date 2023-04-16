package application.database.repositorios;

import application.database.Postgres;
import application.database.interfaces.Read;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoFacturaRep implements
        Read<Integer, List<ArticulosFactura>> {
    @Override
    public List<ArticulosFactura> read(Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length < 1)
            return null;

        try (Postgres postgres = new Postgres();
             CallableStatement call = postgres.getConnection().prepareCall(
                     "{ ? = CALL obtalimentos_factura(?, 3) }")) {
            postgres.getConnection().setAutoCommit(false); //evitamos el cierre de cursores con commit

            //especificamos el tipo de dato que vamos a obtener del CallableStatement
            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setInt(2, id[0]);
            //ejecutamos el procedimiento
            call.execute();

            List<ArticulosFactura> list = new ArrayList<>();
            MedicamentoRep medicamentoRep = new MedicamentoRep();

            //obtenemos la salida del objeto CallableStatement y lo casteamos a ResultSet
            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {
                while (resultSet.next()) {
                    list.add(new ArticulosFactura(
                            medicamentoRep.read(new Integer[]{resultSet.getInt("id_articulo_proveedor")}),
                            resultSet.getInt("cantidad")));
                }
            }

            return list;
        }
    }
}
