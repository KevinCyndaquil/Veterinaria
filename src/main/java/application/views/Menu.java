package application.views;

import application.views.components.BarLeft;
import application.views.components.SectionRound;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.ResponsiveDimension;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Menu extends CustomJFrame {
    private JPanel mainPanel;
    public BarLeft barLeft;

    public Menu() {
        super("Menu");
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

        JLabel fondo = new JLabel(new ImageIcon("src/main/resources/images/fondo_menu.png"));
        fondo.setSize(fondo.getIcon().getIconWidth(), fondo.getIcon().getIconHeight());
        fondo.setLocation(0, 0);
        mainPanel.add(fondo);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mainPanel.setSize(getContentPane().getSize());
                mainPanel.setBackground(Color.white);


                fondo.setLocation(402, 14);
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

            new Menu().setVisible(true);
        });
    }


}
