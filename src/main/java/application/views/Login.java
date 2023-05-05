package application.views;

import application.views.components.*;
import application.views.components.Button;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Fonts;
import application.views.utils.Positions;
import application.views.utils.ResponsiveDimension;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Login extends CustomJFrame {
    private JPanel mainPanel;

    public Login() {
        super("Login");
    }

    @Override
    public void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setSize(ResponsiveDimension.getNextResolution().getSize());
        mainPanel.setLocation(0, 0);
        mainPanel.setLayout(null);
        add(mainPanel);

        Banner banner = new Banner();
        banner.setOpaque(false);
        mainPanel.add(banner);

        PerfilMarco imgProfile = new PerfilMarco();
        imgProfile.setOpaque(false);
        mainPanel.add(imgProfile);

        TextDisplay userJL = new TextDisplay("Member Login");
        userJL.setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 36f));
        userJL.setSize(313, 60);
        mainPanel.add(userJL);

        InputText userJT = new InputText("Username");
        mainPanel.add(userJT);

        InputPassword passwordJT = new InputPassword("Password");
        mainPanel.add(passwordJT);

        Button loginJB = new Button("Login");
        mainPanel.add(loginJB);


        FondoLogin fondoJL = new FondoLogin(new ImageIcon("src/main/resources/images/fondoLogin_1920.png"));
        fondoJL.setLocation(0, 0);
        mainPanel.add(fondoJL);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //System.out.println("content\t" + getContentPane().getSize());
                //System.out.println("frame\t" + getSize());
                //if size window is bigger than HD then resize mainPanel to window size
                mainPanel.setSize(getContentPane().getSize());
                fondoJL.setSize(mainPanel.getSize());
                banner.setLocation(mainPanel, Positions.LEFT, 190, 0);
                imgProfile.setLocation(mainPanel, Positions.RIGHT, -235, -250);
                userJL.setLocation(mainPanel, Positions.RIGHT, -150, -100);
                userJT.setLocation(mainPanel, Positions.RIGHT, -135, -10);
                passwordJT.setLocation(mainPanel, Positions.RIGHT, -135, 60);
                loginJB.setLocation(mainPanel, Positions.RIGHT, -200, 150);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing
            new Login().setVisible(true);

        });
    }


}
