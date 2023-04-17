/*package application.views.FacturaDisplay;

import application.modelos.entidades.Alimento;
import application.modelos.entidades.ArticuloGramaje;
import application.modelos.entidades.Medicamento;
import application.modelos.entidades.Producto;
import application.modelos.entregas.ArticuloFactura;
import application.views.components.abstracts.CustomJTableModel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.TableModelEvent;
import java.util.List;

public class ArticulosJTableModel extends CustomJTableModel<ArticuloFactura> {
    public static String[] titles = new String[]{
            "Id",
            "Nombre",
            "Cantidad",
            "Monto de Proveedor",
            "Subtotal",
            "Proveedor",
            "Presentación",
            "Tipo"
    };
    public static Class<?>[] classes = new Class[]{
            Integer.class,
            String.class,
            Integer.class,
            Double.class,
            Double.class,
            String.class,
            String.class,
            String.class
    };

    @Override
    public void addItem(ArticuloFactura articuloProveedor) {
        if (articuloProveedor == null)
            return;

        items.add(articuloProveedor);

        TableModelEvent event = new TableModelEvent(
                this,
                getRowCount() - 1,
                getRowCount() - 1,
                TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        subscribers.forEach(s -> s.tableChanged(event));
    }

    @Override
    public void addItems(@NotNull List<ArticuloFactura> list) {
        list.forEach(this::addItem);
    }

    @Override
    public ArticuloFactura getItem(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    public void removeItem(ArticuloFactura articuloProveedor) {
        if (articuloProveedor == null)
            return;

        items.remove(articuloProveedor);

        TableModelEvent event = new TableModelEvent(
                this,
                getRowCount() - 1,
                getRowCount() - 1,
                TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        subscribers.forEach(s -> s.tableChanged(event));
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Nls
    @Override
    public String getColumnName(int columnIndex) {
        return titles[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 1 || columnIndex == 2);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArticuloFactura articulo = items.get(rowIndex);
        String clase = articulo.getArticuloProveedor().getClass().getSimpleName();
        String presentacion = "null";

        switch (clase) {
            case "Alimento", "Medicamento" -> presentacion = String.valueOf(((ArticuloGramaje) articulo.getArticuloProveedor()).getGramaje());
            case "Producto" -> presentacion = ((Producto) articulo.getArticuloProveedor()).getTipo().getDescripcion();
        }

        return switch (columnIndex) {
            case 0 -> articulo.getId();
            case 1 -> articulo.getArticuloProveedor().getNombre();
            case 2 -> articulo.getCantidad();
            case 3 -> articulo.getArticuloProveedor().getMontoCompra();
            case 4 -> articulo.getMonto_subtotal();
            case 5 -> articulo.getArticuloProveedor().getProveedor().getNombre();
            case 6 -> presentacion;
            case 7 -> clase;
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ArticuloFactura articulo = items.get(rowIndex);

        try {
            ArticuloFactura articuloToAdd = (ArticuloFactura) aValue;

            switch (columnIndex) {
                case 1 -> articulo.getArticuloProveedor().setNombre(articuloToAdd.getArticuloProveedor().getNombre());
                case 2 -> articulo.setCantidad(articuloToAdd.getCantidad());
            }
        } catch (ClassCastException ex1) {
            try {
                switch (columnIndex) {
                    case 1 -> articulo.getArticuloProveedor().setNombre((String) aValue);
                    case 2 -> articulo.setCantidad((Integer) aValue);
                }
            } catch (ClassCastException ex2) {
                System.out.println("Tipo de dato incorrecto");
            }
        }

        TableModelEvent event = new TableModelEvent(
                this,
                rowIndex,
                rowIndex,
                columnIndex);

        subscribers.forEach(s -> s.tableChanged(event));
    }
}*/
