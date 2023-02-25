package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.ReadAll;
import application.basededatos.interfaces.ReadList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoFacturaRepositorio implements Create<MedicamentoFactura>, ReadList<Integer, Integer, MedicamentoFactura>, ReadAll<MedicamentoFactura> {
    private static MedicamentoFacturaRepositorio instance;

    public static MedicamentoFacturaRepositorio getInstance() {
        return instance = (instance == null) ? new MedicamentoFacturaRepositorio() : instance;
    }

    @Override
    public String create(MedicamentoFactura medicamento_factura) {
        if (medicamento_factura == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO medicamentos_factura VALUES (?, ?, ?, ?)")) {

            statement.setInt(1, medicamento_factura.getCantidad());
            statement.setDouble(2, medicamento_factura.getMonto_total());
            statement.setInt(3, (int) medicamento_factura.getMedicamento().getLlave());
            statement.setInt(4, medicamento_factura.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY " + medicamento_factura;
            return "SQL MISSING: " + medicamento_factura;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        } catch (RuntimeException ex) {
            return "SUB SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public List<MedicamentoFactura> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM medicamentos_factura")) {

            List<MedicamentoFactura> list = new ArrayList<>();

            while (resultSet.next()) {
                Factura factura = FacturaRepositorio.getInstance().read(resultSet.getInt(4));

                list.add(new MedicamentoFactura(
                        factura.getLlave(),
                        factura.getFecha_factura(),
                        factura.getMonto_total(),
                        factura.getProveedor(),
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        MedicamentoRepositorio.getInstance().read(resultSet.getInt(3))
                ));
            }

            return list;
        }
    }

    @Override
    public MedicamentoFactura read(Integer llave_factura, Integer llave_medicamento) throws SQLException {
        if (llave_factura == null || llave_medicamento == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM medicamentos_factura WHERE id_factura = ? AND id_medicamento = ?")) {

            statement.setInt(1, llave_factura);
            statement.setInt(2, llave_medicamento);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Factura factura = FacturaRepositorio.getInstance().read(llave_factura);

                return new MedicamentoFactura(
                        factura.getLlave(),
                        factura.getFecha_factura(),
                        factura.getMonto_total(),
                        factura.getProveedor(),
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        MedicamentoRepositorio.getInstance().read(resultSet.getInt(3))
                );
            }
            return null;
        }
    }
}
