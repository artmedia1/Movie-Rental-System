import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer 
  {
    public ButtonRenderer() {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
      setText((value == null) ? "Modify" : value.toString());
      return this;
    }
  }