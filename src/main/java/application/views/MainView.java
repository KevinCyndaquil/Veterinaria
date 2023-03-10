package application.views;


import application.views.utils.Fonts;
import application.views.utils.FontsLoader;
import application.views.utils.ResponsiveDimension;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private FontsLoader fontsLoader;

    public MainView() {
        setTitle("Bienvenido");
        setLayout(null);
        getContentPane().setPreferredSize(ResponsiveDimension.getResolution());


        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

    }

    private void initComponents() {
        fontsLoader = new FontsLoader();

        mainPanel = new JPanel();
        mainPanel.setSize(ResponsiveDimension.getResolution());
        mainPanel.setLocation(0, 0);
        mainPanel.setLayout(null);
        add(mainPanel);

        JLabel logoJL = new JLabel();
        logoJL.setIcon(new ImageIcon("src/main/resources/images/logo.png"));
        logoJL.setSize(logoJL.getIcon().getIconWidth(), logoJL.getIcon().getIconHeight());
        logoJL.setLocation(mainPanel.getWidth() / 2 - logoJL.getWidth() / 2, mainPanel.getHeight() / 2 - logoJL.getHeight() / 2 - 120);
        mainPanel.add(logoJL);

        JLabel nombreJL = new JLabel("Veterinaria Vida");
        nombreJL.setFont(fontsLoader.load(Fonts.CHERISH).deriveFont(96f));
        nombreJL.setSize(764, 128);
        nombreJL.setLocation(mainPanel.getWidth() / 2 - nombreJL.getWidth() / 2 + 150, mainPanel.getHeight() / 2 - nombreJL.getHeight() / 2 + 50);
        mainPanel.add(nombreJL);

        JLabel welcomeJL = new JLabel("Bienvenido");
        welcomeJL.setFont(fontsLoader.load(Fonts.MONSERRAT).deriveFont(Font.BOLD,36f));
        welcomeJL.setSize(313, 60);
        welcomeJL.setLocation(mainPanel.getWidth() / 2 - welcomeJL.getWidth() / 2 + 60, mainPanel.getHeight() / 2 - welcomeJL.getHeight() / 2 + 200);
        mainPanel.add(welcomeJL);

        JLabel loadingJL = new JLabel();
        loadingJL.setIcon(new ImageIcon("src/main/resources/images/loading.gif"));
        loadingJL.setSize(loadingJL.getIcon().getIconWidth(), loadingJL.getIcon().getIconHeight());
        loadingJL.setLocation(mainPanel.getWidth() / 2 - loadingJL.getWidth() / 2, mainPanel.getHeight() / 2 - loadingJL.getHeight() / 2 + 280);
        mainPanel.add(loadingJL);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("content\t" + getContentPane().getSize());
                System.out.println("frame\t" + getSize());
                //if size window is bigger than HD then resize mainPanel to window size
                mainPanel.setSize(getContentPane().getSize());
                logoJL.setLocation(mainPanel.getWidth() / 2 - logoJL.getWidth() / 2, mainPanel.getHeight() / 2 - logoJL.getHeight() / 2 - 120);
                nombreJL.setLocation(mainPanel.getWidth() / 2 - nombreJL.getWidth() / 2 + 150, mainPanel.getHeight() / 2 - nombreJL.getHeight() / 2 + 50);
                welcomeJL.setLocation(mainPanel.getWidth() / 2 - welcomeJL.getWidth() / 2 + 60, mainPanel.getHeight() / 2 - welcomeJL.getHeight() / 2 + 200);
            }
        });
    }


    public static void main(String[] args) {


        // resto del código que utiliza AWT o Swing
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            new MainView().setVisible(true);
        });
    }
}
