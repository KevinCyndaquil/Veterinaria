package application.controllers.terminados;

import application.MessageDialog;
import application.controllers.abstracts.C_AltaPasar;
import application.controllers.abstracts.C_MostrarParaAlta;
import application.controllers.terminados.C_AltaPropietarios;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Personas;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class C_MostrarPropietarios extends C_MostrarParaAlta<Personas> {

    public C_MostrarPropietarios(Personas propietario, C_AltaPasar<?, ?> alta) {
        super(new Object[]{
                "ID",
                "RFC",
                "Nombre",
                "Apellido paterno",
                "Apellido materno",
                "No Cuenta"}, propietario, alta);
    }

    @Override
    public void mostrar() {
        Find<Personas> findPersonas = new Repository<>(new Postgres());
        List<Object[]> rows;

        try {
            rows = findPersonas.find(m).stream()
                    .map(Personas.class::cast)
                    .map(p -> new Object[]{
                            p.getId_persona(),
                            p.getRfc(),
                            p,
                            p.getApellido_p(),
                            p.getApellido_m(),
                            p.getNo_cuenta()})
                    .toList();
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Ocurrió un error al buscar las coincidencias del objeto %s".formatted(m));
            throw new RuntimeException(e);
        }

        DefaultTableModel model = (DefaultTableModel) view.table.getModel();
        model.setRowCount(0);
        rows.forEach(model::addRow);
    }

    @Override
    public void obtener(ActionEvent event) {
        int rowSelected = view.table.getSelectedRow();

        if (rowSelected == -1) {
            MessageDialog.stupidMessage(
                    view,
                    "Selecciona primero un propietario por favor"
            );
            return;
        }

        Personas propietario = (Personas) view.table.getValueAt(rowSelected, 2);

        if (alta.pasar(propietario)) {
            MessageDialog.successMessage(
                    view,
                    "Propietario seleccionado %s correctamente"
                            .formatted(propietario));
            view.dispose();
        } else {
            MessageDialog.errorMessage(
                    view,
                    "Ocurrió un error al pasar el propietario"
            );
        }
    }

    @Override
    public void lanzarAlta(ActionEvent event) {
        new C_AltaPropietarios();
        mostrar();
    }
}
