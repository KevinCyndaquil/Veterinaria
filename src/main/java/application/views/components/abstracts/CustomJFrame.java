package application.views.components.abstracts;





import application.views.utils.ResponsiveDimension;

import javax.swing.*;

public abstract class CustomJFrame extends JFrame {

    public CustomJFrame(String title) {
        super(title);
        setLayout(null);
        try {
            //getContentPane().setPreferredSize(ResponsiveDimension.getNextResolution().getSize());
            setPreferredSize(ResponsiveDimension.getNextResolution().getSize());
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(this,"Su resolucion no esta permitida para el aplicativo");
            System.exit(1);
        }

        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
    }

    public abstract void initComponents();

}
