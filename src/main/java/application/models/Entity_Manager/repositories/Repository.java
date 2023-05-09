package application.models.Entity_Manager.repositories;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlKey;
import application.database.Postgres;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<M extends Entity> implements
        Find<M>,
        Save<M>,
        Update<M>,
        Delete<M>,
        Hide<M>{


    public static final int AND_WHERE = 0;
    public static final int OR_WHERE = 1;
    public static final int COMMA = 2;

    protected final GetConn conn;


    public Repository(GetConn conn) {
        this.conn = conn;
    }


    /**
     * This method transform a SQL column to an object of java, using an instance of ResultSet. This method does
     * this, check if the parameter object is an instance of String, that means, the object is a simple column
     * name, only get the object returned for ResultSet, but, if an Entity Class, first gets all primary keys,
     * then, gets the results of ResultSet and convert an instance of Entity Class.
     * @param column the column name or type.
     * @param resultSet the ResultSet of SQL query.
     * @return an object, the result of ResultSet.
     * @throws SQLException if there's an error with getting data.
     */
    static @Nullable Object toObject(Object column, ResultSet resultSet) throws SQLException{

        List<Object> values = new ArrayList<>();

        //System.out.println("obj:" + column);

        if (column instanceof String column_name)
            return resultSet.getObject(column_name);

        //casteamos el objeto a uno de tipo clase
        Class<?> clazz = (Class<?>) column;

        //iteramos los atributos referenciados por la entidad
        for (Object n : Entity.keys(clazz)) {
            //obtenemos el valor devuelto por la consulta
            //System.out.println("columna de " + column + ": " + n);
            values.add(toObject(n, resultSet));
        }

        //instanciamos un objeto Find
        Find<Entity> find = new Repository<>(new Postgres());

        //instanciamos el objeto de la columna
        var entity = Entity.newInstance(clazz, values.toArray());

        if (entity == null)
            return null;
        return find.findById(entity);
    }


    /**
     * Transforms a Map of String and Object to a sequence, for example, for where, insert into, update, etc.
     * clauses. The string value is the attribute name in database and object is the values to be compared.
     * @param attributes the map with the columns names and values
     * @param WHERE_TYPE if the where clause is a AND (0) clause, OR (1) clause o a COMMA (2) clause.
     * @return a SQL condition if all work, else, return a void string.
     */
    static @NotNull String toSqlSequence(@NotNull Map<String, Object> attributes, int WHERE_TYPE) {

        if (attributes.size() == 0)
            return "";

        String conjunction;
        StringBuilder where = new StringBuilder();

        switch (WHERE_TYPE) {
            case 0 -> {
                conjunction = " AND ";
                where.append("WHERE ");
            }
            case 1 -> {
                conjunction = " OR ";
                where.append("WHERE ");
            }
            case 2 -> conjunction = ", ";
            default -> {
                return "";
            }
        }


        attributes.forEach((name, value) -> {
            if (value == null)
                return;
            where.append("%s = %s".formatted(name, value)).append(conjunction);
        });

        if (where.isEmpty())
            return "";
        return where.delete(where.length() - conjunction.length(), where.length()).toString();
    }


    /**
     * Execute a SQL update and delete on database. The query must return a boolean value, else, catch
     * a PSQLException.
     * @param query the query to be executed.
     * @return true if the update was successfully, false if not, and null if there was an error.
     * @throws SQLException if there's an error with the query.
     */
    private @Nullable Boolean executeUpdate(String query) throws SQLException{
        try (Connection connection = conn.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next())
                return resultSet.getBoolean(1);
            return false;
        } catch (PSQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Object> find(M model) throws SQLException {
        if (model == null)
            return null;


        Map<String, Object> attributes = new HashMap<>(model.attributes());
            attributes.putAll(model.key(SqlKey.PRIMARY_KEY));
            attributes.putAll(model.key(SqlKey.FOREIGN_KEY));


        String query = "SELECT * FROM %s %s".formatted(
                model.entityName(),
                Repository.toSqlSequence(attributes, OR_WHERE));

        System.out.println("Consulta: " + query);

        List<Object> list = new ArrayList<>();

        try (Connection connection = conn.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                List<Object> values = new ArrayList<>();

                for (Object column : M.columns(model.getClass())) {
                    values.add(Repository.toObject(column, resultSet));
                }
                list.add(M.newInstance(model.getClass(), values.toArray()));
            }
        }

        return list;
    }


    @Override
    public Object findById(@NotNull M model) throws SQLException{

        String query = "SELECT * FROM %s %s".formatted(
                model.entityName(),
                Repository.toSqlSequence(model.key(SqlKey.PRIMARY_KEY), AND_WHERE));

        System.out.println("Consulta: " + query);

        try (Connection connection = conn.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                List<Object> values = new ArrayList<>();

                for (Object column : M.columns(model.getClass())) {
                    var obj = Repository.toObject(column, resultSet);
                    System.out.println(obj + "C" + obj.getClass());
                    values.add(obj);
                }

                System.out.println("Estos son los valores a instanciar" + values);
                return M.newInstance(model.getClass(), values.toArray());
            }
        }
        return null;
    }

    @Override
    public Object save(@NotNull M model) throws SQLException {
        StringBuilder attributes = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder keyName = new StringBuilder("RETURNING ");

        int nKey = 0;

        for (Object value : Entity.nameKeys(model.getClass())) {
            keyName.append(value).append(",");
            nKey++;
        }
        var keys = model.key(SqlKey.PRIMARY_KEY);
        keys.putAll(model.key(SqlKey.FOREIGN_KEY));
        keys.forEach((fk, v) -> {
            System.out.println(fk);
            System.out.println(v);
            attributes.append(fk).append(",");
            values.append(v).append(",");
        });
        model.attributes().forEach((a, v) -> {
            if (v == null)
                return;

            attributes.append(a).append(",");
            values.append(v).append(",");
        });

        if (attributes.isEmpty() || values.isEmpty())
            return null;

        attributes.delete(attributes.length() - 1, attributes.length());
        values.delete(values.length() - 1, values.length());
        keyName.delete(keyName.length() - 1, keyName.length());

        String entity = "%s(%s)".formatted(model.entityName(), attributes);
        String query = "INSERT INTO %s VALUES(%s) %s".formatted(entity, values, keyName);

        System.out.println("Consulta: " + query);


        try (Connection connection = conn.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {

                if (nKey == 1)
                    return resultSet.getObject(1);

                List<Object> result = new ArrayList<>();

                for (int i = 1; i <= nKey; i++) {
                    result.add(resultSet.getObject(i));
                }

                return result;
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

    @Override
    public Boolean delete(@NotNull M model) throws SQLException {

        Map<String, Object> attributes = new HashMap<>(model.attributes());
            attributes.putAll(model.key(SqlKey.PRIMARY_KEY));
            attributes.putAll(model.key(SqlKey.FOREIGN_KEY));


        String query = "DELETE FROM %s %s RETURNING TRUE".formatted(
                model.entityName(),
                toSqlSequence(attributes, OR_WHERE));

        System.out.println("Consulta: " + query);

        return executeUpdate(query);
    }

    @Override
    public Boolean deleteById(@NotNull M model) throws SQLException {

        String query = "DELETE FROM %s %s RETURNING TRUE".formatted(
                model.entityName(),
                toSqlSequence(model.key(SqlKey.PRIMARY_KEY), OR_WHERE));

        System.out.println("Consulta: " + query);

        return executeUpdate(query);
    }

    @Override
    public Boolean hideById(@NotNull M model) throws SQLException {

        String query = "UPDATE %s SET active = NOT active %s RETURNING active".formatted(
                model.entityName(),
                toSqlSequence(model.key(SqlKey.PRIMARY_KEY), AND_WHERE));

        System.out.println("Consulta: " + query);

        return executeUpdate(query);
    }

    @Override
    public Boolean updateById(@NotNull M model) throws SQLException {

        Map<String, Object> attributes = new HashMap<>(model.attributes());
        attributes.putAll(model.key(SqlKey.FOREIGN_KEY));

        String query = "UPDATE %s SET %s %s RETURNING TRUE".formatted(
                model.entityName(),
                toSqlSequence(attributes, COMMA),
                toSqlSequence(model.key(SqlKey.PRIMARY_KEY), AND_WHERE));

        System.out.println(query);

        return executeUpdate(query);
    }
}
