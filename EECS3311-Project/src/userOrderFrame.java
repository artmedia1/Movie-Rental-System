import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class userOrderFrame extends JPanel {
	private String Username;

	public userOrderFrame(String Username) throws Exception {

		String[] columnNames
		= {"Order Number", "Items", "Total", "Status", "Shipping Address", "Username", "Request Cancel"};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\orders.csv";
		maintainOrder maintain = new maintainOrder();
		int count = 0;

		maintain.load(path);
		for(orders u: maintain.orders){
			count++;
		}

		Object[][] data = new Object[count][7];
		int count2 = 0;
		for(orders u: maintain.orders) {
			for(int j = 0; j < 7; j++) {
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
				else if(j == 5) {
					data[count2][j] = u.getUsername(); //OH NO --- low on time fix
				}
				else {
					data[count2][j] = "Request Cancel";
				}
			}
			count2++;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable jTable = new JTable(model);
		TableColumnModel tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(600);
		tcm.getColumn(2).setPreferredWidth(50);
		tcm.getColumn(3).setPreferredWidth(75);
		tcm.getColumn(4).setPreferredWidth(200);
		tcm.getColumn(5).setPreferredWidth(75);
		tcm.getColumn(6).setPreferredWidth(200);
//		jTable.setAutoResizeMode(jTable.AUTO_RESIZE_OFF);		
		
		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand() );
				String temp = (String) table.getValueAt(table.getSelectedRow(), 3);
				if(temp == "Pending") {
					JFrame frame = new JFrame("Shopping Cart");
					frame.setTitle("Shopping Cart");
					frame.setSize(300, 200);
					JLabel cartMessage = new JLabel("Order has been cancelled, reopen to refresh");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setPreferredSize(new Dimension(300,150));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					String stringHolder = (String) table.getValueAt(table.getSelectedRow(), 0);
					int temp3 = Integer.valueOf(stringHolder);
					try {
						maintain.cancelOrder(temp3);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// NOT FINISHED NEED TO IMPLEMENT WAREHOUSE STOCK USE OBSERVER?
				}
				else if(temp == "Shipped") {
					JFrame frame = new JFrame("Shopping Cart");
					frame.setTitle("Shopping Cart");
					frame.setSize(450, 200);
					JLabel cartMessage = new JLabel("Cannot cancel order, items have already been shipped");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setPreferredSize(new Dimension(1250,600));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if(temp == "Cancelled") {
					JFrame frame = new JFrame("Shopping Cart");
					frame.setTitle("Shopping Cart");
					frame.setSize(300, 200);
					JLabel cartMessage = new JLabel("Order has already been cancelled");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setPreferredSize(new Dimension(1250,600));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 6);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		JScrollPane js=new JScrollPane(jTable);
		js.setVisible(true);
		add(js, BorderLayout.CENTER);
		jTable.setPreferredScrollableViewportSize(new Dimension(1440, 810));
		
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());


		jTable.setRowSorter(rowSorter);
		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + Username));





		
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