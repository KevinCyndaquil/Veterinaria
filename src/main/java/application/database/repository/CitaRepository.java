package application.database.repository;

import application.models.Entity_Manager.repositories.GetConn;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Citas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaRepository extends Repository<Citas> {
    public CitaRepository(GetConn conn) {
        super(conn);
    }

    public List<Object[]> obtenerDetalle(int id_mascota) throws SQLException {
        List<Object[]> data = new ArrayList<>();

        try (Connection connection = conn.get();
             CallableStatement call = connection.prepareCall(
                     "{? = CALL obtDetalle_cita(?)}")) {

            connection.setAutoCommit(false);

            call.registerOutParameter(1, Types.REF_CURSOR);
            call.setInt(2, id_mascota);
            call.execute();

            try (ResultSet resultSet = call.getObject(1, ResultSet.class)) {


                while (resultSet.next()) {
                    data.add(new Object[]{
                            resultSet.getString("mascota"),
                            resultSet.getString("articulo"),
                            resultSet.getBigDecimal("costo"),
                            resultSet.getString("proveedor"),
                            resultSet.getString("cantidad"),
                            resultSet.getString("tipo_articulo")
                    });
                }

            }
            connection.setAutoCommit(true);
            System.out.println(data);
            return data;
        }
    }
}
