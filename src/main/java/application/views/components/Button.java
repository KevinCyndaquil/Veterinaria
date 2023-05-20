package application.views.components;

import application.views.components.abstracts.CustomJButton;
import lombok.Setter;
import org.jdesktop.swingx.border.MatteBorderExt;
import application.views.utils.Fonts;


import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class Button extends CustomJButton {
    @Setter
    private int arcSize = 20;
    @Setter
    private Color hoverBackgroundColor = Color.decode("#B4A4CD");
    public Button(String text) {
        super();
        setText(text);
        setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 21f));
        setSize(180, 50);

        setUI(new BasicButtonUI());
        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorder(null);
        setRolloverEnabled(true);

        /*addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(new MatteBorderExt(1, 1, 1, 1, Color.BLACK));
            }
            @Override
            public void focusLost(FocusEvent e) {
                setBorder(null);
            }
        });*/

        addChangeListener(e -> repaint());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ButtonModel buttonModel = getModel();
        if (hasFocus()) {
            g2.setColor(Color.decode("#F8F2E7"));
        } else if (buttonModel.isRollover()) {
            g2.setColor(hoverBackgroundColor);
        } else {
            g2.setColor(getBackground());
        }

        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcSize, arcSize));

        g2.dispose();
        super.paintComponent(g);
    }

}
