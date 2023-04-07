package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.ArticuloProveedor;
import application.modelos.entidades.ArticuloVenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloVentaRep implements
        Create<ArticuloVenta>,
        ReadFull<Integer, ArticuloVenta>,
        Update<ArticuloVenta>,
        Hide<Integer> {
    @Override
    public String create(ArticuloVenta articuloVenta) {
        if (articuloVenta == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT agrarticulo_venta(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    articuloVenta.getMontoVenta(),
                    articuloVenta.getDescripcion(),
                    articuloVenta.getStock(),
                    articuloVenta.getArticulo().getId(),
                    articuloVenta.getArticulo().getClass().getSimpleName()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + articuloVenta;
        } catch (SQLException ex) {
            return "SQL ERROR " + ex.getMessage();
        }
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        if (id == null)
            return null;

        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "UPDATE articulos_venta SET activo = NOT activo WHERE id_articulo_venta = ?")) {

            statement.setInt(1, id[0]);

            int rowUpdated = statement.executeUpdate();

            if (rowUpdated > 0)
                return "HIDING SUCCESSFULLY " + id[0];
            return "HIDING MISSING FOR ID " + id[0];
        }
    }

    @Override
    public ArticuloVenta read(Integer[] id) throws SQLException {
        if (id == null)
            return null;
        if (id.length == 0)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT * FROM articulos_venta WHERE id_articulo_venta = ? AND activo = TRUE")) {

            statement.setInt(1, id[0]);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                Integer[] id_art_pro = new Integer[]{resultSet.getInt(5)};
                Read<Integer, ? extends ArticuloProveedor> art_rep;

                switch (tipo) {
                    case "alimento" -> art_rep = new AlimentoRep();
                    case "producto" -> art_rep = new ProductoRep();
                    case "medicamento" -> art_rep = new MedicamentoRep();
                    default -> {
                        return null;
                    }
                }

                return new ArticuloVenta(
                        resultSet.getInt(1),
                        art_rep.read(id_art_pro),
                        resultSet.getDouble(2),
                        resultSet.getInt(4),
                        resultSet.getString(3),
                        tipo);
            }
            return null;
        }
    }

    @Override
    public List<ArticuloVenta> readAll() throws SQLException {
        try (Postgres postgres = new Postgres();
             Statement statement = postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM articulos_venta WHERE activo = TRUE")) {

            List<ArticuloVenta> list = new ArrayList<>();

            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                Integer[] id_art_pro = new Integer[]{resultSet.getInt(5)};
                Read<Integer, ? extends ArticuloProveedor> art_rep;

                switch (tipo) {
                    case "alimento" -> art_rep = new AlimentoRep();
                    case "producto" -> art_rep = new ProductoRep();
                    case "medicamento" -> art_rep = new MedicamentoRep();
                    default -> {
                        return null;
                    }
                }

                list.add(new ArticuloVenta(
                        resultSet.getInt(1),
                        art_rep.read(id_art_pro),
                        resultSet.getDouble(2),
                        resultSet.getInt(4),
                        resultSet.getString(3),
                        resultSet.getString(6)));
            }
            return list;
        }
    }

    @Override
    public ArticuloVenta update(ArticuloVenta articuloVenta) throws SQLException {
        if (articuloVenta == null)
            return null;

        try (Postgres postgres = new Postgres();
             PreparedStatement statement = postgres.getConnection().prepareStatement(
                     "SELECT actarticulo_venta(?)")) {

            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    articuloVenta.getMontoVenta(),
                    articuloVenta.getDescripcion(),
                    articuloVenta.getStock(),
                    articuloVenta.getArticulo().getId(),
                    articuloVenta.getArticulo().getClass().getSimpleName(),
                    articuloVenta.getId()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return articuloVenta;
            return null;
        }
    }
}
