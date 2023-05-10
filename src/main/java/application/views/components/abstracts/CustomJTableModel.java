package application.views.components.abstracts;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.*;

/**
 * Implemente un modelo para tablas que se puede implementar según las necesidades de la tabla,
 * agrega sus escuchadores y demás herramientas necesarias para su funcionamiento.
 * @param <Item> el tipo de objeto que va a ser añadido a la tabla.
 */
public abstract class CustomJTableModel <Item> implements TableModel {
    protected final LinkedList<TableModelListener> subscribers;
    @Getter
    protected final List<Item> items;

    public CustomJTableModel() {
        subscribers = new LinkedList<>();
        items = new ArrayList<>();
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        subscribers.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        subscribers.remove(listener);
    }

    public void removeAll() {
        items.clear();

        TableModelEvent event = new TableModelEvent(
                this,
                getRowCount() - 1,
                getRowCount() - 1,
                TableModelEvent.ALL_COLUMNS,
                TableModelEvent.DELETE);

        subscribers.forEach(s -> s.tableChanged(event));
    }

    public void addItem(@NotNull Item item) {
        items.add(item);

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

    public void addItems(@NotNull List<Item> list) {
        list.forEach(this::addItem);
    }
    public Item getItem(int rowIndex) {
        return items.get(rowIndex);
    }
    public void removeItem(@NotNull Item item) {
        items.remove(item);

        TableModelEvent event = new TableModelEvent(
                this,
                getRowCount() - 1,
                getRowCount() - 1,
                TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        subscribers.forEach(s -> s.tableChanged(event));
    }
}
