package application.views.components.abstracts;

import application.views.utils.FontsLoader;
import application.views.utils.ResponsiveDimension;
import lombok.Getter;

import javax.swing.*;

public abstract class CustomJFrame extends JFrame {
    @Getter
    private final FontsLoader fontsLoader;

    public CustomJFrame(String title) {
        super(title);
        fontsLoader = new FontsLoader();
        setLayout(null);
        getContentPane().setPreferredSize(ResponsiveDimension.getNextResolution().getSize());
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public abstract void initComponents();

}
