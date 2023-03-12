package application.views.components;

import javax.swing.*;
import java.awt.*;

public class FondoLogin extends JLabel {
    private final Image image;

    public FondoLogin(ImageIcon imageIcon) {
        super();
           this.image = imageIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        if (image != null) {
            g.drawImage(image, 0, 0, width, height, null);
        }
    }
}
