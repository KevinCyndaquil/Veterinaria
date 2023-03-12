package application.views.components;

import application.views.components.abstracts.CustomJLabel;
import application.views.utils.Fonts;
import application.views.utils.FontsLoader;

import javax.swing.*;

public class TextDisplay extends CustomJLabel {
    private final FontsLoader fontsLoader;
    public TextDisplay(String text) {
        super(text);
        fontsLoader = new FontsLoader();
        setFont(fontsLoader.load(Fonts.CHERISH).deriveFont(96f));
        setHorizontalAlignment(SwingConstants.CENTER);
        //128
        setSize(477, 128);
    }
}
