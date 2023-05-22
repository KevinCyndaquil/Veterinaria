package application.views.components;

import application.views.utils.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class HeaderInventarioCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row,column);

        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);

        setBackground(new Color(246, 235, 178));
        setForeground(Color.BLACK);
        setFont(Fonts.load(Fonts.LT_COMICAL).deriveFont(Font.BOLD, 20f));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));

        return this;
    }
}
