import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SystemUserFrame extends JPanel {

	private String userToRemove;


	public SystemUserFrame() throws Exception {

		String[] columnNames
		= {"Name", "Email", "Username", "Password","Account Type", "", ""};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		MaintainUser maintain = new MaintainUser();

		maintain.load(path);

		Object[][] data = new Object[maintain.users.size()][7];
		int count2 = 0;
		for(User u: maintain.users) {
			for(int j = 0; j < 7; j++) {
				if(j == 0){
					data[count2][j] = u.getName();		
				}
				else if(j == 1) {
					data[count2][j] = u.getEmail();
				}
				else if(j == 2) {
					data[count2][j] = u.getUsername();
				}
				else if(j == 3) {
					data[count2][j] = u.getPassword();
				}
				else if(j == 4) {
					data[count2][j] = u.getAccountType();
				}
				else if(j == 5) {
					data[count2][j] = "Edit Account";
				}
				else {
					data[count2][j] = "Remove User";
				}
			}
			count2++;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable jTable = new JTable(model);
		TableColumnModel tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(80);
		tcm.getColumn(1).setPreferredWidth(100);
		tcm.getColumn(2).setPreferredWidth(80);
		tcm.getColumn(3).setPreferredWidth(80);
		tcm.getColumn(4).setPreferredWidth(100);
		tcm.getColumn(5).setPreferredWidth(100);
		tcm.getColumn(6).setPreferredWidth(100);

		Action edit = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand() );
				String temp = (String) table.getValueAt(table.getSelectedRow(), 2);

				SettingsFrame settingsFrame = new SettingsFrame(temp);
			}
		};

		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand() );
				String temp = "User: " + (String) table.getValueAt(table.getSelectedRow(), 2) + " has been removed from the database";
				userToRemove = (String) table.getValueAt(table.getSelectedRow(), 2);
				
				JFrame frame = new JFrame("Updated");
				JLabel cartMessage = new JLabel(temp);
				cartMessage.setHorizontalAlignment(JLabel.CENTER);
				cartMessage.setVerticalAlignment(JLabel.CENTER);
				frame.add(cartMessage);
				frame.setSize(560, 300);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				((DefaultTableModel)table.getModel()).removeRow(modelRow);

				for(User u : maintain.users) {
		//			System.out.println(titleToRemove);
					if(u.getUsername().equals(userToRemove)) {
						maintain.users.remove(maintain.users.indexOf(u));
						try {
							maintain.update(path);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, edit, 5);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

		ButtonColumn buttonColumn2 = new ButtonColumn(jTable, delete, 6);
		buttonColumn2.setMnemonic(KeyEvent.VK_D);

		JScrollPane js=new JScrollPane(jTable);
		js.setVisible(true);
		add(js);

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());

		JTextField jtfFilter = new JTextField();
		JButton jbtFilter = new JButton("Filter");
		jTable.setRowSorter(rowSorter);


		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Specify a word to match:"), BorderLayout.WEST);
		panel.add(jtfFilter, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		add(new JScrollPane(jTable), BorderLayout.CENTER);

		jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}

		});
	}

	private static class JTableButtonRenderer implements TableCellRenderer {        
		@Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JButton button = (JButton)value;
			return button;  
		}
	}

}