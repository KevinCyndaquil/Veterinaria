package application.views.terminadas;

import application.views.components.abstracts.CustomJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChooserMascotaView extends CustomJFrame {

    public ChooserMascotaView() {
        super("tabla mascotas");
        setBackground(Color.WHITE);
        setSize(600, 400);
        setResizable(false);
    }

    @Override
    public void initComponents() {
        panel = new JPanel(null);
        getContentPane().add(panel);

        tblMascotas = new JTable(new DefaultTableModel(new Object[]{
                "mascota",
                "fecha_nacimiento",
                "sexo",
                "propietario",
                "raza"}, 0));
        scrItem = new JScrollPane(tblMascotas);
        panel.add(scrItem);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                panel.setSize(getSize());
                tblMascotas.setSize(getSize());
                scrItem.setSize(getSize());
            }
        });
    }

    public JPanel panel;

    public JScrollPane scrItem;
    public JTable tblMascotas;
}
