package application.views.FacturaChooser;

import application.MessageDialog;
import application.database.Postgres;
import application.database.repository.FacturaRepository;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Proveedores;
import application.models.finanzas.FacturasProveedor;
import application.views.FacturaChooser.FacturaDisplay.DetalleFacturaCnt;
import application.views.components.interfaces.ComboBoxController;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class FacturaChooserCnt extends ViewerController<FacturaChooserView> implements
        ComboBoxController,
        TableController {

    public FacturaChooserCnt() {
        super(new FacturaChooserView());
        refreshComboBoxes();
        refreshTables();
    }

    @Override
    public void initEvents() {
        view.dateChooser.addPropertyChangeListener(e -> refreshTables());

        //botones
        view.btnInsertar.addActionListener(e -> {
            String folio = view.fldId_factura.getText();
            Integer id_factura;

            try {
                id_factura = (folio.equals("")) ? null : Integer.parseInt(folio);
            } catch (NumberFormatException ex) {
                MessageDialog.stupidMessage(
                        view,
                        "El folio de la factura debe ser un número");
                return;
            }

            FacturasProveedor factura = new FacturasProveedor(
                    id_factura,
                    Date.valueOf(LocalDate.now()),
                    (Proveedores) view.cmbProveedor.getSelectedItem()
            );

            Save<FacturasProveedor> saveF = new Repository<>(new Postgres());

            try {
                saveF.save(factura);
                MessageDialog.querySuccessMessage(
                        view,
                        "Factura de folio %s del proveedor %s registrada correctamente.".formatted(
                                factura.getId_factura(),
                                factura.getProveedor()));

                refreshTables();
            } catch (SQLException ex) {
                MessageDialog.errorMessage(
                        view,
                        "Ocurrió al intentar registrar la factura de folio %s del proveedor %s. LLame a su programador más experimentado".formatted(
                        factura.getId_factura(),
                        factura.getProveedor()));
                throw new RuntimeException(ex);
            }
        });

        view.btnConsultar.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) view.tblFacturas.getModel();

            int selectedRow = view.tblFacturas.getSelectedRow();

            if (selectedRow == -1) {
                MessageDialog.uncertainMessage(
                        view,
                        "Selecciona una factura para poder consultar");
                return;
            }

            int id_factura = (Integer) model
                    .getValueAt(selectedRow, 0);
            FacturasProveedor factura = new FacturasProveedor(id_factura);

            FacturaRepository facRep = new FacturaRepository(new Postgres());
            try {
                factura = facRep.findByIdAsTable(factura);
            } catch (SQLException ex) {
                MessageDialog.queryErrorMessage(
                        view,
                        "Ocurrió un error con la lectura de la factura %s, llame a su programador más experimentado".formatted(
                                factura));
                throw new RuntimeException(ex);
            }

            new DetalleFacturaCnt(factura);
        });
    }

    @Override
    public void refreshComboBoxes() {
        view.cmbProveedor.removeAllItems();

        Find<Proveedores> findP = new Repository<>(new Postgres());

        try {
            findP.find(new Proveedores(null))
                    .forEach(p -> view.cmbProveedor.addItem((Proveedores) p));
        } catch (SQLException e) {
            MessageDialog.errorMessage(
                    view,
                    "Ocurrió un error con la lectura de los proveedores. LLame a su programador más experimentado");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void refreshTables() {
        Find<FacturasProveedor> findF = new FacturaRepository(new Postgres());

        Date fecha = new Date(view.dateChooser.getDate().getTime());
        DefaultTableModel model = (DefaultTableModel) view.tblFacturas.getModel();

        model.setRowCount(0);

        try {
            findF.find(new FacturasProveedor(fecha, null)).forEach(obj -> {
                FacturasProveedor factura = (FacturasProveedor) obj;
                model.addRow(new Object[]{
                        factura.getId_factura(),
                        factura.getProveedor(),
                        factura.monto()
                });
            });
        } catch (SQLException e) {
            MessageDialog.errorMessage(
                    view,
                    "Ocurrió un error con la lectura de las facturas. LLame a su programador más experimentado");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new FacturaChooserCnt();
    }
}
