package application.basededatos.interfaces;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create <I, M> {
    public I create(@NotNull M m)  {
        String func_name = "obt" + m.getClass().getSimpleName();
        Class<?> m_class = m.getClass();
        Map<String, Object> attributes = new HashMap<>();
        List<String> attributeList = new ArrayList<>();
        List<Object> attributesValue = new ArrayList<>();

        while(!m_class.equals(Object.class)) {
            Field[] fields = m_class.getDeclaredFields();
            Constructor<?> constructor = null;
            /*try {
                constructor = m.getClass().getConstructor(Integer.class, String.class, BigDecimal.class, String.class, BigDecimal.class);
                Parameter[] parameters = constructor.getParameters();

                for (Parameter parameter : parameters) {
                    System.out.println("C:" + parameter.getName());
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }*/


            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    attributes.put(field.getName(), field.get(m));
                } catch (IllegalAccessException e) {
                    System.out.println("err.couldn't access to " + field.getName() + " > " + e.getMessage());
                }
                field.setAccessible(false);
            }
            m_class = m_class.getSuperclass();
        }


        System.out.println(attributes);
        try {
            attributes.forEach((s, a) -> {
                if (a != null)
                    System.out.println(a.getClass());
            });
        } catch (NullPointerException e) {
            System.out.println("null");
        }
        return null;
    }
}
