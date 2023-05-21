package application.views.alta;

import application.views.components.abstracts.AbstractJFrame;

import javax.swing.*;
import java.awt.*;

public class AltaRaza extends AbstractJFrame {

    public AltaRaza() throws HeadlessException {
        super("alta raza");

        setSize(440, 170);
        initComponents();
    }

    @Override
    public void initComponents() {
        panel = new JPanel(null);
        panel.setSize(getSize());
        getContentPane().add(panel);

        tNombre = new JLabel("nombre");
        tNombre.setBounds(10, 10, 100, 20);
        panel.add(tNombre);

        tTotalAdopcion = new JLabel("total adopción");
        tTotalAdopcion.setBounds(10, 40, 100, 20);
        panel.add(tTotalAdopcion);

        tAnimal = new JLabel("animal");
        tAnimal.setBounds(10, 70, 100, 20);
        panel.add(tAnimal);

        iNombre = new JTextField();
        iNombre.setBounds(110, 10, 200, 20);
        panel.add(iNombre);

        iTotalAdopcion = new JTextField();
        iTotalAdopcion.setBounds(110, 40, 200, 20);
        panel.add(iTotalAdopcion);

        iAnimal = new JTextField();
        iAnimal.setBounds(110, 70, 200, 20);
        panel.add(iAnimal);

        bAnimal = new JButton("ver");
        bAnimal.setBounds(320, 70, 100, 20);
        panel.add(bAnimal);

        bRegistrar = new JButton("alta");
        bRegistrar.setBounds(150, 100, 100, 20);
        panel.add(bRegistrar);
    }

    public JPanel panel;

    public JLabel tNombre;
    public JLabel tTotalAdopcion;
    public JLabel tAnimal;

    public JTextField iNombre;
    public JTextField iTotalAdopcion;
    public JTextField iAnimal;

    public JButton bAnimal;
    public JButton bRegistrar;
}
