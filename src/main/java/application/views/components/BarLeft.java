package application.views.components;


import application.views.utils.Fonts;
import application.views.utils.ResponsiveDimension;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class BarLeft extends SectionRound{
    public Button btncompras;
    public Button btncitas;
    public Button btnreportes;
    public Button btnadmins;

    public Point compXY;
    public Point compWH;
    public Rectangle buttonSpace;
    public Rectangle labelSpace;

    public BarLeft() {
        Dimension screenDimension = ResponsiveDimension.getNextResolution().getSize();

        buttonSpace = new Rectangle(121, 13, 225, 255);
        labelSpace = new Rectangle(67, 25, buttonSpace.width, buttonSpace.height);

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

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                System.out.println(getSize());

                compXY = new Point(getWidth() / 20, getHeight() / 20);
                compWH = new Point(getWidth() - (compXY.x * 2), getHeight() / 3 - (compXY.y * 2));

                System.out.println(compXY);
                System.out.println(compWH);

                btncompras.setBounds(compXY.x + 100, compXY.y, compWH.x - 100, compWH.y);
                tcompras.setBounds(compXY.x, btncompras.getY(), 100, compWH.y);

                btnreportes.setBounds(compXY.x + 100, compXY.y * 3 + compWH.y, compWH.x - 100, compWH.y);
                treportes.setBounds(compXY.x, btnreportes.getY(), 100, btnreportes.getHeight());

                btncitas.setBounds(compXY.x + 100, compXY.y * 5 + compWH.y * 2, compWH.x - 100, compWH.y);
                tcitas.setBounds(compXY.x, btncitas.getY(), 100, btncitas.getHeight());

                System.out.println(btncompras.getBounds());
            }
        });
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
