package application.basededatos.crud;

import application.basededatos.Postgres;
import application.basededatos.interfaces.CreateDaoI;
import application.basededatos.interfaces.LockDaoI;
import application.basededatos.interfaces.ReadDaoI;
import application.basededatos.interfaces.UpdateDaoI;
import application.modelos.entidades.Medicamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoCRUD  implements CreateDaoI<Medicamento>, ReadDaoI<Integer, Medicamento>, UpdateDaoI<Medicamento>, LockDaoI<Integer, Medicamento> {
    private static AlimentoCRUD instance;

    public static AlimentoCRUD getInstance() {
        return instance = (instance == null) ? new AlimentoCRUD() : instance;
    }

    @Override
    public String create(Medicamento medicamento) {
        if (medicamento == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO medicamentos VALUES (default, ?, ?, ?, ?, ?, default)")) {

            statement.setString(1, medicamento.getNombre());
            statement.setDouble(2, medicamento.getCosto());
            statement.setDouble(3, medicamento.getGramaje());
            statement.setString(4, medicamento.getLaboratorio());
            statement.setString(5, medicamento.getDescripcion());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY: " + resultSet.getInt(1);

            return "SQL MISSING: " + medicamento;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Medicamento read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM medicamentos WHERE id_medicamento = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Medicamento(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        null);
        }

        return null;
    }

    @Override
    public List<Medicamento> readAll() throws SQLException {

        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM medicamentos WHERE activo = true ORDER BY nombre")) {

            List<Medicamento> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Medicamento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        null));
            }

            return list;
        }
    }

    @Override
    public Medicamento update(Medicamento alimento) throws SQLException {

        if (alimento == null)
            return null;

        try(PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE medicamentos SET " +
                        "nombre = ?, " +
                        "monto = ?, " +
                        "gramaje = ?, " +
                        "descripcion = ?, " +
                        "laboratorio = ? " +
                "WHERE id_medicamento = ? AND activo = true")) {

            statement.setString(1, alimento.getNombre());
            statement.setDouble(2, alimento.getCosto());
            statement.setDouble(3, alimento.getGramaje());
            statement.setString(4, alimento.getLaboratorio());
            statement.setString(5, alimento.getDescripcion());
            statement.setInt(6, alimento.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return alimento;
        }

        return null;
    }

    @Override
    public Medicamento lock(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE medicamentos SET activo = false WHERE id_medicamento = ?")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Medicamento(
                        llave,
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        null);
            return null;
        }
    }
}
