package application.views.FacturaChooser.FacturaDisplay;

import application.views.components.TextDisplay;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DetalleFacturaView extends CustomJFrame {
    private Color simpleTextColor;

    public DetalleFacturaView() {
        super("detalle factura");
        setSize(700, 700);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void initComponents() {
        simpleTextColor = new Color(245, 255, 154);

        panel = new JPanel(null);
        panel.setBackground(new Color(8, 199, 119));

        title = new TextDisplay("D E T A L L E    DE    F A C T U R A");
        title.setForeground(simpleTextColor);
        title.setFont(Fonts.load(Fonts.LT_COMICAL).deriveFont(Font.ITALIC, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(title);

        tblDetalleFacturas = new JTable(new DefaultTableModel());

        sclFacturas = new JScrollPane(tblDetalleFacturas);
        sclFacturas.setColumnHeaderView(tblDetalleFacturas.getTableHeader());
        panel.add(sclFacturas);

        btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setFont(Fonts.load(Fonts.HEY_COMIC).deriveFont(Font.BOLD, 16));
        btnActualizar.setHorizontalAlignment(SwingConstants.CENTER);
        btnActualizar.setForeground(Color.RED);
        panel.add(btnActualizar);

        getContentPane().add(panel);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                panel.setSize(getContentPane().getSize());

                title.setBounds(40, 20, getWidth() - 280, 60);

                sclFacturas.setBounds(0, 90, getWidth(), getHeight() - 110);
                tblDetalleFacturas.setBounds(0, 90, getWidth(), getHeight() - 110);

                btnActualizar.setBounds(getWidth() - 180, 20, 150, 50);
            }
        });
    }

    //components
    public JPanel panel;

    public TextDisplay title;

    public JTable tblDetalleFacturas;
    public JScrollPane sclFacturas;

    public JButton btnActualizar;
}
