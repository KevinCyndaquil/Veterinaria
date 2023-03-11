package application.views;

import application.views.components.abstracts.CustomJFrame;

public class Login extends CustomJFrame {
    public Login() {
        super("Login");
    }

    @Override
    public void initComponents() {

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
