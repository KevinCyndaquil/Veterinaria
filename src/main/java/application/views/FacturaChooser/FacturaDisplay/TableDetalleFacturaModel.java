package application.views.FacturaChooser.FacturaDisplay;

import application.models.finanzas.Articulos;
import application.views.components.abstracts.CustomJTableModel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.TableModelEvent;
import java.text.DecimalFormat;
import java.util.Map;

public class TableDetalleFacturaModel extends CustomJTableModel<Articulos> {
    public static String[] titles = new String[]{
            "Nombre",
            "Cantidad",
            "Monto de Proveedor",
            "Subtotal",
            "Tipo"
    };
    public static Class<?>[] classes = new Class[]{
            String.class,
            Integer.class,
            Double.class,
            Double.class,
            String.class
    };

    @Override
    public void addItem(@NotNull Map.Entry<Articulos, Integer> articulo) {
        items.put(articulo.getKey(), articulo.getValue());

        TableModelEvent event = new TableModelEvent(
                this,
                getRowCount() - 1,
                getRowCount() - 1,
                TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        subscribers.forEach(s -> s.tableChanged(event));
    }

    @Override
    public void addItems(@NotNull Map<Articulos, Integer> list) {
        list.forEach((a, c) -> addItem(Map.entry(a, c)));
    }

    @Override
    public Map.Entry<Articulos, Integer> getItem(int rowIndex) {
        int i = 0;

        for (Map.Entry<Articulos, Integer> entry : items.entrySet()) {
            if (i == rowIndex)
                return entry;
            i++;
        }

        return null;
    }

    @Override
    public void removeItem(@NotNull Map.Entry<Articulos, Integer> articulo) {
        items.remove(articulo.getKey());

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
        return (columnIndex == 0 || columnIndex == 1);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var detalle_factura = getItem(rowIndex);
        Articulos articulo = detalle_factura.getKey();
        String tipo = articulo.getTipo();

        return switch (columnIndex) {
            case 0 -> articulo.getNombre();
            case 1 -> detalle_factura.getValue();
            case 2 -> articulo.getMonto_compra();
            case 3 -> Double.parseDouble((new DecimalFormat("#.##")).format(articulo.getMonto_compra().doubleValue() * detalle_factura.getValue()));
            case 4 -> tipo.toUpperCase();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        var detalle_factura = getItem(rowIndex);
        Articulos articulo = detalle_factura.getKey();

        try {
            switch (columnIndex) {
                case 0 -> articulo.setNombre((String) aValue);
                case 1 -> detalle_factura.setValue((Integer) aValue);
                default -> System.out.println("campo no editable");
            }
        } catch (ClassCastException ex1) {
            System.out.println("Tipo de dato incorrecto");
        }

        TableModelEvent event = new TableModelEvent(
                this,
                rowIndex,
                rowIndex,
                columnIndex);

        subscribers.forEach(s -> s.tableChanged(event));
    }
}
