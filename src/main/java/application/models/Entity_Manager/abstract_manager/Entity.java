package application.models.Entity_Manager.abstract_manager;

import application.models.Entity_Manager.annotations.*;
import lombok.NonNull;
import org.jetbrains.annotations.Contract;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * This interface provides a form to manipulate models' attributes of database in java, from an entity relation
 * model. You can get attributes and keys, that figures attributes in the tables in database. The class has a
 * default code using the Annotations provides by us. The model must use the correct form of them for a correct
 * work of the class.
 */
public interface Entity {


    /**
     * Formats an object to a valid attribute in database.
     * @param value the object needs to be formatted.
     * @return the object formatted.
     */
    static Object format(Object value) {
        if (value == null)
            return null;

        return switch (value.getClass().getSimpleName()) {
            case "String" -> (value.equals(SqlAttribute.DEFAULT_VALUE)) ? value : "'" + value + "'";
            case "LocalDate", "LocalTime","Date", "Time" -> "'" + value + "'";
            case "Integer", "int", "Float", "float", "Double", "double", "BigDecimal" -> value;
            //case  -> ((BigDecimal) value).doubleValue();
            default -> {
                System.out.println(value.getClass() + " is not supported for formatted");
                yield value;
            }
        };
    }


    @Contract(pure = true)
    static Entity newInstance(@NonNull Class<?> clazz, Object[] params) {
        try {
            Arrays.stream(clazz.getConstructors()).toList().forEach(cons -> {

            });

            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.isAnnotationPresent(SqlInstance.class)) {
                    Object[] values = params;
                    int nParam = constructor.getParameterCount();

                    if (nParam > params.length) {
                        values = new Object[nParam];


                        for (int i = 0; i < nParam; i++) {
                            values[i] = (i < params.length) ? params[i] : null;
                        }
                    }

                    System.out.printf("\n**Estos son los valores a instanciar para %s en newInstance:%n", clazz.getSimpleName());
                    Arrays.stream(values).toList().forEach(v -> System.out.println(v + ":" + ((v == null) ? "null" : v.getClass().getTypeName())));
                    return (Entity) constructor.newInstance(values);
                }
            }
            System.out.println("Constructor with Annotation @SqlInstance must be use in " + clazz.getSimpleName());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Class " + clazz.getSimpleName() + " couldn't be instanced: " + e.getMessage());
        }
        return null;
    }


    private static @NonNull Iterable<Field> fields(@NonNull Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> c = clazz;

        if (!clazz.isAnnotationPresent(SqlEntity.class))
            return null;

        while (!c.equals(Object.class)) {
            fields.addAll(Arrays.stream(c.getDeclaredFields()).toList());

            c = c.getSuperclass();
        }

        return fields;
    }


    /**
     * This method inspects a Class<>, getting all SQL columns or attributes in Entity Class.
     * @param clazz the Class has to be inspected.
     * @return a List of objects with columns. The list will be like this. If the SQLAttribute is not an
     * Entity, for example, a String type. It will be represented with its name. But, if it's an Entity, will
     * be a Class object of that type.
     */

    static @NonNull List<Object> columns(Class<?> clazz) {
        List<Object> columns = new ArrayList<>();

        //iteramos todas las columnas
        Entity.fields(clazz).forEach(f -> {
            SqlAttribute attribute = f.getAnnotation(SqlAttribute.class);

            //si el Field no es un atributo SQL, no lo tomamos en cuenta
            if (attribute == null)
                return;

            if (f.getType().isEnum())
                columns.addAll(Entity.columns(f.getType()));
            //si el Field es una entidad (Una referencia a otra tabla)
            else if (f.getType().isAnnotationPresent(SqlEntity.class))
                columns.add(f.getType());
            //comprobamos el nombre del atributo
            else if (attribute.value().equals(SqlEntity.DEFAULT_NAME))
                columns.add(f.getName());
            else
                columns.add(attribute.value());
        });

        return columns;
    }


    /**
     * This method gets all primary keys' name for a SQL table, check all annotations @SqlKey with value PRIMARY_KEY,
     * It's important that the fields in class has that annotation using a correct value.
     * @param clazz The clazz to inspect
     * @return a list of string with names of each primary key
     */
    static @NonNull List<Object> keys(Class<?> clazz) {
        List<Object> primaryKeys = new ArrayList<>();

        Entity.fields(clazz).forEach(f -> {
            SqlAttribute attribute = f.getAnnotation(SqlAttribute.class);
            SqlKey key = f.getAnnotation(SqlKey.class);
            SqlKeys keys = f.getAnnotation(SqlKeys.class);

            if (key == null) {
                if (keys == null)
                    return;

                for (SqlKey k : keys.value()) {
                    if (k.value() == SqlKey.PRIMARY_KEY)
                        key = k;
                }

                if (key == null)
                    return;
            }

            if (key.value() == SqlKey.FOREIGN_KEY)
                return;

            if (f.getType().isAnnotationPresent(SqlEntity.class)) {
                primaryKeys.add(f.getType());
                return;
            }

            if (attribute.value().equals(SqlEntity.DEFAULT_NAME))
                primaryKeys.add(f.getName());
            else
                primaryKeys.add(attribute.value());

        });

        return primaryKeys;
    }


    static @NonNull List<Object> nameKeys(Class<?> clazz) {
        List<Object> primaryKeys = new ArrayList<>();

        Entity.fields(clazz).forEach(f -> System.out.println(f.getName()));

        Entity.fields(clazz).forEach(f -> {
            SqlAttribute attribute = f.getAnnotation(SqlAttribute.class);
            SqlKey key = f.getAnnotation(SqlKey.class);
            SqlKeys keys = f.getAnnotation(SqlKeys.class);

            if (key == null) {
                if (keys == null)
                    return;

                for (SqlKey k : keys.value()) {
                    if (k.value() == SqlKey.PRIMARY_KEY)
                        key = k;
                }

                if (key == null)
                    return;
            }

            if (key.value() == SqlKey.FOREIGN_KEY)
                return;

            if (f.getType().isAnnotationPresent(SqlEntity.class)) {
                primaryKeys.addAll(Entity.nameKeys(f.getType()));
                return;
            }

            if (attribute.value().equals(SqlEntity.DEFAULT_NAME))
                primaryKeys.add(f.getName());
            else
                primaryKeys.add(attribute.value());
        });

        return primaryKeys;
    }


    private Object fieldValue(@NonNull Field field) {
        Object value = null;

        try {
            field.setAccessible(true); //put the field accessible

            if (field.getType().isEnum())
                value = field.get(this);
            else
                value = Entity.format(field.get(this));

            field.setAccessible(false); //put the field don't accessible
        } catch (IllegalAccessException e) {
            System.out.println("couldn't access to field " + field.getName() + " in class " + getClass().getSimpleName());
        }

        return value;
    }


    default String entityName() {
        SqlEntity entity = getClass().getAnnotation(SqlEntity.class);

        if (entity.value().equals(SqlEntity.DEFAULT_NAME))
            return this.getClass().getSimpleName();
        return entity.value();
    }


    default Map<String, Object> attributes() {
        Map<String, Object> attributes = new HashMap<>();

        Entity.fields(getClass()).forEach(f -> {
            SqlAttribute attribute = f.getAnnotation(SqlAttribute.class);
            SqlKey primaryKey = f.getAnnotation(SqlKey.class);
            SqlKeys keys = f.getAnnotation(SqlKeys.class);

            if (attribute == null)
                return;
            if (primaryKey != null)
                return;
            if (keys != null)
                return;

            Object value = fieldValue(f);

            if (value == null)
                return;

            if (value.getClass().isEnum()) {
                if (value instanceof Entity entity)
                    attributes.putAll(entity.attributes());
            } else if (attribute.value().equals(SqlEntity.DEFAULT_NAME))
                attributes.put(f.getName(), value);
            else
                attributes.put(attribute.value(), value);
        });

        return attributes;
    }


    default Map<String, Object> key(int key_type) {

        Map<String, Object> mapPrimaryKey = new HashMap<>();

        Entity.fields(getClass()).forEach(f -> {
            SqlAttribute attribute = f.getAnnotation(SqlAttribute.class);
            SqlKey key = f.getAnnotation(SqlKey.class);
            SqlKeys keys = f.getAnnotation(SqlKeys.class);

            String name = f.getName();
            Object value = fieldValue(f);

            // check the entity has annotations
            if (key == null) {
                if (keys == null)
                    return;

                for (SqlKey k : keys.value()) {
                    if (k.value() == key_type)
                        key = k;
                }

                if (key == null)
                    return;
            }

            if (key.value() != key_type)
                return;
            if (attribute == null)
                return;

            // if value is not null, then, we check if is an entity too. If it is, we get its primary keys and do the same of before.
            if (value != null)
                if (value.getClass().getAnnotation(SqlEntity.class) != null) {
                    mapPrimaryKey.putAll(((Entity) value).key(SqlKey.PRIMARY_KEY));
                    return;
                }
            if (value == null)
                return;

            // if the annotation's values is not a DEFAULT_NAME, we put the annotation's value
            if (!attribute.value().equals(SqlEntity.DEFAULT_NAME))
                name = attribute.value();

            // we put in map the values.
            mapPrimaryKey.put(name, value);
        });

        return mapPrimaryKey;
    }

}
