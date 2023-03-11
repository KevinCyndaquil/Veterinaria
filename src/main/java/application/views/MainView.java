package application.views;


import application.views.components.Banner;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainView extends CustomJFrame {
    private JPanel mainPanel;

    public MainView() {
        super("Bienvenido");
    }

    @Override
    public void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setSize(ResponsiveDimension.getNextResolution());
        mainPanel.setLocation(0, 0);
        mainPanel.setLayout(null);
        add(mainPanel);

        Banner banner = new Banner();
        mainPanel.add(banner);

        JLabel welcomeJL = new JLabel("Bienvenido");
        welcomeJL.setFont(getFontsLoader().load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 36f));
        welcomeJL.setSize(313, 60);
        mainPanel.add(welcomeJL);

        JLabel loadingJL = new JLabel();
        loadingJL.setIcon(new ImageIcon("src/main/resources/images/loading.gif"));
        loadingJL.setSize(loadingJL.getIcon().getIconWidth(), loadingJL.getIcon().getIconHeight());
        mainPanel.add(loadingJL);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("content\t" + getContentPane().getSize());
                System.out.println("frame\t" + getSize());
                //if size window is bigger than HD then resize mainPanel to window size
                mainPanel.setSize(getContentPane().getSize());
                banner.setLocation(mainPanel, Positions.CENTER, 0, -120);
                welcomeJL.setLocation(mainPanel.getWidth() / 2 - welcomeJL.getWidth() / 2 + 60, mainPanel.getHeight() / 2 - welcomeJL.getHeight() / 2 + 200);
                loadingJL.setLocation(mainPanel.getWidth() / 2 - loadingJL.getWidth() / 2, mainPanel.getHeight() / 2 - loadingJL.getHeight() / 2 + 280);
            }
        });
    }


    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing
            new MainView().setVisible(true);
        });
    }
}
