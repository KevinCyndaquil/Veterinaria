package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlKey;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaveF<M extends Entity> implements Save<M> {


    private final GetConn conn;


    public SaveF(GetConn conn) {
        this.conn = conn;
    }


    @Override
    public Object save(@NotNull M model) throws SQLException {

        StringBuilder attributes = new StringBuilder();

        model.key(SqlKey.PRIMARY_KEY).forEach((pk, v) -> {
            if (v == null)
                return;

            attributes.append("%s := %s".formatted(pk, v)).append(",");
        });
        model.key(SqlKey.FOREIGN_KEY).forEach((fk, v) -> {
            if (v == null)
                return;

            attributes.append("%s := %s".formatted(fk, v)).append(",");
        });
        model.attributes().forEach((a, v) -> {
            if (v == null)
                return;

            attributes.append("%s := %s".formatted(a, v)).append(",");
        });

        if (attributes.isEmpty())
            return null;

        attributes.replace(attributes.length() - 1, attributes.length(), "");

        String procedure = "SELECT obt%s(%s)".formatted(model.entityName(), attributes);

        System.out.println(procedure);

        try(Connection connection = conn.get();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(procedure)) {

            if (resultSet.next()) {
                return resultSet.getObject(1);
            }
        }
        return null;
    }


    @Override
    public Iterable<Object> saveAll(@NotNull Iterable<M> models) throws SQLException {
        List<Object> keys = new ArrayList<>();

        for (M model : models) {
            keys.add(save(model));
        }

        return keys;
    }
}
