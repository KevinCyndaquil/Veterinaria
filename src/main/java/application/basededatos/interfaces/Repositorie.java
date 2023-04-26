package application.basededatos.interfaces;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Repositorie <I, M>{
    public boolean save(@NotNull M m) {
        Class<?> model = m.getClass();
        List<String> attributesName = new ArrayList<>();
        List<Object> attributesValue = new ArrayList<>();

        while(!model.equals(Object.class)) {
            Field[] fields = model.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
                attributesName.add(field.getName());
                field.setAccessible(true);
                try {
                    attributesValue.add(field.get(m).toString());
                } catch (IllegalAccessException e) {
                    System.out.println("hola");
                }

                field.setAccessible(false);
            }
            model = model.getSuperclass();
        }

        System.out.println(attributesName);
        System.out.println(attributesValue);

        return false;
    }
}
