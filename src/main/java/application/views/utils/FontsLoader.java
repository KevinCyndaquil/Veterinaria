package application.views.utils;

import java.awt.*;
import java.io.File;

public class FontsLoader {
    public Font load(Fonts font) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(font.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
