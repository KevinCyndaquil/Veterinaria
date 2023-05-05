package application.views.FacturaChooser;

import application.models.entidades.Proveedores;
import application.views.components.InputText;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Fonts;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.Instant;
import java.util.Date;

public class FacturaChooserView extends CustomJFrame {
    public FacturaChooserView() {
        super("FacturaChooserView");
        setLayout(null);
        setSize(900, 500);
    }

    public void initComponents() {
        panel = new JPanel(null);
        panel.setBackground(new Color(225, 216, 206));
        getContentPane().add(panel);

        pnlButtons = new JPanel(null);
        pnlButtons.setBackground(new Color(8, 128, 107));
        pnlButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panel.add(pnlButtons);

        title = new JLabel("FACTURAS VETERINARIA VIDA");
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.ITALIC, 25));
        title.setBackground(new Color(239, 170, 59));
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.add(title);

        lblId_factura = new JLabel("Folio factura");
        lblId_factura.setFont(Fonts.load(Fonts.LT_COMICAL).deriveFont(Font.BOLD, 14));
        panel.add(lblId_factura);

        lblFecha = new JLabel("Fecha factura");
        lblFecha.setFont(Fonts.load(Fonts.LT_COMICAL).deriveFont(Font.BOLD, 14));
        panel.add(lblFecha);

        lblProveedor = new JLabel("Proveedores");
        lblProveedor.setFont(Fonts.load(Fonts.LT_COMICAL).deriveFont(Font.BOLD, 14));
        panel.add(lblProveedor);

        cmbProveedor = new JComboBox<>();
        panel.add(cmbProveedor);

        fldId_factura = new InputText("folio...");
        fldId_factura.setFont(fldId_factura.getFont().deriveFont(Font.ITALIC, 14));
        panel.add(fldId_factura);

        dateChooser = new JDateChooser();
        dateChooser.setDate(Date.from(Instant.now()));
        panel.add(dateChooser);

        tblFacturas = new JTable(new DefaultTableModel(new Object[]{"Número de factura", "Proveedor", "Monto total"}, 0));
        scrollPane = new JScrollPane(tblFacturas);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panel.add(scrollPane);

        btnConsultar = new JButton(
                "<html><p style='width: 50px; height: 50px; text-align: center'>CONSULTAR</p></html>");
        btnConsultar.setMargin(null);
        btnConsultar.setHorizontalAlignment(SwingConstants.CENTER);
        btnConsultar.setForeground(Color.RED);
        btnConsultar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        pnlButtons.add(btnConsultar);

        btnInsertar = new JButton(
                "<html><p style='width: 50px; height: 50px; text-align: center'>INSERTAR</p></html>");
        btnInsertar.setForeground(Color.GREEN);
        btnInsertar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        pnlButtons.add(btnInsertar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                panel.setSize(getSize());
                pnlButtons.setBounds(0, 0, 230, getHeight());
                title.setBounds(225, 0, getWidth() - 240, 60);

                lblId_factura.setBounds(270, 70, 150, 20);
                lblProveedor.setBounds(490, 70, 150, 20);
                lblFecha.setBounds(700, 70, 150, 20);

                fldId_factura.setBounds(270, 90, 150, 20);
                cmbProveedor.setBounds(490, 90, 150, 20);
                dateChooser.setBounds(700, 90, 150, 20);

                btnConsultar.setBounds(65, getHeight() / 5, 100, 100);
                btnInsertar.setBounds(65, (getHeight() / 5) * 3, 100, 100);

                scrollPane.setBounds(228, 125, getWidth() - 244, getHeight() - 164);
                tblFacturas.setBounds(228, 125, getWidth() - 244, getHeight() - 164);
            }
        });
    }

    public static void main(String[] args) {
        FacturaChooserView view = new FacturaChooserView();
        view.setVisible(true);
    }

    //Components
    public JPanel panel;
    public JPanel pnlButtons;

    public JLabel title;
    public JLabel lblProveedor;
    public JLabel lblId_factura;
    public JLabel lblFecha;

    public JComboBox<Proveedores> cmbProveedor;
    public InputText fldId_factura;
    public JDateChooser dateChooser;

    public JScrollPane scrollPane;
    public JTable tblFacturas;

    public JButton btnConsultar;
    public JButton btnInsertar;
}
