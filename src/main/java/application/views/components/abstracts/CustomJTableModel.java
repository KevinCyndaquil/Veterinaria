package application.views.components.abstracts;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Implemente un modelo para tablas que se puede implementar según las necesidades de la tabla,
 * agrega sus escuchadores y demás herramientas necesarias para su funcionamiento.
 * @param <Item> el tipo de objeto que va a ser añadido a la tabla.
 */
public abstract class CustomJTableModel <Item> implements TableModel {
    protected final LinkedList<TableModelListener> subscribers;
    protected final LinkedList<Item> items;

    public CustomJTableModel() {
        subscribers = new LinkedList<>();
        items = new LinkedList<>();
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        subscribers.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        subscribers.remove(listener);
    }

    public abstract void addItem(Item item);
    public abstract void addItems(List<Item> list);
    public abstract Item getItem(int rowIndex);
    public abstract void removeItem(Item item);
}
