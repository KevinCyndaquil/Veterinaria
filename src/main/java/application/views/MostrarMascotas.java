package application.views;

import application.views.components.Button;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.ResponsiveDimension;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarMascotas extends JFrame {
    public JTable table1;
    public Button btnSeleccionar;
    public Button btnRegistrar;
    public MostrarMascotas() {
        setSize(800, 800);
        setBackground(Color.white);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

    }

    public void initComponents() {


        table1 = new JTable();
        table1.setBounds(10, 10, 500, 500);
        table1.setRowHeight(80);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("fecha nacimiento");
        model.addColumn("Sexo");
        model.addColumn("Dueño");
        model.addColumn("Raza");

        model.addRow(new Object[]{"1", "Firulais", "12/12/12", "Macho", "Juan", "Pitbull"});
        model.addRow(new Object[]{"2", "Pitbull", "12/12/12", "Macho", "Juan", "Pitbull"});
        table1.setModel(model);

        table1.setBackground(Color.white);
        JScrollPane sp = new JScrollPane(table1);
        sp.setBounds(10, 10, 500, 500);
        sp.setViewportView(table1);
        add(sp);

        btnSeleccionar = new Button("Seleccionar");
        btnSeleccionar.setBounds(10, 520, 200, 50);
        btnSeleccionar.setBackground(Color.decode("#F8F2E7"));
        add(btnSeleccionar);

        btnRegistrar = new Button("Registrar");
        btnRegistrar.setBounds(300, 520, 200, 50);
        btnRegistrar.setBackground(Color.decode("#F8F2E7"));
        add(btnRegistrar);


    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing

            try {
                UIManager.setLookAndFeel(new MaterialLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            new MostrarMascotas().setVisible(true);

        });
    }
}
