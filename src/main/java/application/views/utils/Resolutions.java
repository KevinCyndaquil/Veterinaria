package application.views.utils;

import lombok.Getter;

import java.awt.*;

public enum Resolutions {
    WXGA(1366, 768),
    FULL_HD(1920, 1080);

    @Getter
    private final Integer width;
    @Getter
    private final Integer height;

    Resolutions(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Dimension getSize() {
        return new Dimension(width, height);
    }

}
