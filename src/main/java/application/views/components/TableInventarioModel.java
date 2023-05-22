package application.views.components;

import application.models.entidades.Proveedores;
import application.models.finanzas.ArticuloInventario;
import application.views.components.abstracts.CustomJTableModel;
import org.jetbrains.annotations.Nls;

public class TableInventarioModel extends CustomJTableModel<ArticuloInventario> {
    public static String[] titles = new String[] {
            "ARTICULO",
            "TIPO",
            "PROVEEDOR",
            "CANTIDAD REAL",
            "STOCK",
            "VARIACIÓN"};

    public static Class<?>[] classes = new Class[] {
            ArticuloInventario.class,
            String.class,
            Proveedores.class,
            Integer.class,
            Integer.class,
            Integer.class};

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
        return columnIndex == 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArticuloInventario articuloInventario = getItem(rowIndex);
        return switch (columnIndex) {
            case 0 -> articuloInventario.getArticulosVenta();
            case 1 -> articuloInventario.getArticulosVenta().getTipo();
            case 2 -> articuloInventario.getArticulosVenta().getArticulo().getProveedor();
            case 3 -> articuloInventario.getCantidadReal();
            case 4 -> articuloInventario.getArticulosVenta().getStock();
            case 5 -> articuloInventario.getVariacion();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 3)
            getItem(rowIndex).agregarCantidad(Math.max((Integer) aValue, 0));
    }
}
