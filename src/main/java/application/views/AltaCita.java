package application.views;

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
    public BarLeft barLeft;
    public Button btnRegresar;
    public Button btnagendar;
    public Button btnqryveterinario;
    public Button btnpagar;
    public Button btnqrynombre;
    public InputText inombremascota;
    public AltaCita() {
        super("Alta Cita");
    }

    @Override
    public void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setSize(ResponsiveDimension.getNextResolution().getSize());
        mainPanel.setLocation(0, 0);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.white);
        add(mainPanel);

        barLeft = new BarLeft();
        barLeft.setLocation(10, 10);
        //barLeft.setOpaque(false);
        mainPanel.add(barLeft);

        SectionRound section2 = new SectionRound();
        section2.setLocation(500, 50);
        //barLeft.setOpaque(false);
        mainPanel.add(section2);

        TextDisplay tfecha = new TextDisplay("Fecha de la cita");
        tfecha.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tfecha.setSize(tfecha.getPreferredSize());
        section2.add(tfecha);

        JXDatePicker picker = new JXDatePicker();
        picker.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        picker.setDate(new java.util.Date());
        picker.setFormats(new java.text.SimpleDateFormat("dd/MM/yyyy"));
        picker.setSize(300, 62);
        picker.getComponent(0).setBackground(Color.white);
        section2.add(picker);

        TextDisplay thora = new TextDisplay("Hora de la cita");
        thora.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        thora.setSize(thora.getPreferredSize());
        section2.add(thora);

        InputText ihora = new InputText("hh:mm");
        ihora.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        ihora.setSize(300, 62);
        section2.add(ihora);

        TextDisplay tinformacion = new TextDisplay("Informacion adicional");
        tinformacion.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tinformacion.setSize(tinformacion.getPreferredSize());
        section2.add(tinformacion);

        JTextArea iinformacion = new JTextArea();
        iinformacion.setBackground(Color.white);
        iinformacion.setBorder(BorderFactory.createLineBorder(Color.black));
        iinformacion.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        iinformacion.setSize(420, 149);
        section2.add(iinformacion);

        TextDisplay tnombremascota = new TextDisplay("Nombre de la mascota");
        tnombremascota.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tnombremascota.setSize(tnombremascota.getPreferredSize());
        section2.add(tnombremascota);


        inombremascota = new InputText("Nombre");
        inombremascota.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        inombremascota.setSize(300, 62);
        section2.add(inombremascota);


        btnqrynombre = new Button("Buscar");
        btnqrynombre.setBackground(Color.decode("#F8F2E7"));
        btnqrynombre.setSize(100, 62);
        section2.add(btnqrynombre);

        TextDisplay tnombreveterinario = new TextDisplay("Nombre del veterinario");
        tnombreveterinario.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tnombreveterinario.setSize(tnombreveterinario.getPreferredSize());
        section2.add(tnombreveterinario);

        InputText inombreveterinario = new InputText("Nombre");
        inombreveterinario.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        inombreveterinario.setSize(300, 62);
        section2.add(inombreveterinario);

        btnqryveterinario = new Button("Buscar");
        btnqryveterinario.setBackground(Color.decode("#F8F2E7"));
        btnqryveterinario.setSize(100, 62);
        section2.add(btnqryveterinario);

        TextDisplay tpagar = new TextDisplay("Pagar? (Opcional)");
        tpagar.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tpagar.setSize(tpagar.getPreferredSize());
        section2.add(tpagar);

        btnpagar = new Button("Pagar");
        btnpagar.setBackground(Color.decode("#F8F2E7"));
        btnpagar.setSize(100, 62);
        section2.add(btnpagar);

        btnagendar = new Button("Agendar");
        btnagendar.setSize(275, 50);
        section2.add(btnagendar);

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

                section2.setSize(1391, 989);
                section2.setBackground(Color.white);

                tfecha.setLocation(section2, Positions.TOP,-520,30);
                picker.setLocation(90,70);

                thora.setLocation(section2, Positions.TOP,-530,150);
                ihora.setLocation(90,190);

                tinformacion.setLocation(section2, Positions.TOP,-490,270);
                iinformacion.setLocation(90,310);

                tnombremascota.setLocation(section2, Positions.TOP,305,30);
                inombremascota.setLocation(section2, Positions.TOP,330,70);

                btnqrynombre.setLocation(1210,70);

                tnombreveterinario.setLocation(section2, Positions.TOP,305,150);
                inombreveterinario.setLocation(section2, Positions.TOP,330,190);

                btnqryveterinario.setLocation(1210,190);

                tpagar.setLocation(section2, Positions.TOP,280,300);
                btnpagar.setLocation(1210,310);

                btnagendar.setLocation(section2,Positions.BOTTOM,0,-300);

                btnRegresar.setLocation(section2, Positions.BOTTOM, -530, -40);
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