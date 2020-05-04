package Chess;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorRenderer extends JLabel implements TableCellRenderer {

    public ColorRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (Integer.parseInt(table.getValueAt(row, column).toString()) < 0) {
            setBackground(Color.black);
        }
        if (Integer.parseInt(table.getValueAt(row, column).toString()) == 0) {
            setBackground(Color.white);
        }
        if (Integer.parseInt(table.getValueAt(row, column).toString()) > 0) {
            setBackground(Color.lightGray);
        }
        return this;
    }
}
