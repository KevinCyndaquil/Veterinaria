package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.ViewerController;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Update;
import application.models.finanzas.ArticuloInventario;
import application.models.finanzas.ArticulosVenta;
import application.views.Inventario;
import application.views.components.CellInventarioCellRenderer;
import application.views.components.HeaderInventarioCellRenderer;
import application.views.components.TableInventarioModel;
import application.views.components.interfaces.TableController;

import javax.swing.table.JTableHeader;
import java.sql.SQLException;
import java.util.List;

public class C_Inventario extends ViewerController<Inventario> implements TableController {
    public C_Inventario() {
        super(new Inventario());

        refreshTables();
    }

    public boolean actualizar() {
        Update<ArticulosVenta> update = new Repository<>(new Postgres());
        TableInventarioModel model = (TableInventarioModel) view.table.getModel();

        try {
            model.getItems().stream()
                    .map(inv -> {
                        ArticulosVenta venta = inv.getArticulosVenta();
                        venta.setStock(inv.getCantidadReal());
                        return venta;
                    })
                    .forEach(model1 -> {
                        try {
                            update.updateById(model1);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (RuntimeException e) {
            MessageDialog.querySuccessMessage(
                    view,
                    "No se pudo actualizar el inventario");
            return false;
        }

        refreshTables();
        return true;
    }

    @Override
    public void initEvents() {
        view.table.addPropertyChangeListener(evt -> view.table.repaint());

        view.bActualizar.addActionListener(evt -> {
            if (actualizar())
                MessageDialog.successMessage(
                        view,
                        "Inventario registrado correctamente");
        });
    }

    @Override
    public void refreshTables() {
        Find<ArticulosVenta> find = new Repository<>(new Postgres());
        TableInventarioModel model = new TableInventarioModel();
        List<ArticuloInventario> articulosVentas;

        JTableHeader jTableHeader = view.table.getTableHeader();
        jTableHeader.setDefaultRenderer(new HeaderInventarioCellRenderer());
        view.table.setTableHeader(jTableHeader);
        view.table.setModel(model);

        try {
            articulosVentas = find.find(new ArticulosVenta()).stream()
                    .map(ArticulosVenta.class::cast)
                    .map(a -> new ArticuloInventario(
                            a,
                            0,
                            a.getStock()))
                    .toList();
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    view,
                    "No se pudieron leer los articulos en venta");
            throw new RuntimeException(e);
        }

        model.addItems(articulosVentas);
        view.table.getColumnModel().getColumns()
                .asIterator()
                .forEachRemaining(c -> c.setCellRenderer(new CellInventarioCellRenderer()));
        view.repaint();
    }

    public static void main(String[] args) {
        new C_Inventario();
    }
}
