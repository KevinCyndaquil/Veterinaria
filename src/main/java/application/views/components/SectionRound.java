package application.views.components;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SectionRound extends JPanel {
    @Setter @Getter
    private int radius = 40;

    public SectionRound(){
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        graphics.setColor(new Color(0,0,0,25));
        graphics.setStroke(new BasicStroke(3));
        //Esto mejora (alisa) es aspecto de renderizado del borde
        graphics.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        graphics.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, radius, radius));
        graphics.dispose();
//
    }

}
