import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class EditEmployeeAccountFrame extends JPanel {
	private Button button1, button2;
	private String Username;

	public EditEmployeeAccountFrame() throws Exception {

		String[] columnNames
		=  {"Name", "Email", "Username", "Password","Account Type", ""};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		MaintainUser maintain = new MaintainUser();
		int count = 0;

		maintain.load(path);
		
		for (User u: maintain.users) {
			if(!u.getAccountType().equals(AccountType.Customer)) {
				count++;
			}
		}
		Object[][] data = new Object[count++][6];
		int count2 = 0;
		for(User u: maintain.users) {
			if(!u.getAccountType().equals(AccountType.Customer)) {
				for(int j = 0; j < 6; j++) {
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
					else if(j==4){
						data[count2][j] = u.getAccountType();
					}
					else if(j==5){
						data[count2][j] = "Set Account";
					}
				}
				count2++;
			}
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable jTable = new JTable(model);
		TableColumn sportColumn = jTable.getColumnModel().getColumn(4);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Customer");
		comboBox.addItem("Employee");
		comboBox.addItem("Admin");
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		TableColumnModel tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(200);
		tcm.getColumn(2).setPreferredWidth(80);
		tcm.getColumn(3).setPreferredWidth(80);
		tcm.getColumn(4).setPreferredWidth(100);
		tcm.getColumn(5).setPreferredWidth(80);

//		jTable.setAutoResizeMode(jTable.AUTO_RESIZE_OFF);		
		
		Action edit = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				String newType = "";
				System.out.println(e.getActionCommand());
				int modelRow = Integer.valueOf( e.getActionCommand() );
				String accountType = (String) table.getValueAt(table.getSelectedRow(), 4);
				String username = (String) table.getValueAt(table.getSelectedRow(), 2);
				for(User u: maintain.users) {
					if(u.getUsername().equals(username)) {
						if(accountType.equals("Customer")) {
							u.setAccountType(AccountType.Customer);
							newType = "Customer";
						}
						else if(accountType.equals("Employee")) {
							u.setAccountType(AccountType.Employee);
							newType = "Employee";
						}
						else if(accountType.equals("Admin")) {
							u.setAccountType(AccountType.Admin);
							newType = "Admin";
						}
						try {
							maintain.update(path);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
				
				JFrame frame = new JFrame("Account type changed");
				frame.setTitle("Account type changed");
				frame.setSize(400, 200);
				JLabel cartMessage = new JLabel("User: " + username + " has been change to account type " + newType);
				cartMessage.setHorizontalAlignment(JLabel.CENTER);
				cartMessage.setVerticalAlignment(JLabel.CENTER);
				frame.add(cartMessage);
				frame.setPreferredSize(new Dimension(300,250));
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, edit, 5);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
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
		button1 = new Button("View Cart");
		button1.addActionListener(actionListener);
		panel.add(button1, BorderLayout.EAST);
		//		button2 = new Button("User Settings");
		//		button2.addActionListener(actionListener2);
		//		panel.add(button2, BorderLayout.EAST);

		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		add(new JScrollPane(jTable), BorderLayout.CENTER);
		jTable.setPreferredScrollableViewportSize(new Dimension(1440, 810));

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

	public void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new JFrame("Orders");
				frame.setPreferredSize(new Dimension(1250,600));
				try {
					frame.add(new userOrderFrame(Username));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});
	}


	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("Shopping Cart");
			frame.setTitle("Shopping Cart");
			frame.setPreferredSize(new Dimension(1250,600));
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	};

	//	ActionListener actionListener2 = new ActionListener() {
	//	    @Override
	//	    public void actionPerformed(ActionEvent e) {
	//	    	JFrame frame = new JFrame("User Settings");
	//	    	frame.setTitle("User Settings");
	//			frame.setSize(280, 150);
	//			frame.setLocationRelativeTo(null);
	//			frame.setVisible(true);
	//	    }
	//	};
}