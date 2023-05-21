package application.views.alta;

import application.views.components.abstracts.AbstractJFrame;

import javax.swing.*;
import java.awt.*;

public class AltaPropietario extends AbstractJFrame {
    public AltaPropietario() throws HeadlessException {
        super("alta propietario");

        setSize(330, 230);
        initComponents();
    }

    @Override
    public void initComponents() {
        panel = new JPanel(null);
        panel.setSize(getSize());
        getContentPane().add(panel);

        tRfc = new JLabel("RFC");
        tRfc.setBounds(10, 10, 100, 20);
        panel.add(tRfc);

        tNombre = new JLabel("nombre");
        tNombre.setBounds(10, 40, 100, 20);
        panel.add(tNombre);

        tApellido_p = new JLabel("apellido paterno");
        tApellido_p.setBounds(10, 70, 100, 20);
        panel.add(tApellido_p);

        tApellido_m = new JLabel("apellido materno");
        tApellido_m.setBounds(10, 100, 100, 20);
        panel.add(tApellido_m);

        tNoCuenta = new JLabel("no. cuenta");
        tNoCuenta.setBounds(10, 130, 100, 20);
        panel.add(tNoCuenta);

        iRfc = new JTextField();
        iRfc.setBounds(110, 10, 200, 20);
        panel.add(iRfc);

        iNombre = new JTextField();
        iNombre.setBounds(110, 40, 200, 20);
        panel.add(iNombre);

        iApellido_p = new JTextField();
        iApellido_p.setBounds(110, 70, 200, 20);
        panel.add(iApellido_p);

        iApellido_m = new JTextField();
        iApellido_m.setBounds(110, 100, 200, 20);
        panel.add(iApellido_m);

        iNoCuenta = new JTextField();
        iNoCuenta.setBounds(110, 130, 200, 20);
        panel.add(iNoCuenta);

        bRegistrar = new JButton("alta");
        bRegistrar.setBounds(100, 160, 100, 20);
        panel.add(bRegistrar);
    }

    public JPanel panel;

    public JLabel tRfc;
    public JLabel tNombre;
    public JLabel tApellido_p;
    public JLabel tApellido_m;
    public JLabel tNoCuenta;

    public JTextField iRfc;
    public JTextField iNombre;
    public JTextField iApellido_p;
    public JTextField iApellido_m;
    public JTextField iNoCuenta;

    public JButton bRegistrar;
}
