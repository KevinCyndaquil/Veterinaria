package application.views.FacturaChooser;

import application.MessageDialog;
import application.database.Postgres;
import application.database.repository.FacturaProveedorRep;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.entidades.Proveedores;
import application.models.finanzas.FacturasProveedor;
import application.views.FacturaChooser.FacturaDisplay.DetalleFactura;
import application.views.components.interfaces.ComboBoxController;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class FacturaChooser extends ViewerController<FacturaChooserView> implements
        ComboBoxController,
        TableController {

    public FacturaChooser() {
        super(new FacturaChooserView());
        refreshComboBoxes();
        refreshTables();
    }

    @Override
    public void initEvents() {
        view.dateChooser.addPropertyChangeListener(e -> refreshTables());

        //botones
        view.btnInsertar.addActionListener(e -> {
            Integer id_factura = (view.fldId_factura.getText().equals("")) ?
                    null :
                    Integer.parseInt(view.fldId_factura.getText());

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

            FacturasProveedor factura = new FacturasProveedor((Integer) model
                    .getValueAt(view.tblFacturas.getSelectedRow(), 0));

            try {
                factura = (new FacturaProveedorRep(new Postgres()))
                        .findByIdWithAllArticles(factura);
            } catch (SQLException ex) {
                MessageDialog.queryErrorMessage(
                        view,
                        "Ocurrió un error con la lectura de la factura %s, llame a su programador más experimentado".formatted(
                                factura));
                throw new RuntimeException(ex);
            }

            new DetalleFactura(factura);
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
        Find<FacturasProveedor> findF = new FacturaProveedorRep(new Postgres());

        Date fecha = new Date(view.dateChooser.getDate().getTime());
        DefaultTableModel model = (DefaultTableModel) view.tblFacturas.getModel();

        model.setRowCount(0);

        try {
            findF.find(new FacturasProveedor(fecha, null)).forEach(obj -> {
                FacturasProveedor factura = (FacturasProveedor) obj;
                model.addRow(new Object[]{
                        factura.getId_factura(),
                        factura.getProveedor(),
                        factura.getMonto_total()
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
        new FacturaChooser();
    }
}
