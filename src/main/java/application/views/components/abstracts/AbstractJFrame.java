package application.views.components.abstracts;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractJFrame extends JFrame {

    public AbstractJFrame(String title) throws HeadlessException {
        super(title);

        setLayout(null);
    }

    public abstract void initComponents();
}
