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

public class AlimentoFacturaRepositorio implements Create<AlimentoFactura>, ReadList<Integer, Integer, AlimentoFactura>, ReadAll<AlimentoFactura> {
    private static AlimentoFacturaRepositorio instance;

    public static AlimentoFacturaRepositorio getInstance() {
        return instance = (instance == null) ? new AlimentoFacturaRepositorio() : instance;
    }

    @Override
    public String create(AlimentoFactura alimento_factura) {
        if (alimento_factura == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO alimentos_factura VALUES (?, ?, ?, ?)")) {

            statement.setInt(1, alimento_factura.getCantidad());
            statement.setDouble(2, alimento_factura.getMonto_total());
            statement.setInt(3, (int) alimento_factura.getAlimento().getLlave());
            statement.setInt(4, alimento_factura.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY " + alimento_factura;
            return "SQL MISSING: " + alimento_factura;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public List<AlimentoFactura> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM alimentos_factura")) {

            List <AlimentoFactura> list = new ArrayList<>();

            while (resultSet.next()) {
                Factura factura = FacturaRepositorio.getInstance().read(resultSet.getInt(4));

                list.add(new AlimentoFactura(
                        factura.getLlave(),
                        factura.getFecha_factura(),
                        factura.getMonto_total(),
                        factura.getProveedor(),
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        AlimentoRepositorio.getInstance().read(resultSet.getInt(3))
                ));
            }

            return list;
        }
    }

    @Override
    public AlimentoFactura read(Integer llave_factura, Integer llave_alimento) throws SQLException {
        if (llave_factura == null || llave_alimento == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM alimentos_factura WHERE id_factura = ? AND id_alimento = ?")) {

            statement.setInt(1, llave_factura);
            statement.setInt(2, llave_alimento);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Factura factura = FacturaRepositorio.getInstance().read(llave_factura);

                return new AlimentoFactura(
                        factura.getLlave(),
                        factura.getFecha_factura(),
                        factura.getMonto_total(),
                        factura.getProveedor(),
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        AlimentoRepositorio.getInstance().read(resultSet.getInt(3))
                );
            }
            return null;
        }
    }
}
