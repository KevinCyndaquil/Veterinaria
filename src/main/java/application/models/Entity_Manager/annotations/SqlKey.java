package application.models.Entity_Manager.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(SqlKeys.class)
public @interface SqlKey{
    int PRIMARY_KEY = 0;
    int FOREIGN_KEY = 1;

    int STRONG = 0;
    int SERIAL = 1;
    int CONSECUTIVE = 2;


    int value() default PRIMARY_KEY;
    int type() default STRONG;
}
