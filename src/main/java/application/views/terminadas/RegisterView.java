package application.views.terminadas;
/*
import application.views.components.abstracts.CustomJFrame;

import javax.swing.*;
import java.util.List;

public class RegisterView extends CustomJFrame {
    public JPanel panel;

    public final List<String> datas;
    public List<JLabel> lblDatas;
    public List<JTextField> fldDatas;

    public RegisterView(String[] datas) {
        super("register");

        this.datas = List.of(datas);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.NORMAL);

        initComponents();
    }

    @Override
    public void initComponents() {
        if (datas == null) return;

        panel = new JPanel(null);
        getContentPane().add(panel);

        //init list
        lblDatas = datas.stream().map(JLabel::new).toList();
        lblDatas.forEach(f -> {
            f.setSize(f.getPreferredSize());
            panel.add(f);
        });
        fldDatas = datas.stream().map(JTextField::new).toList();


    }
}*/
