package application.views.components;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BarLeft extends SectionRound{
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public BarLeft() {
        setSize(466, 1033);
        setBackground(Color.decode("#A0CDC8"));

        button1 = new Button("");
        button1.setArcSize(225);
        button1.setSize(225, 225);
        button1.setIcon(new ImageIcon("src/main/resources/images/btn1.png"));
        button1.setLocation(121, 13);
        add(button1);

        button2 = new Button("");
        button2.setArcSize(225);
        button2.setSize(225, 225);
        button2.setIcon(new ImageIcon("src/main/resources/images/btn2.png"));
        button2.setLocation(121, 258);
        add(button2);

        button3 = new Button("");
        button3.setArcSize(225);
        button3.setSize(225, 225);
        button3.setIcon(new ImageIcon("src/main/resources/images/btn3.png"));
        button3.setLocation(121, 506);
        add(button3);

        button4 = new Button("");
        button4.setArcSize(225);
        button4.setSize(225, 225);
        button4.setIcon(new ImageIcon("src/main/resources/images/btn4.png"));
        button4.setLocation(121, 776);
        add(button4);


        JLabel tcompras = new JLabel(new ImageIcon("src/main/resources/images/Compras.png"));
        tcompras.setSize(tcompras.getIcon().getIconWidth(), tcompras.getIcon().getIconHeight());
        tcompras.setLocation(67, 25);
        add(tcompras);

        JLabel tcitas = new JLabel(new ImageIcon("src/main/resources/images/Citas.png"));
        tcitas.setSize(tcitas.getIcon().getIconWidth(), tcitas.getIcon().getIconHeight());
        tcitas.setLocation(67, 300);
        add(tcitas);

        JLabel treportes = new JLabel(new ImageIcon("src/main/resources/images/Reportes.png"));
        treportes.setSize(treportes.getIcon().getIconWidth(), treportes.getIcon().getIconHeight());
        treportes.setLocation(67, 500);
        add(treportes);

        JLabel tadmins = new JLabel(new ImageIcon("src/main/resources/images/Admins.png"));
        tadmins.setSize(tadmins.getIcon().getIconWidth(), tadmins.getIcon().getIconHeight());
        tadmins.setLocation(67, 800);
        add(tadmins);

        JLabel hueso = new JLabel(new ImageIcon("src/main/resources/images/hueso.png"));
        hueso.setSize(hueso.getIcon().getIconWidth(), hueso.getIcon().getIconHeight());
        hueso.setLocation(310, 935);
        add(hueso);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, getRadius(), getRadius());
        graphics.setColor(Color.decode("#82A7A2"));
        graphics.setStroke(new BasicStroke(1));
        //Esto mejora (alisa) es aspecto de renderizado del borde
        graphics.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        graphics.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, getRadius(), getRadius()));
        graphics.dispose();
    }
}
