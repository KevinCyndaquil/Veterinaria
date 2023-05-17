package application.controllers;

import application.MessageDialog;
import application.database.Postgres;
import application.database.repository.FacturaRepository;
import application.models.detalles.DetalleFactura;
import application.models.finanzas.FacturasProveedor;
import application.views.FacturaChooser.FacturaDisplay.CellDetalleFacturaCellRenderer;
import application.views.FacturaChooser.FacturaDisplay.DetalleFacturaView;
import application.views.FacturaChooser.FacturaDisplay.HeaderDetalleFacturaCellRenderer;
import application.views.FacturaChooser.FacturaDisplay.TableDetalleFacturaModel;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;

import javax.swing.table.JTableHeader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class C_DetalleFactura extends ViewerController <DetalleFacturaView> implements TableController {
    private final FacturasProveedor factura;


    public C_DetalleFactura(FacturasProveedor factura) {
        super(new DetalleFacturaView());
        this.factura = factura;

        System.out.println(factura.getDetalleArticulos());
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

        model.removeAll();
        factura.getDetalleArticulos().forEach(model::addItem);
        view.tblDetalleFacturas.setModel(model);
        view.tblDetalleFacturas.setTableHeader(tblHeader);

        view.tblDetalleFacturas.getColumnModel().getColumns().asIterator()
                .forEachRemaining(c -> c.setCellRenderer(new CellDetalleFacturaCellRenderer()));
    }

    public void actualizarDetalle() {
        TableDetalleFacturaModel model = (TableDetalleFacturaModel) view.tblDetalleFacturas.getModel();
        FacturaRepository facRep = new FacturaRepository(new Postgres());
        List<DetalleFactura> detalleInsert = new ArrayList<>();
        List<DetalleFactura> detalleUpdate = new ArrayList<>();

        if (model.getItems().isEmpty()) {
            MessageDialog.errorMessage(
                    view,
                    "ERROR: No hay una factura en la tabla, llame a su programador más experimentado");
            return;
        }

        if (!MessageDialog.yesNoQuestionMessage(
                view,
                "¿Estás seguro de querer realizar cambios en la factura de folio %s"
                        .formatted(factura.getId_factura()))) return;

        model.getItems().forEach(d -> {
            if (d.cantidad() == 0) return;
            if (d.getCns() == 0) detalleInsert.add(d);
            if (d.getCns() > 0) detalleUpdate.add(d);

            System.out.println("*********CNS:" + d.getCns());
        });

        try {
            if (facRep.updateDetalleArticulos(detalleUpdate))
                MessageDialog.querySuccessMessage(
                        view,
                        "Articulos %s actualizados correctamente".formatted(detalleUpdate));
            if (facRep.insertDetalleArticulos(detalleInsert))
                MessageDialog.querySuccessMessage(
                        view,
                        "Articulos %s insertados correctamente".formatted(detalleInsert));
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "No se pudo actualizar el detalle de la factura, llame a su programador más experimentado.");
            throw new RuntimeException(e);
        }

        factura.eliminarArticulos();
        detalleUpdate.forEach(factura::agregarArticulo);
        detalleInsert.forEach(factura::agregarArticulo);
        factura.getDetalleArticulos().sort(Comparator.comparing(DetalleFactura::getCns));
        System.out.println(factura);

        refreshTables();
    }
}
