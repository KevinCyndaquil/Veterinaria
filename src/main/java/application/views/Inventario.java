package application.views;

import application.views.components.Button;
import application.views.components.abstracts.CustomJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Inventario extends CustomJFrame {
    public Inventario() {
        super("inventario");

        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public void initComponents() {
        setBackground(Color.WHITE);

        panel = new JPanel(null);
        panel.setLocation(0, 0);
        panel.setBackground(Color.WHITE);
        getContentPane().add(panel);

        table = new JTable();
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        panel.add(scrollPane);

        bActualizar = new Button("actualizar");
        panel.add(bActualizar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                panel.setSize(getSize());
                bActualizar.setBounds(10, 10, 200, 40);
                table.setBounds(0, 60, getWidth(), getHeight() - 60);
                scrollPane.setBounds(table.getBounds());

            }
        });
    }

    public JPanel panel;

    public JTable table;
    public JScrollPane scrollPane;

    public Button bActualizar;

    public static void main(String[] args) {
        (new Inventario()).setVisible(true);
    }
}
