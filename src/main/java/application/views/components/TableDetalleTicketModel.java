package application.views.components;

import application.models.detalles.DetalleTicket;
import application.views.components.abstracts.CustomJTableModel;
import org.jetbrains.annotations.Nls;

import java.math.BigDecimal;

public class TableDetalleTicketModel extends CustomJTableModel<DetalleTicket> {
    public static String[] titles = new String[]{
            "Código",
            "Nombre",
            "Cantidad",
            "Precio Unitario",
            "Subtotal"
    };

    public static Class<?>[] classes = new Class[] {
            Integer.class,
            String.class,
            Integer.class,
            BigDecimal.class,
            BigDecimal.class
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
        return columnIndex == 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetalleTicket detalleTicket = getItem(rowIndex);
        return switch (columnIndex) {
            case 0 -> detalleTicket.getArticuloVenta().getArticulo().getId_articulo();
            case 1 -> detalleTicket.getArticuloVenta().getArticulo().getNombre();
            case 2 -> detalleTicket.cantidad();
            case 3 -> detalleTicket.getArticuloVenta().monto();
            case 4 -> detalleTicket.monto();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DetalleTicket detalleTicket = getItem(rowIndex);

        if (columnIndex == 2)
            detalleTicket.cantidad((Integer) aValue);
    }
}
