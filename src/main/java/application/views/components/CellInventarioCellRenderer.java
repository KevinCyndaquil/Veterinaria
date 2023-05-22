package application.views.components;

import application.views.utils.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellInventarioCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setOpaque(true);
        setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));

        if (row % 2 == 0) {
            Color bg = new Color(4, 168, 102);
            setBackground((isSelected) ? (hasFocus) ? bg.darker().darker() : bg.darker() : bg );
            setForeground(Color.WHITE);
        }
        else {
            Color bg = new Color(249, 243, 253);
            setBackground((isSelected) ? (hasFocus) ? bg.darker().darker() : bg.darker() : bg);
            setForeground((hasFocus) ? Color.WHITE : Color.DARK_GRAY);
        }

        if (value instanceof Integer num) {
            setHorizontalAlignment(CENTER);

            if (column == 5)
                if (num < 0)
                    setForeground(Color.GREEN);
                else if (num > 0)
                    setForeground(Color.RED);
        }

        if (hasFocus)
            setFont(getFont().deriveFont(Font.BOLD));
        else
            setFont(getFont().deriveFont(Font.PLAIN));

        return this;
    }
}
