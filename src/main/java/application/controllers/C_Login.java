package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Generic;
import application.database.Postgres;
import application.views.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.List;

public class C_Login extends C_Generic<Login> implements ActionListener {

    public C_Login() {
        super(Login.class);
    }

    @Override
    public void showView() {
        view.setVisible(true);
        view.loginJB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==view.loginJB){
            try(  Postgres postgres = new Postgres()){
                String user = view.userJT.getText();
                String password = String.copyValueOf(view.passwordJT.getPassword());

                postgres.setUser(user, password);
                postgres.connectTo();
                MessageDialog.successMessage(view, "Conectado con exito");

                C_Menu c_menu = new C_Menu();
                view.dispose();
                c_menu.showView();
            }catch (Exception ex){
                MessageDialog.errorMessage(view, "Error");
                view.userJT.setText("");
                view.passwordJT.setText("");
                view.userJT.requestFocus();
            }
        }
    }
}
