package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Read;
import application.basededatos.interfaces.Update;
import application.modelos.finanzas.Nomina;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominaRep implements
        Create<Nomina>,
        Read<Integer, List<Nomina>>,
        Update<Nomina> {
    @Override
    public String create(Nomina nomina) {
        if (nomina == null)
            return "NOMINA DOESN'T EXIST";

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrdetalle_nomina(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[] {
                    nomina.getFecha_inicio(),
                    nomina.getFecha_fin(),
                    nomina.getTotal_horas(),
                    nomina.getTotal_bono(),
                    nomina.getEmpleado().getId()
            });

            statement.setArray(1, array);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0)
                return "SQL SUCCESSFULLY: " + rowInserted;
            return "SQL MISSING";
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public List<Nomina> read(Integer[] id) throws SQLException {
        if (id == null || id.length < 1)
            return null;

        try (Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT * FROM detalles_nomina WHERE id_empleado = ?")) {

            statement.setInt(1, id[0]);
            ResultSet resultSet = statement.executeQuery();

            List<Nomina> list = new ArrayList<>();
            EmpleadosRep empRep = new EmpleadosRep();

            while (resultSet.next()) {
                list.add(new Nomina(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        resultSet.getDouble(5),
                        empRep.read(new Integer[]{resultSet.getInt(6)})));
            }
            return list;
        }
    }

    @Override
    public Nomina update(Nomina nomina) throws SQLException {
        if (nomina == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT actdetalle_nomina(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[] {
                    nomina.getFecha_inicio(),
                    nomina.getFecha_fin(),
                    nomina.getTotal_horas(),
                    nomina.getTotal_bono(),
                    nomina.getEmpleado().getId(),
                    nomina.getId()
            });

            statement.setArray(1, array);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return nomina;
            return null;
        }
    }
}
