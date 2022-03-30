import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class orderFrame extends JPanel {
	private Button button1;
	String shoppingCart = "";

	public orderFrame() throws Exception {

		String[] columnNames
		= {"Order Number", "Items", "Total", "Status", "Shipping Address", "Request Cancel"};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\orders.csv";
		maintainOrder maintain = new maintainOrder();
		int count = 0;

		maintain.load(path);
		for(orders u: maintain.orders){
			count++;
		}

		Object[][] data = new Object[count][6];
		int count2 = 0;
		for(orders u: maintain.orders) {
			for(int j = 0; j < 6; j++) {
				if(j == 0){
					data[count2][j] = String.valueOf(u.getOrderID());					
				}
				else if(j == 1) {
					data[count2][j] = u.getShoppingCart();
				}
				else if(j == 2) {
					data[count2][j] = String.valueOf(u.getOrderTotal());
				}
				else if(j == 3) {
					data[count2][j] = String.valueOf(u.getOrderStatus());
				}
				else if(j == 4) {
					data[count2][j] = u.getShippingAddress();
				}
				else {
					data[count2][j] = "Request Cancel";
				}
			}
			count2++;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable jTable = new JTable(model);

		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();

				int modelRow = Integer.valueOf( e.getActionCommand() );
				String temp = (String) table.getValueAt(table.getSelectedRow(), 3);
				String temp2 = (String) table.getValueAt(table.getSelectedRow(), 0);
				int temp3 = Integer.valueOf(temp2);

					for(orders order : maintain.orders) {
						if(order.getOrderID() == temp3) {
							order.setOrderStatus(orderStatus.Cancelled);
							try {
								maintain.update(path);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
				
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 5);
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new JFrame("Orders");
				try {
					frame.add(new orderFrame());
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
			frame.setSize(280, 150);
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