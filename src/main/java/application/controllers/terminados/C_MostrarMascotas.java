package application.controllers.terminados;

import application.MessageDialog;
import application.controllers.abstracts.C_AltaPasar;
import application.controllers.abstracts.C_MostrarParaAlta;
import application.controllers.terminados.C_AltaMascota;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Mascotas;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class C_MostrarMascotas extends C_MostrarParaAlta<Mascotas> {

    public C_MostrarMascotas(Mascotas mascotas, C_AltaPasar<?, ?> alta) {
        super(new Object[]{
                "ID",
                "Nombre",
                "Fecha Nacimiento",
                "Sexo",
                "Dueño",
                "Raza"
        }, mascotas, alta);
    }

    @Override
    public void mostrar() {
        Find<Mascotas> findMascota = new Repository<>(new Postgres());
        List<Object[]> rows;

        try {
            rows = findMascota.find(m).stream()
                    .map(Mascotas.class::cast)
                    .map(m -> new Object[]{
                            m.getId_mascota(),
                            m,
                            m.getFecha_nacimiento(),
                            m.getSexo(),
                            m.getPropietario().getNombre(),
                            m.getRaza().nombre()})
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
        int selectedRow = view.table.getSelectedRow();

        if (selectedRow == -1) {
            MessageDialog.stupidMessage(
                    view,
                    "Selecciona primero una mascota por favor"
            );
            return;
        }

        Mascotas mascota = (Mascotas) view.table.getValueAt(selectedRow, 1);

        if (alta.pasar(mascota)) {
            MessageDialog.successMessage(
                    view,
                    "Mascota seleccionada %s correctamente"
                            .formatted(mascota));
            view.dispose();
        } else {
            MessageDialog.errorMessage(
                    view,
                    "Ocurrió un error al pasar la mascota"
            );
        }
    }

    @Override
    public void lanzarAlta(ActionEvent event) {
        new C_AltaMascota();
    }
}
