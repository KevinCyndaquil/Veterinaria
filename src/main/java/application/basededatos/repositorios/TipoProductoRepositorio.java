package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.FullRead;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TipoProductoRepositorio implements FullRead<Integer, TipoProducto> {
    private static TipoProductoRepositorio instance;

    public static TipoProductoRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, TipoProductoRepositorio::new);
    }

    @Override
    public TipoProducto read(Integer llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM tipo_producto WHERE id_tipo_producto = ? AND activo = true")) {

            statement.setInt(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new TipoProducto(
                        llave,
                        resultSet.getString(2)
                );

            return null;
        }
    }

    @Override
    public List<TipoProducto> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM tipo_producto WHERE activo = true ORDER BY nombre")) {

            List <TipoProducto> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add( new TipoProducto(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }

            return list;
        }
    }
}
