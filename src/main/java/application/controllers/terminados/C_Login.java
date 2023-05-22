package application.controllers.terminados;

import application.MessageDialog;
import application.controllers.C_Menu;
import application.database.Postgres;
import application.views.terminadas.Login;
import application.controllers.abstracts.ViewerController;

import java.sql.SQLException;

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
            MessageDialog.successMessage(
                    view,
                    "Conectado con exito con usuario %s".formatted(user));

            new C_Menu();
            view.dispose();
        } catch (RuntimeException | SQLException ex ){
            MessageDialog.stupidMessage(view, "Error, usuario o contraseña incorrectos");
            view.userJT.setText("");
            view.passwordJT.setText("");
            view.userJT.requestFocus();
            //ex.printStackTrace();
        }
    }

    @Override
    public void initEvents() {
        view.loginJB.addActionListener(e -> validarUsuario());
    }
}
