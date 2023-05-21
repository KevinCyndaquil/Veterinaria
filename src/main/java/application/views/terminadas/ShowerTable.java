package application.views.terminadas;

import application.views.components.Button;
import application.views.components.abstracts.CustomJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This view shows a table with two options:
 * a button to register a new entry.
 * a button to select a row.
 */
public class ShowerTable extends CustomJFrame {
    public Object[] columns;

    public ShowerTable(Object[] columns) {
        super("mostrar");

        this.columns = columns;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.NORMAL);

        initComponents();
    }

    @Override
    public void initComponents() {
        if (columns == null) return;

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        table.setSize(table.getPreferredSize().width, 370);
        table.setLocation(10, 10);
        table.setBackground(Color.white);
        System.out.println(table.getPreferredSize());

        setSize(table.getWidth() + 40, 500);
        setMinimumSize(new Dimension(450, 500));

        JScrollPane scl = new JScrollPane(table);
        scl.setBounds(table.getBounds());
        scl.setViewportView(table);
        add(scl);



        btnSeleccionar = new application.views.components.Button("Seleccionar");
        btnSeleccionar.setBounds(20, getHeight() - 100, 200, 50);
        btnSeleccionar.setBackground(Color.decode("#F8F2E7"));
        add(btnSeleccionar);

        btnRegistrar = new application.views.components.Button("Registrar");
        btnRegistrar.setBounds(getWidth() - 240, getHeight() - 100, 200, 50);
        btnRegistrar.setBackground(Color.decode("#F8F2E7"));
        add(btnRegistrar);


    }

    public JTable table;

    public application.views.components.Button btnRegistrar;
    public Button btnSeleccionar;
}
