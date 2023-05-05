package application.views.components;

import application.views.components.abstracts.CustomJLabel;
import application.views.utils.Fonts;

import javax.swing.*;

public class TextDisplay extends CustomJLabel {
    public TextDisplay(String text) {
        super(text);
        setFont(Fonts.load(Fonts.CHERISH).deriveFont(96f));
        setHorizontalAlignment(SwingConstants.CENTER);
        //128
        setSize(477, 128);
    }
}
