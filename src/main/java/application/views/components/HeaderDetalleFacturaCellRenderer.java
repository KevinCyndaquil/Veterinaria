package application.views.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class HeaderDetalleFacturaCellRenderer implements TableCellRenderer {
    private final Color headColor;
    private final Color textColor;

    public HeaderDetalleFacturaCellRenderer() {
        headColor = null;
        textColor = new Color(255, 255, 255);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            JLabel head = new JLabel((String) value);

            head.setHorizontalAlignment(SwingConstants.CENTER);
            head.setSize(head.getWidth(), 30);
            head.setPreferredSize(new Dimension(head.getWidth(), 30));

            head.setOpaque(true);

            head.setBackground(headColor);
            head.setForeground(textColor);

            head.setFont(new Font("Verdana", Font.BOLD, 16));

            return head;
        }

        return null;
    }
}
