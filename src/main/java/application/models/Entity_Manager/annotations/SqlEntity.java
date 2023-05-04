package application.models.Entity_Manager.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface SqlEntity {
    String DEFAULT_NAME = "@default_name";

    int ENTITY_CLASS = 0;
    int ATTRIBUTES_CLASS = 1;
    int GENERALIZED_CLASS = 2;
    int SPECIALIZED_CLASS = 3;

    String value() default DEFAULT_NAME;
    int type() default ENTITY_CLASS;
}
