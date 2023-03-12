package application.views.components;

import application.views.components.abstracts.CustomJPanel;
import lombok.Getter;


import javax.swing.*;

public class PerfilMarco extends CustomJPanel {
    private final JLabel marco;
    //this is the image that will be used to set the image of the JLabel the size is: 135x135 max
    @Getter
    private final JLabel fotoJL;
    public PerfilMarco() {
        setLayout(null);

        marco = new JLabel();
        marco.setIcon(new ImageIcon("src/main/resources/images/marco_perfil.png"));
        marco.setSize(marco.getIcon().getIconWidth(), marco.getIcon().getIconHeight());
        add(marco);

        fotoJL = new JLabel();
        fotoJL.setIcon(new ImageIcon("src/main/resources/images/perfil_foto.png"));
        fotoJL.setSize(fotoJL.getIcon().getIconWidth(), fotoJL.getIcon().getIconHeight());
        add(fotoJL);

        setSize(marco.getSize());

        fotoJL.setLocation(this.getWidth() / 2 - fotoJL.getWidth() / 2 + 2, this.getHeight() / 2 - fotoJL.getHeight() / 2 + 7);
        marco.setLocation(0, 0);
    }
}
