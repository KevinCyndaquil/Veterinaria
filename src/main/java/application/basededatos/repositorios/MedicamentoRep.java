package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Medicamento;
import application.modelos.entidades.ViasMedicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoRep implements
        Create<Medicamento>,
        ReadFull<Integer, Medicamento>,
        Hide<Integer>,
        Update<Medicamento> {
    @Override
    public String create (Medicamento medicamento) {
        if (medicamento == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrarticulo_proveedor(?, 3)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    medicamento.getNombre(),
                    medicamento.getMontoCompra(),
                    medicamento.getProveedor().getId(),
                    medicamento.getGramaje(),
                    medicamento.getLaboratorio(),
                    medicamento.getVia().getVia()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + medicamento;
        }  catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public String hide (Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "UPDATE articulos_proveedor SET activo = NOT activo WHERE id_articulo_proveedor = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public Medicamento read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM articulos_proveedor INNER JOIN medicamentos " +
                             "ON id_articulo_proveedor = id_articulo_medicamento " +
                             "WHERE id_articulo_medicamento = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ProveedorRep proRep = new ProveedorRep();

                return new Medicamento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        proRep.read(new Integer[]{resultSet.getInt(4)}),
                        resultSet.getDouble(7),
                        resultSet.getString(8),
                        ViasMedicamento.getValueFrom(resultSet.getString(9))
                );
            }
            return null;
        }
    }

    @Override
    public List<Medicamento> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM articulos_proveedor INNER JOIN medicamentos " +
                             "ON id_articulo_proveedor = id_articulo_medicamento " +
                             "WHERE activo = true ORDER BY nombre")) {

            List<Medicamento> list = new ArrayList<>();

            while (resultSet.next()) {
                ProveedorRep proRep = new ProveedorRep();

                list.add(new Medicamento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        proRep.read(new Integer[]{resultSet.getInt(4)}),
                        resultSet.getDouble(7),
                        resultSet.getString(8),
                        ViasMedicamento.getValueFrom(resultSet.getString(9))
                ));
            }
            return list;
        }
    }

    @Override
    public Medicamento update (Medicamento medicamento) throws SQLException {
        if (medicamento == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                    "SELECT actarticulo_proveedor(?, 3)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    medicamento.getNombre(),
                    medicamento.getMontoCompra(),
                    medicamento.getProveedor().getId(),
                    medicamento.getGramaje(),
                    medicamento.getLaboratorio(),
                    medicamento.getVia().getVia(),
                    medicamento.getId()
            });

            ResultSet resultSet = statement.getResultSet();

            statement.setArray(1, array);

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return medicamento;
            return null;
        }
    }
}
