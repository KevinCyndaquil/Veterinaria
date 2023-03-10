package application.views.utils;

import lombok.Getter;

public enum Fonts {
    CHERISH("Cherish", "src/main/resources/fonts/Cherish/Cherish-Regular.ttf"),
    MONSERRAT("Montserrat", "src/main/resources/fonts/Montserrat/Montserrat-VariableFont_wght.ttf");

    @Getter
    private final String name;
    @Getter
    private final String path;

    Fonts(String name, String path) {
        this.name = name;
        this.path = path;
    }

}
