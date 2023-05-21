package application.views.terminadas;

import application.models.entidades.Personas;
import application.views.components.InputText;
import application.views.components.abstracts.CustomJFrame;
import application.views.components.abstracts.CustomJTextField;
import application.views.utils.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HistorialMascotaView extends CustomJFrame {
    public HistorialMascotaView() {
        super("Historial Mascota");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public void initComponents() {
        panel = new JPanel(null);
        getContentPane().add(panel);

        title = new JLabel("HISTORIAL MASCOTA");
        title.setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.ITALIC, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);

        lblNombreMascota = new JLabel("mascota");
        lblNombreMascota.setFont(Fonts.load(Fonts.HEY_COMIC).deriveFont(Font.PLAIN, 16));
        lblNombreMascota.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblNombreMascota);

        lblPropietarioMascota = new JLabel("propietario");
        lblPropietarioMascota.setFont(Fonts.load(Fonts.HEY_COMIC).deriveFont(Font.PLAIN, 16));
        lblPropietarioMascota.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblPropietarioMascota);

        lblIdMascota = new JLabel("id mascota");
        lblIdMascota.setFont(Fonts.load(Fonts.HEY_COMIC).deriveFont(Font.PLAIN, 16));
        lblIdMascota.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblIdMascota);

        fldNombreMascota = new InputText("nombre mascota");
        fldNombreMascota.setFont(fldNombreMascota.getFont().deriveFont(Font.PLAIN, 16));
        panel.add(fldNombreMascota);

        cmbPropietarioMascota = new JComboBox<>();
        cmbPropietarioMascota.setFont(fldNombreMascota.getFont().deriveFont(Font.PLAIN, 16));
        panel.add(cmbPropietarioMascota);

        fldIdMascota = new InputText("id mascota");
        fldIdMascota.setFont(fldNombreMascota.getFont().deriveFont(Font.PLAIN, 16));
        panel.add(fldIdMascota);

        tblHistorial = new JTable(new DefaultTableModel(new Object[]{
                "Mascota",
                "Articulo",
                "Costo",
                "Proveedor",
                "Cantidad",
                "TipoArticulo"},
                0));
        sclHistorial = new JScrollPane(tblHistorial);
        panel.add(sclHistorial);

        btnConsultar = new JButton("C O N S U L T A R");
        btnConsultar.setFont(Fonts.load(Fonts.HEY_COMIC).deriveFont(Font.PLAIN, 16));
        panel.add(btnConsultar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                panel.setSize(getContentPane().getSize());

                title.setBounds(20, 20, getWidth() - 20, 40);

                lblNombreMascota.setBounds(getWidth() - 990, 90, 100, 20);
                fldNombreMascota.setBounds(getWidth() - 880, 90, 200, 20);

                lblPropietarioMascota.setBounds(getWidth() - 660, 90, 100, 20);
                cmbPropietarioMascota.setBounds(getWidth() - 550, 90, 200, 20);

                lblIdMascota.setBounds(getWidth() - 330, 90, 100, 20);
                fldIdMascota.setBounds(getWidth() - 220, 90, 200, 20);

                btnConsultar.setBounds(40, 80, getWidth() - 1030, 40);

                sclHistorial.setBounds(0, 150, getWidth(), getHeight() - 150);
                tblHistorial.setBounds(0, 150, getWidth(), getHeight() - 150);
            }
        });
    }

    //components
    public JPanel panel;

    public JLabel title;
    public JLabel lblNombreMascota;
    public JLabel lblPropietarioMascota;
    public JLabel lblIdMascota;

    public CustomJTextField fldNombreMascota;
    public JComboBox<Personas> cmbPropietarioMascota;
    public CustomJTextField fldIdMascota;

    public JScrollPane sclHistorial;
    public JTable tblHistorial;

    public JButton btnConsultar;
}
