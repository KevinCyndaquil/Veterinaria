package application.views.alta;

import application.views.components.abstracts.AbstractJFrame;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class AltaMascota extends AbstractJFrame {
    public AltaMascota() {
        super("alta mascota");

        setSize(440, 230);
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

        tFecha = new JLabel("fecha");
        tFecha.setBounds(10, 40, 100, 20);
        panel.add(tFecha);

        tSexo = new JLabel("sexo");
        tSexo.setBounds(10, 70, 100, 20);
        panel.add(tSexo);

        tPropietario = new JLabel("propietario");
        tPropietario.setBounds(10, 100, 100, 20);
        panel.add(tPropietario);

        tRaza = new JLabel("raza");
        tRaza.setBounds(10, 130, 100, 20);
        panel.add(tRaza);

        iNombre = new JTextField();
        iNombre.setBounds(110, 10, 200, 20);
        panel.add(iNombre);

        dFecha = new JDateChooser(Date.valueOf(LocalDate.now()));
        dFecha.setBounds(110, 40, 200, 20);
        panel.add(dFecha);

        cSexo = new JComboBox<>(new String[]{"macho", "hembra"});
        cSexo.setBounds(110, 70, 200, 20);
        panel.add(cSexo);

        iPropietario = new JTextField();
        iPropietario.setBounds(110, 100, 200, 20);
        panel.add(iPropietario);

        iRaza = new JTextField();
        iRaza.setBounds(110, 130, 200, 20);
        panel.add(iRaza);

        bPropietario = new JButton("ver");
        bPropietario.setBounds(320, 100, 100, 20);
        panel.add(bPropietario);

        bRaza = new JButton("ver");
        bRaza.setBounds(320, 130, 100, 20);
        panel.add(bRaza);

        bRegistrar = new JButton("alta");
        bRegistrar.setBounds(150, 160, 100, 20);
        panel.add(bRegistrar);
    }

    public JPanel panel;

    public JLabel tNombre;
    public JLabel tFecha;
    public JLabel tSexo;
    public JLabel tPropietario;
    public JLabel tRaza;

    public JTextField iNombre;
    public JDateChooser dFecha;
    public JComboBox<String> cSexo;
    public JTextField iPropietario;
    public JTextField iRaza;

    public JButton bPropietario;
    public JButton bRaza;
    public JButton bRegistrar;
}
