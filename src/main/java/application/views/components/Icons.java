package application.views.components;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

public enum Icons {
    EMOTIVE_SUCROUSE(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/sucrouse01.png"))),
    UNCERTAIN_SUCROUSE(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/sucrouse02.png"))),
    SHY_SUCROUSE(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/sucrouse03.png"))),
    HAPPY_SUCROUSE(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/sucrouse04.png"))),
    STAR_EYES_VENTI(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/venti01.png"))),
    STAR_EYES_GANYU(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/ganyu01.png"))),
    DOGGY(Objects.requireNonNull(Icons.class.getClassLoader().getResource(
            "images/icons/perrito_lengua.jpg")));
    private URL path;

    Icons(@NotNull URL path) {
        this.path = path;
    }

    @Contract("_ -> new")
    public static @NotNull ImageIcon get(@NotNull Icons icon) {
        return new ImageIcon(icon.path);
    }
}
