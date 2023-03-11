package application.views.components;

import application.views.components.abstracts.CustomJLabel;


import javax.swing.*;

public class Logo extends CustomJLabel {
    private final ImageIcon imageIcon;

    public Logo() {
        super();
        imageIcon = new ImageIcon("src/main/resources/images/logo.png");
        setIcon(imageIcon);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

}
