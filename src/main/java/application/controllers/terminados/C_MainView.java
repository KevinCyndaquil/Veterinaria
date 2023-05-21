package application.controllers.terminados;

import application.views.terminadas.MainView;

import javax.swing.*;

public class C_MainView {
    private MainView view;
    private C_Login c_login;

    public C_MainView() {
        view = new MainView();
    }
    public void showView() {
        view.setVisible(true);


        Timer timer = new Timer(700, e -> {
            view.setVisible(false);
            if(c_login == null) {
                c_login = new C_Login();
            }
            view = null;
            System.out.println("hola");
        });
        timer.setRepeats(false);
        timer.start();
    }
}