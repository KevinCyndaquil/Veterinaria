package application.controllers;

import application.MessageDialog;
import application.database.Postgres;
import application.database.repository.CitaRepository;
import application.models.entidades.Mascotas;
import application.views.ChooserMascotaView;
import application.views.components.interfaces.ViewerController;
import org.jetbrains.annotations.NotNull;

import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class C_ChooserMascota extends ViewerController<ChooserMascotaView> {
    private final DefaultTableModel modelToSend;

    public C_ChooserMascota(DefaultTableModel modelToSend) {
        super(new ChooserMascotaView());
        this.modelToSend = modelToSend;
    }

    public void agregarMascota(@NotNull Mascotas mascota) {
        DefaultTableModel model = (DefaultTableModel) view.tblMascotas.getModel();
        model.addRow(new Object[]{
                mascota,
                mascota.fecha_nacimiento(),
                mascota.sexo(),
                mascota.propietario(),
                mascota.raza()
        });
    }

    public void enviar() {
        CitaRepository citaRepository = new CitaRepository(new Postgres());
        DefaultTableModel model = (DefaultTableModel) view.tblMascotas.getModel();
        int id_mascota = ((Mascotas) model.getValueAt(view.tblMascotas.getSelectedRow(), 0)).id_mascota();

        modelToSend.setRowCount(0);

        try {
            citaRepository.obtenerDetalle(id_mascota)
                    .forEach(modelToSend::addRow);
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "Ocurrió un error al leer el detalle de la factura"
            );
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initEvents() {
        view.tblMascotas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == 10) {
                    if (view.tblMascotas.getSelectedRow() == -1) {
                        MessageDialog.uncertainMessage(
                                view,
                                "selecciona una fila para poder ver su detalle");
                        return;
                    }
                    enviar();
                    view.setVisible(false);
                }
            }
        });
    }
}
