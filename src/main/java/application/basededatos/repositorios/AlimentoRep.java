package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.Alimento;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoRep implements
        Create<Alimento>,
        ReadFull<Integer, Alimento>,
        Update<Alimento>,
        Hide<Integer> {
    @Override
    public String create (@NotNull Alimento alimento) {
        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                "SELECT agrarticulo_proveedor(?, 1)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    alimento.getNombre(),
                    alimento.getMontoCompra(),
                    alimento.getProveedor().getId(),
                    alimento.getGramaje(),
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + alimento;
        } catch (SQLException ex) {
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
    public Alimento read (Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                "SELECT * FROM articulos_proveedor INNER JOIN alimentos " +
                        "ON id_articulo_proveedor = id_articulo_alimento " +
                        "WHERE id_articulo_alimento = ? AND activo = true")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ProveedorRep proveedorRep = new ProveedorRep();

                return new Alimento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        proveedorRep.read(new Integer[]{resultSet.getInt(4)}),
                        resultSet.getDouble(7)
                );
            }
            return null;
        }
    }

    @Override
    public List<Alimento> readAll () throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM articulos_proveedor INNER JOIN alimentos " +
                             "ON id_articulo_proveedor = id_articulo_alimento " +
                             "WHERE activo = true")) {

            List<Alimento> list = new ArrayList<>();
            ProveedorRep proveedorRep = new ProveedorRep();

            while (resultSet.next()) {
                list.add(new Alimento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        proveedorRep.read(new Integer[]{resultSet.getInt(4)}),
                        resultSet.getDouble(7)
                ));
            }
            return list;
        }
    }

    @Override
    public Alimento update (Alimento alimento) throws SQLException {
        if (alimento == null)
            return null;

        try(Postgres postgres = new Postgres();
            PreparedStatement statement = postgres.getConnection().prepareStatement(
                "SELECT actarticulo_proveedor(?, 1)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    alimento.getNombre(),
                    alimento.getMontoCompra(),
                    alimento.getProveedor().getId(),
                    alimento.getId(),
                    alimento.getGramaje(),
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                if (resultSet.getInt(1) > 0)
                    return alimento;
            return null;
        }
    }
}
