package application.views.FacturaChooser.FacturaDisplay;

import application.models.detalles.DetalleFactura;
import application.views.components.abstracts.CustomJTableModel;
import org.jetbrains.annotations.Nls;

import javax.swing.event.TableModelEvent;

public class TableDetalleFacturaModel extends CustomJTableModel<DetalleFactura> {
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
        DetalleFactura detalleFactura = getItem(rowIndex);

        return switch (columnIndex) {
            case 0 -> detalleFactura.getArticulo().getNombre();
            case 1 -> detalleFactura.cantidad();
            case 2 -> detalleFactura.getArticulo().monto();
            case 3 -> detalleFactura.monto();
            case 4 -> detalleFactura.getArticulo().getTipo();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DetalleFactura detalleFactura = getItem(rowIndex);

        try {
            switch (columnIndex) {
                case 0 -> detalleFactura.getArticulo().setNombre((String) aValue);
                case 1 -> {
                    detalleFactura.cantidad((Integer) aValue);
                }
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
