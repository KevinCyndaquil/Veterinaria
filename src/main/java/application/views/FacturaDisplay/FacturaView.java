/*package application.views.FacturaDisplay;

import application.basededatos.interfaces.ReadAll;
import application.basededatos.repositorios.AlimentoRep;
import application.modelos.entidades.Alimento;
import application.views.components.TextDisplay;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.ResponsiveDimension;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FacturaView extends CustomJFrame {
    private Dimension size;

    /**
     * Constructor de la vista
     * @param fecha_factura es la fecha de la factura a leer.
     *//*
    public FacturaView(LocalDate fecha_factura, Integer id_proveedor) {
        super("Factura");
        //initComponents();
    }
    public void initComponents() {
        size = ResponsiveDimension.getNextResolution().getSize();
        //setSize(size);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, size.width, size.height);

        jTableModel = new ArticulosJTableModel();
        jTable = new JTable(jTableModel);

        jScrollPaneJTable = new JScrollPane(jTable);
        jScrollPaneJTable.setColumnHeaderView(jTable.getTableHeader());

        titulo = new TextDisplay("Registro de facturas proveedor");
        titulo.setOpaque(true);
        titulo.setBackground(new Color(55, 6, 192));
        titulo.setForeground(Color.white);
        titulo.setFont(new Font(Font.DIALOG_INPUT, Font.ITALIC, 18));

        jPanel.add(titulo);
        jPanel.add(jScrollPaneJTable);

        getContentPane().add(jPanel);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                jPanel.setSize(getContentPane().getSize());
                titulo.setBounds(0, 0, getContentPane().getWidth(), 50);
                jScrollPaneJTable.setBounds(0, 50, getContentPane().getWidth(), getContentPane().getHeight() - 70);
                jTable.setBounds(0, 50, getContentPane().getWidth(), getContentPane().getHeight() - 70);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing
            new FacturaView(LocalDate.of(2003, 12, 10), 1).setVisible(true);

        });
    }

    private JPanel jPanel;
    private TextDisplay titulo;
    public ArticulosJTableModel jTableModel;
    public JTable jTable;
    private JScrollPane jScrollPaneJTable;
    private JButton btnAceptar;
    private JLabel lblMontoTotal;
}*/
