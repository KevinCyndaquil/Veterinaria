package application.views.components;

import application.views.components.abstracts.CustomJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        JScrollPane scl = new JScrollPane(table);
        scl.setBounds(table.getBounds());
        scl.setViewportView(table);
        add(scl);

        btnSeleccionar = new Button("Seleccionar");
        btnSeleccionar.setBounds(20, getHeight() - 100, 200, 50);
        btnSeleccionar.setBackground(Color.decode("#F8F2E7"));
        add(btnSeleccionar);

        btnRegistrar = new application.views.components.Button("Registrar");
        btnRegistrar.setBounds(getWidth() - 240, getHeight() - 100, 200, 50);
        btnRegistrar.setBackground(Color.decode("#F8F2E7"));
        add(btnRegistrar);
    }

    public JTable table;

    public Button btnRegistrar;
    public Button btnSeleccionar;
}
