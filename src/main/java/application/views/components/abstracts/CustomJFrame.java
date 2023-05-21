package application.views.components.abstracts;





import application.views.utils.ResponsiveDimension;

import javax.swing.*;

public abstract class CustomJFrame extends AbstractJFrame {

    public CustomJFrame(String title) {
        super(title);
        setResizable(false);

        try {
            //getContentPane().setPreferredSize(ResponsiveDimension.getNextResolution().getSize());
            setPreferredSize(ResponsiveDimension.getNextResolution().getSize());
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(this,"Su resolución no esta permitida para el aplicativo");
            System.exit(1);
        }

        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
