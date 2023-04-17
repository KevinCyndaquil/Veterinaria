package application.models.utils;

public @interface DoNotUse {
    String reason() default "attribute for class only";
}
