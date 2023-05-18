package application.views;

import application.views.components.BarLeft;
import application.views.components.SectionRound;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Positions;
import application.views.utils.ResponsiveDimension;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Menu extends CustomJFrame {
    private JPanel mainPanel;
    private JPanel switchPanel;
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

        switchPanel = new JPanel(null);
        switchPanel.setBackground(Color.WHITE);
        mainPanel.add(switchPanel);

        barLeft = new BarLeft();
        barLeft.setLocation(10, 10);
        //barLeft.setOpaque(false);
        mainPanel.add(barLeft);

        ImageIcon imgFondo = new ImageIcon("src/main/resources/images/fondo_menu.png");
        JLabel fondo = new JLabel(imgFondo);
        fondo.setSize(fondo.getIcon().getIconWidth(), fondo.getIcon().getIconHeight());
        fondo.setLocation(0, 0);
        switchPanel.add(fondo);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mainPanel.setSize(getContentPane().getSize());
                mainPanel.setBackground(Color.white);

                mainPanel.setSize(getContentPane().getSize());
                mainPanel.setBackground(Color.white);

                barLeft.setSize(getWidth() / 4, getHeight() - 60);

                switchPanel.setBounds(
                        barLeft.getX() + barLeft.getWidth() + 20,
                        barLeft.getY(),
                        getWidth() - (barLeft.getX() + barLeft.getWidth() + 20),
                        getHeight() - barLeft.getY());

                fondo.setBounds(0, 0, switchPanel.getWidth(), switchPanel.getHeight());
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
