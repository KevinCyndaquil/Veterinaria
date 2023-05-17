package application.views;

import application.views.components.BarLeft;
import application.views.components.Button;
import application.views.components.SectionRound;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Positions;
import application.views.utils.ResponsiveDimension;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class MenuCitas extends CustomJFrame {
    private JPanel mainPanel;
    public BarLeft barLeft;
    public Button btnRegresar;
    public Button btnaltacitas;
    public Button btnReportes;
    public Button btnModificaciones;
    public MenuCitas() {
        super("Menu Citas");
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


        btnaltacitas = new Button("Alta Citas");
        btnaltacitas.setBackground(Color.decode("#F8F2E7"));
        btnaltacitas.setSize(350, 110);
        section2.add(btnaltacitas);

        btnReportes = new Button("Reportes");
        btnReportes.setBackground(Color.decode("#F8F2E7"));
        btnReportes.setSize(350, 110);
        section2.add(btnReportes);

        btnModificaciones = new Button("Modificaciones");
        btnModificaciones.setBackground(Color.decode("#F8F2E7"));
        btnModificaciones.setSize(350, 110);
        section2.add(btnModificaciones);

        btnRegresar = new Button("Regresar");
        btnRegresar.setBackground(Color.decode("#F8F2E7"));
        btnRegresar.setForeground(Color.red);
        btnRegresar.setSize(250, 50);
        section2.add(btnRegresar);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainPanel.setSize(getContentPane().getSize());
                mainPanel.setBackground(Color.white);

                section2.setSize(1391, 989);
                section2.setBackground(Color.white);

                btnaltacitas.setLocation(section2, Positions.TOP,-300,90);
                btnReportes.setLocation(section2, Positions.TOP, 300, 90);
                btnModificaciones.setLocation(section2, Positions.CENTER, 0, -150);
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

            new MenuCitas().setVisible(true);

        });
    }
}
