package application.views.terminadas;

import application.views.components.*;
import application.views.components.Button;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Fonts;
import application.views.utils.Positions;
import application.views.utils.ResponsiveDimension;
import mdlaf.MaterialLookAndFeel;
import org.jdesktop.swingx.JXDatePicker;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AltaCita extends CustomJFrame {
    private JPanel mainPanel;
    public Button btnRegresar;
    public Button btnAgendar;
    public Button btnQryVeterinario;
    public Button btnPagar;
    public Button btnQryNombre;
    public InputText iNombreMascota;
    public InputText iNombreVeterinario;
    public AltaCita() {
        super("Alta Cita");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setSize(ResponsiveDimension.getNextResolution().getSize());
        mainPanel.setLocation(0, 0);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.white);
        add(mainPanel);

        SectionRound section2 = new SectionRound();
        section2.setLocation(20, 20);
        //barLeft.setOpaque(false);
        mainPanel.add(section2);

        TextDisplay tFecha = new TextDisplay("Fecha de la cita");
        tFecha.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tFecha.setSize(tFecha.getPreferredSize());
        section2.add(tFecha);

        JXDatePicker picker = new JXDatePicker();
        picker.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        picker.setDate(new java.util.Date());
        picker.setFormats(new java.text.SimpleDateFormat("dd/MM/yyyy"));
        picker.setSize(300, 62);
        picker.getComponent(0).setBackground(Color.white);
        section2.add(picker);

        TextDisplay tHora = new TextDisplay("Hora de la cita");
        tHora.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tHora.setSize(tHora.getPreferredSize());
        section2.add(tHora);

        InputText iHora = new InputText("hh:mm");
        iHora.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        iHora.setSize(300, 62);
        section2.add(iHora);

        TextDisplay tInformacion = new TextDisplay("Información adicional");
        tInformacion.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tInformacion.setSize(tInformacion.getPreferredSize());
        section2.add(tInformacion);

        JTextArea iInformacion = new JTextArea();
        iInformacion.setBackground(Color.white);
        iInformacion.setBorder(BorderFactory.createLineBorder(Color.black));
        iInformacion.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        iInformacion.setSize(420, 149);
        section2.add(iInformacion);

        TextDisplay tNombreMascota = new TextDisplay("Nombre de la mascota");
        tNombreMascota.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tNombreMascota.setSize(tNombreMascota.getPreferredSize());
        section2.add(tNombreMascota);


        iNombreMascota = new InputText("Nombre");
        iNombreMascota.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        iNombreMascota.setSize(300, 62);
        section2.add(iNombreMascota);


        btnQryNombre = new Button("Buscar");
        btnQryNombre.setBackground(Color.decode("#F8F2E7"));
        btnQryNombre.setSize(100, 62);
        section2.add(btnQryNombre);

        TextDisplay tNombreVeterinario = new TextDisplay("Nombre del veterinario");
        tNombreVeterinario.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tNombreVeterinario.setSize(tNombreVeterinario.getPreferredSize());
        section2.add(tNombreVeterinario);


        iNombreVeterinario = new InputText("Nombre");
        iNombreVeterinario.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        iNombreVeterinario.setSize(300, 62);
        section2.add(iNombreVeterinario);

        btnQryVeterinario = new Button("Buscar");
        btnQryVeterinario.setBackground(Color.decode("#F8F2E7"));
        btnQryVeterinario.setSize(100, 62);
        section2.add(btnQryVeterinario);

        TextDisplay tPagar = new TextDisplay("¿Pagar? (Opcional)");
        tPagar.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tPagar.setSize(tPagar.getPreferredSize());
        section2.add(tPagar);

        btnPagar = new Button("Pagar");
        btnPagar.setBackground(Color.decode("#F8F2E7"));
        btnPagar.setSize(100, 62);
        section2.add(btnPagar);

        btnAgendar = new Button("Agendar");
        btnAgendar.setSize(275, 50);
        section2.add(btnAgendar);

        btnRegresar = new Button("Regresar");
        btnRegresar.setBackground(Color.decode("#F8F2E7"));
        btnRegresar.setForeground(Color.red);
        btnRegresar.setSize(250, 50);

        section2.add(btnRegresar);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mainPanel.setSize(getContentPane().getSize());
                mainPanel.setBackground(Color.white);

                section2.setSize(getWidth() - 50, getHeight() - 75);
                section2.setBackground(Color.white);

                tFecha.setLocation(section2, Positions.TOP,-520,30);
                picker.setLocation(90,70);

                tHora.setLocation(section2, Positions.TOP,-530,150);
                iHora.setLocation(90,190);

                tInformacion.setLocation(section2, Positions.TOP,-490,270);
                iInformacion.setLocation(90,310);

                tNombreMascota.setLocation(section2, Positions.TOP,305,30);
                iNombreMascota.setLocation(section2, Positions.TOP,330,70);

                btnQryNombre.setLocation(1210,70);

                tNombreVeterinario.setLocation(section2, Positions.TOP,305,150);
                iNombreVeterinario.setLocation(section2, Positions.TOP,330,190);

                btnQryVeterinario.setLocation(1210,190);

                tPagar.setLocation(section2, Positions.TOP,280,300);
                btnPagar.setLocation(1210,310);

                btnAgendar.setLocation(section2,Positions.BOTTOM,0,-100);

                btnRegresar.setLocation(20, section2.getHeight() - btnRegresar.getHeight() - 20);
            }
        });
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

            new AltaCita().setVisible(true);
        });
    }
}
