package application.views.components.abstracts;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implemente un modelo para tablas que se puede implementar según las necesidades de la tabla,
 * agrega sus escuchadores y demás herramientas necesarias para su funcionamiento.
 * @param <Item> el tipo de objeto que va a ser añadido a la tabla.
 */
public abstract class CustomJTableModel <Item> implements TableModel {
    protected final LinkedList<TableModelListener> subscribers;
    @Getter
    protected final Map<Item, Integer> items;

    public CustomJTableModel() {
        subscribers = new LinkedList<>();
        items = new HashMap<>();
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
                TableModelEvent.INSERT);

        subscribers.forEach(s -> s.tableChanged(event));
    }

    public abstract void addItem(@NotNull Map.Entry<Item, Integer> item);
    public abstract void addItems(@NotNull Map<Item, Integer> list);
    public abstract Map.Entry<Item, Integer> getItem(int rowIndex);
    public abstract void removeItem(@NotNull Map.Entry<Item, Integer> item);
}
