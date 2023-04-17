package application.basededatos.interfaces;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Repositorie <I, M>{
    public boolean save(M m) {
        Class<?> model = m.getClass();
        List<String> attributeList = new ArrayList<>();

        while(!model.equals(Object.class)) {
            Field[] fields = model.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
                attributeList.add(field.getName());
            }
            model = model.getSuperclass();
        }

        System.out.println(attributeList);

        return false;
    }
}
