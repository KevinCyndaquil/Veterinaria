package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlimentoStockRepositorio implements Create <AlimentoStock>, ReadList<Integer, LocalDate, AlimentoStock>, ReadAll<AlimentoStock>, Update<AlimentoStock>, Delete<AlimentoStock> {
    private static AlimentoStockRepositorio instance;

    public static AlimentoStockRepositorio getInstance() {
        return instance = (instance == null) ? new AlimentoStockRepositorio() : instance;
    }
    @Override
    public String create(AlimentoStock alimento_stock) {
        if (alimento_stock == null) 
            return null;
        
        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO alimentos_stock VALUES (?, ?, ?)")) {
            
            statement.setDate(1, Date.valueOf(alimento_stock.getLlave()));
            statement.setInt(2, alimento_stock.getCantidad());
            statement.setInt(3, (int) alimento_stock.getAlimento().getLlave());
            
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY " + alimento_stock;
            return "SQL MISSING " + alimento_stock;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public AlimentoStock delete(AlimentoStock alimento_stock) throws SQLException {
        if (alimento_stock == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "DELETE FROM alimentos_stock WHERE id_alimento = ? AND caducidad = ?")) {

            statement.setInt(1, (int) alimento_stock.getAlimento().getLlave());
            statement.setDate(2, Date.valueOf(alimento_stock.getLlave()));

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                return alimento_stock;
            return null;
        }
    }

    @Override
    public List<AlimentoStock> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM alimentos_stock")) {

            List <AlimentoStock> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new AlimentoStock(
                        resultSet.getDate(1).toLocalDate(),
                        resultSet.getInt(2),
                        AlimentoRepositorio.getInstance().read(resultSet.getInt(3))
                ));
            }

            return list;
        }
    }

    @Override
    public AlimentoStock read(Integer llave_alimento, LocalDate caducidad) throws SQLException {
        if (llave_alimento == null || caducidad == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                     "SELECT * FROM alimentos_stock WHERE id_alimento = ? AND caducidad = ?")) {

            statement.setInt(1, llave_alimento);
            statement.setDate(2, Date.valueOf(caducidad));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                 return new AlimentoStock(
                        resultSet.getDate(1).toLocalDate(),
                        resultSet.getInt(2),
                        AlimentoRepositorio.getInstance().read(resultSet.getInt(3))
                );
            return null;
        }
    }

    @Override
    public AlimentoStock update(AlimentoStock alimento_stock) throws SQLException {
        if (alimento_stock == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE alimentos_stock SET cantidad = ? WHERE id_alimento = ? AND caducidad = ?")) {

            statement.setInt(1, alimento_stock.getCantidad());
            statement.setInt(2, (int) alimento_stock.getAlimento().getLlave());
            statement.setDate(3, Date.valueOf(alimento_stock.getLlave()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return alimento_stock;
            return null;
        }
    }
}
