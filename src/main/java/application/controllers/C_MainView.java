package application.controllers;

import application.Main;
import application.controllers.abstracts.C_Generic;
import application.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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