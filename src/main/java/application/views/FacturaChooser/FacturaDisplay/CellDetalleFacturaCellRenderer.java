package application.views.FacturaChooser.FacturaDisplay;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellDetalleFacturaCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object value, boolean selected, boolean focused, int row, int column) {
        this.setOpaque(true);

        if (row % 2 == 0)
            this.setBackground(new Color(246, 238, 227));
        else
            this.setBackground(new Color(232, 218, 207));

        switch (value.getClass().getSimpleName()) {
            case "Integer", "BigDecimal", "Float", "Double" -> {
                this.setHorizontalAlignment(CENTER);
                this.setForeground((focused) ? Color.MAGENTA : new Color(44, 143, 2));
                this.setFont(new Font("Verdana", Font.BOLD, 14));
            }
            case "String" -> {
                this.setForeground((focused) ? Color.WHITE : new Color(91, 88, 87));
                this.setFont(new Font(Font.SERIF, Font.BOLD, 14));
            }
            case "Boolean" -> {
                this.setHorizontalAlignment(CENTER);
                this.setForeground((!selected) ? Color.BLUE : (focused) ? Color.YELLOW : Color.BLUE);
                this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
            }
            case "Date" -> {
                this.setHorizontalAlignment(CENTER);
                this.setForeground((!selected) ? new Color(54, 190, 0) : Color.MAGENTA);
                this.setFont(new Font("Verdana", Font.BOLD, 16));
            }
            default -> this.setHorizontalAlignment(RIGHT);
        }

        if (column == 5)
            this.setHorizontalAlignment(CENTER);

        if (selected)
            this.setBackground(new Color(201, 183, 183));

        if (focused)
            this.setBackground(new Color(126, 110, 93));

        this.setText(value.toString());

        return this;
    }
}
