package application.views.components;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BarLeft extends SectionRound{
    public Button btncompras;
    public Button btncitas;
    public Button btnreportes;
    public Button btnadmins;
    public BarLeft() {
        setSize(466, 1033);
        setBackground(Color.decode("#A0CDC8"));

        btncompras = new Button("");
        btncompras.setArcSize(225);
        btncompras.setSize(225, 225);
        btncompras.setIcon(new ImageIcon("src/main/resources/images/btn1.png"));
        btncompras.setLocation(121, 13);
        add(btncompras);

        btncitas = new Button("");
        btncitas.setArcSize(225);
        btncitas.setSize(225, 225);
        btncitas.setIcon(new ImageIcon("src/main/resources/images/btn2.png"));
        btncitas.setLocation(121, 258);
        add(btncitas);

        btnreportes = new Button("");
        btnreportes.setArcSize(225);
        btnreportes.setSize(225, 225);
        btnreportes.setIcon(new ImageIcon("src/main/resources/images/btn3.png"));
        btnreportes.setLocation(121, 506);
        add(btnreportes);

        btnadmins = new Button("");
        btnadmins.setArcSize(225);
        btnadmins.setSize(225, 225);
        btnadmins.setIcon(new ImageIcon("src/main/resources/images/btn4.png"));
        btnadmins.setLocation(121, 776);
        add(btnadmins);


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
