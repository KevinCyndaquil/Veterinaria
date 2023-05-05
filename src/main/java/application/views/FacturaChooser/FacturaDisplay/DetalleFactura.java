package application.views.FacturaChooser.FacturaDisplay;

import application.database.Postgres;
import application.database.repository.FacturaProveedorRep;
import application.models.entidades.Proveedores;
import application.models.finanzas.FacturasProveedor;
import application.views.components.interfaces.TableController;
import application.views.components.interfaces.ViewerController;
import lombok.Setter;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class DetalleFactura extends ViewerController <DetalleFacturaView> implements TableController {
    @Setter
    private Date fecha;
    @Setter
    private Proveedores proveedor;


    public DetalleFactura(Date fecha, Proveedores proveedor) {
        super(new DetalleFacturaView());

        this.fecha = fecha;
        this.proveedor = proveedor;

        refreshTables();
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void refreshTables() {
        ((DefaultTableModel) view.tblDetalleFacturas.getModel()).setRowCount(0);

        TableDetalleFacturaModel model = new TableDetalleFacturaModel();
        FacturaProveedorRep repF = new FacturaProveedorRep(new Postgres());

        try {
            FacturasProveedor factura = repF.findFacturaWithArticles(fecha, proveedor);

            model.addItems(factura.getArticulos());

            view.tblDetalleFacturas.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing
            new DetalleFactura(Date.valueOf(LocalDate.now()), new Proveedores(1));
        });
    }
}
