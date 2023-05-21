package application.controllers.terminados;

import application.MessageDialog;
import application.controllers.C_AltaRaza;
import application.controllers.abstracts.*;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Razas;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class C_MostrarRazas extends C_MostrarParaAlta<Razas> {

    public C_MostrarRazas(Razas razas, C_AltaPasar<?, ?> alta) {
        super(new Object[]{
                "ID",
                "Nombre",
                "Total Adopción",
                "Animal"
        }, razas, alta);
    }

    @Override
    public void mostrar() {
        Find<Razas> findRazas = new Repository<>(new Postgres());
        List<Object[]> rows;

        try {
            rows = findRazas.find(m).stream()
                    .map(Razas.class::cast)
                    .map(r -> new Object[]{
                            r.id_raza(),
                            r,
                            r.total_adopcion(),
                            r.animal()})
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
                    "Selecciona primero una raza por favor"
            );
            return;
        }

        Razas razas = (Razas) view.table.getValueAt(rowSelected, 1);

        if (alta.pasar(razas)) {
            MessageDialog.successMessage(
                    view,
                    "Raza seleccionada %s correctamente"
                            .formatted(razas));
            view.dispose();
        } else {
            MessageDialog.errorMessage(
                    view,
                    "Ocurrió un error al pasar la raza"
            );
        }
    }

    @Override
    public void lanzarAlta(ActionEvent event) {
        new C_AltaRaza();
    }
}
