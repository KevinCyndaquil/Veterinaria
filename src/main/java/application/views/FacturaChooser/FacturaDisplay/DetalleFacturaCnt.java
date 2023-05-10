package application.views.FacturaChooser.FacturaDisplay;

import application.MessageDialog;
import application.database.Postgres;
import application.database.repository.FacturaRepository;
import application.models.finanzas.FacturasProveedor;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.sql.SQLException;

public class DetalleFacturaCnt extends ViewerController <DetalleFacturaView> implements TableController {
    private final FacturasProveedor factura;


    public DetalleFacturaCnt(FacturasProveedor factura) {
        super(new DetalleFacturaView());
        this.factura = factura;

        refreshTables();
    }

    @Override
    public void initEvents() {
        view.btnActualizar.addActionListener(e -> actualizarDetalle());
    }

    @Override
    public void refreshTables() {
        TableDetalleFacturaModel model = new TableDetalleFacturaModel();
        JTableHeader tblHeader = view.tblDetalleFacturas.getTableHeader();
        tblHeader.setDefaultRenderer(new HeaderDetalleFacturaCellRenderer());

        factura.getDetalleArticulos().forEach(model::addItem);
        view.tblDetalleFacturas.setModel(model);
        view.tblDetalleFacturas.setTableHeader(tblHeader);

        view.tblDetalleFacturas.getColumnModel().getColumns().asIterator()
                .forEachRemaining(c -> c.setCellRenderer(new CellDetalleFacturaCellRenderer()));
    }

    public void actualizarDetalle() {
        if (factura == null) {
            MessageDialog.errorMessage(
                    view,
                    "ERROR: No hay una factura en la tabla, llame a su programador más experimentado");
            return;
        }

        TableDetalleFacturaModel model = (TableDetalleFacturaModel) view.tblDetalleFacturas.getModel();

        factura.eliminarArticulos();

        FacturaRepository facRep = new FacturaRepository(new Postgres());

        model.getItems().forEach(d -> {
            if (d.cantidad() > 0) {
                factura.agregarArticulo(d);
                System.out.println("*********CNS:" + d.getCns());
            }

        });

        System.out.println(factura.getDetalleArticulos());
        try {
            if (facRep.updateDetalleArticulos(factura))
                MessageDialog.querySuccessMessage(
                        view,
                        "Articulos %s actualizados correctamente".formatted(factura.getDetalleArticulos()));
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "No se pudo actualizar el detalle de la factura, llame a su programador más experimentado.");
            throw new RuntimeException(e);
        }

        refreshTables();
    }
}
