package application.models.Entity_Manager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SqlAttribute {
    String DEFAULT_VALUE = "@default_value";
    String value() default SqlEntity.DEFAULT_NAME;
}
