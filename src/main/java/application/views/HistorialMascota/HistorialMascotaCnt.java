package application.views.HistorialMascota;

import application.MessageDialog;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Mascotas;
import application.models.entidades.Personas;
import application.views.ChooserMascotaCnt;
import application.views.components.interfaces.ComboBoxController;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class HistorialMascotaCnt extends ViewerController<HistorialMascotaView> implements
        ComboBoxController,
        TableController {
    public HistorialMascotaCnt() {
        super(new HistorialMascotaView());

        refreshComboBoxes();
    }

    @Override
    public void initEvents() {
        view.btnConsultar.addActionListener(e -> consultar());
    }

    public void consultar() {
        Find<Mascotas> findMascotas = new Repository<>(new Postgres());
        Integer id_mascota;
        try {
            id_mascota = (view.fldIdMascota.getText().equals("")) ?
                    null :
                    Integer.parseInt(view.fldIdMascota.getText());
        } catch (NumberFormatException e) {
            MessageDialog.stupidMessage(
                    view,
                    "por favor, la id de la mascota debe ser un número");
            return;
        }
        String nombre_mascota = (view.fldNombreMascota.getText().equals("")) ? null :
                view.fldNombreMascota.getText().toUpperCase();
        Personas propietario = (Personas) view.cmbPropietarioMascota.getSelectedItem();
        Mascotas mascota = new Mascotas(
                id_mascota,
                nombre_mascota,
                null,
                null,
                null,
                propietario);

        ChooserMascotaCnt chooser = new ChooserMascotaCnt(
                (DefaultTableModel) view.tblHistorial.getModel());

        try {
            findMascotas.find(mascota).stream()
                    .map(Mascotas.class::cast)
                    .forEach(chooser::agregarMascota);
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Error al leer las mascotas con las siguientes características:\nid: %s\nnombre: %s\npropietario: %s"
                                    .formatted(id_mascota, nombre_mascota, propietario));
            throw new RuntimeException(e);
        }

    }

    @Override
    public void refreshComboBoxes() {
        Find<Personas> findPersonas = new Repository<>(new Postgres());

        try {
            findPersonas.find(new Personas(null)).stream()
                    .map(Personas.class::cast)
                    .forEach(view.cmbPropietarioMascota::addItem);
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Ocurrió un error al leer los propietarios de la base de datos");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void refreshTables() {

    }

    public static void main(String[] args) {
        new HistorialMascotaCnt();
    }


}
