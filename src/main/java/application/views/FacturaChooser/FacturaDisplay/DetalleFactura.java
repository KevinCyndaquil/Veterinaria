package application.views.FacturaChooser.FacturaDisplay;

import application.MessageDialog;
import application.database.Postgres;
import application.database.repository.FacturaProveedorRep;
import application.models.Entity_Manager.repositories.Save;
import application.models.Entity_Manager.repositories.Update;
import application.models.entidades.Proveedores;
import application.models.finanzas.FacturasProveedor;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;
import lombok.Setter;
import org.postgresql.util.PSQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;

public class DetalleFactura extends ViewerController <DetalleFacturaView> implements TableController {
    @Setter
    private Date fecha;
    @Setter
    private Proveedores proveedor;
    private final FacturasProveedor factura;


    public DetalleFactura(FacturasProveedor factura) {
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
        ((DefaultTableModel) view.tblDetalleFacturas.getModel()).setRowCount(0);
        TableDetalleFacturaModel model = new TableDetalleFacturaModel();

        model.addItems(factura.getArticulos());
        view.tblDetalleFacturas.setModel(model);
    }

    public void actualizarDetalle() {
        if (factura == null) {
            MessageDialog.errorMessage(
                    view,
                    "ERROR: No hay una factura en la tabla, llame a su programador más experimentado");
            return;
        }


        TableDetalleFacturaModel model = (TableDetalleFacturaModel) view.tblDetalleFacturas.getModel();

        try {
            factura.eliminarArticulos();

            model.getItems().forEach((a, c) -> {
                if (c > 0)
                    factura.agregarArticulo(a, c);

                System.out.println(a);
            });

            System.out.println(factura.getArticulos());

            FacturaProveedorRep update = new FacturaProveedorRep(new Postgres());

            update.updateDetalle(factura);

            MessageDialog.querySuccessMessage(
                    view,
                    "Articulos %s actualizados correctamente".formatted(factura.getArticulos()));

        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "No se pudo actualizar el detalle de la factura, llame a su programador más experimentado.");
            throw new RuntimeException(e);
        }

        model.getItems().forEach((a, c) -> {

        });

    }
}
