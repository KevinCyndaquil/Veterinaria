package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.C_Alta;
import application.controllers.abstracts.C_Mostrar;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.entidades.Mascotas;
import application.views.components.ShowerTable;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class C_MostrarMascotas extends C_Mostrar<ShowerTable, Mascotas>{
    private final C_Alta<?, ?> alta;

    public C_MostrarMascotas(Mascotas mascotas, C_Alta<?, ?> alta) {
        super(new ShowerTable(new Object[]{
                "ID",
                "Nombre",
                "Fecha Nacimiento",
                "Sexo",
                "Dueño",
                "Raza"
        }), mascotas);

        this.alta = alta;
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
    public void initEvents() {
        view.btnSeleccionar.addActionListener(e -> {
            int selectedRow = view.table.getSelectedRow();
            Mascotas mascota = (Mascotas) view.table.getValueAt(selectedRow, 1);

            alta.pasar(mascota);
            view.dispose();

            MessageDialog.successMessage(view, "Mascota seleccionada: " + mascota);
        });

        view.btnRegistrar.addActionListener(e -> {
            new C_AltaMascota();
            //view.dispose();
        });
    }
}
