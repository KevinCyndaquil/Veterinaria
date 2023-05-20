package application.controllers;

import application.MessageDialog;
import application.database.Postgres;
import application.views.Login;
import application.controllers.abstracts.ViewerController;

public class C_Login extends ViewerController<Login> {

    public C_Login() {
        super(new Login());
    }

    public void validarUsuario() {
        try (Postgres postgres = new Postgres()) {

            String user = view.userJT.getText();
            String password = String.copyValueOf(view.passwordJT.getPassword());

            postgres.setUser(user, password);
            postgres.connectTo();
            MessageDialog.successMessage(view, "Conectado con exito");

            view.dispose();
            new C_Menu();
        } catch (Exception ex){
            MessageDialog.errorMessage(view, "Error, usuario o contraseña incorrectos");
            view.userJT.setText("");
            view.passwordJT.setText("");
            view.userJT.requestFocus();
        }
    }

    @Override
    public void initEvents() {
        view.loginJB.addActionListener(e -> validarUsuario());
    }
}
