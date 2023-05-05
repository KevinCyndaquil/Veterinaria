package application.views.utils;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public enum Fonts {
    CHERISH("Cherish", Objects.requireNonNull(Fonts.class.getClassLoader().getResource(
            "fonts/Cherish/Cherish-Regular.ttf"))),
    MONSERRAT("Montserrat", Objects.requireNonNull(Fonts.class.getClassLoader().getResource(
            "fonts/Montserrat/Montserrat-VariableFont_wght.ttf"))),
    HEY_COMIC("Hey Comic", Objects.requireNonNull(Fonts.class.getClassLoader().getResource(
            "fonts/Hey Comic.ttf"))),
    LT_COMICAL("LT Comical", Objects.requireNonNull(Fonts.class.getClassLoader().getResource(
            "fonts/LT Comical.ttf")));

    @Getter
    private final String name;
    @Getter
    private final URL path;

    /**
     * A partir de uno de los valores posible de la enum Fonts, devolvemos una instancia de Font que puede
     * ser usada para dar formato a cualquier componente.
     * @param font una instancia de la enum Fonts.
     * @return un objeto Font.
     */
    public static @Nullable Font load(@NotNull Fonts font) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, font.getPath().openStream());
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    Fonts(String name, URL path) {
        this.name = name;
        this.path = path;
    }
}
