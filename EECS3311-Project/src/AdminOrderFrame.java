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

public class AdminOrderFrame extends JPanel {
	private Button button1;
	private String Username;

	public AdminOrderFrame() throws Exception {

		String[] columnNames
		= {"Order Number", "Items", "Total", "Status", "Shipping Address", "Username", "Status", ""};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\orders.csv";
		String path2 = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain2 = new maintainMovie();
		maintainOrder maintain = new maintainOrder();
		int count = 0;

		maintain.load(path);
		maintain2.load(path2);
		for(orders u: maintain.orders){
			count++;
		}

		Object[][] data = new Object[count][8];
		int count2 = 0;
		for(orders orders: maintain.orders) {
			for(int j = 0; j < 8; j++) {
				if(j == 0){
					data[count2][j] = String.valueOf(orders.getOrderID());					
				}
				else if(j == 1) {
					data[count2][j] = orders.getShoppingCart();
				}
				else if(j == 2) {
					data[count2][j] = String.valueOf(orders.getOrderTotal());
				}
				else if(j == 3) {
					data[count2][j] = String.valueOf(orders.getOrderStatus());
				}
				else if(j == 4) {
					data[count2][j] = orders.getShippingAddress();
				}
				else if(j == 5) {
					data[count2][j] = orders.getUsername(); //OH NO --- low on time fix
				}
				else if(j == 6) {
					data[count2][j] = orders.getOrderStatus();
				}
				else if(j == 7){
					data[count2][j] = "Set status";
				}
			}
			count2++;
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable jTable = new JTable(model);
		TableColumn sportColumn = jTable.getColumnModel().getColumn(6);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Ship");
		comboBox.addItem("Cancel");
		comboBox.addItem("Pending");
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		TableColumnModel tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(600);
		tcm.getColumn(2).setPreferredWidth(50);
		tcm.getColumn(3).setPreferredWidth(75);
		tcm.getColumn(4).setPreferredWidth(200);
		tcm.getColumn(5).setPreferredWidth(75);
		tcm.getColumn(6).setPreferredWidth(100);
//		jTable.setAutoResizeMode(jTable.AUTO_RESIZE_OFF);		
		
		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				System.out.println(e.getActionCommand());
				int modelRow = Integer.valueOf( e.getActionCommand() );
				String temp2 = (String) table.getValueAt(table.getSelectedRow(), 6).toString();
				String items = (String) table.getValueAt(table.getSelectedRow(), 1);
				System.out.println(temp2);
				String temp = (String) table.getValueAt(table.getSelectedRow(), 3);
				
				if(temp2 == "Ship") {
					if(temp.equals("Shipped")) {
						JFrame frame = new JFrame("Order Status");
						frame.setTitle("Order Status");
						frame.setSize(350, 200);
						JLabel cartMessage = new JLabel("Order has already been shipped");
						cartMessage.setHorizontalAlignment(JLabel.CENTER);
						cartMessage.setVerticalAlignment(JLabel.CENTER);
						frame.add(cartMessage);
						frame.setPreferredSize(new Dimension(300,250));
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}
					else {
						JFrame frame = new JFrame("Order Status");
						frame.setTitle("Order Status");
						frame.setSize(350, 200);
						JLabel cartMessage = new JLabel("Order has been set to shipped, reopen to refresh");
						cartMessage.setHorizontalAlignment(JLabel.CENTER);
						cartMessage.setVerticalAlignment(JLabel.CENTER);
						frame.add(cartMessage);
						frame.setPreferredSize(new Dimension(300,250));
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						String stringHolder = (String) table.getValueAt(table.getSelectedRow(), 0);
						for(movie movie : maintain2.movies) {
							if(items.contains(movie.getMovieTitle())) {
								try {
									maintain2.decreaseStock(movie.getMovieTitle());
									maintain2.update(path2);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						int temp3 = Integer.valueOf(stringHolder);
						try {
							maintain.shipOrder(temp3);
							maintain.update(path);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				else if(temp2 == "Cancel") {
					if(temp.equals("Shipped")) {
						for(movie movie : maintain2.movies) {
							if(items.contains(movie.getMovieTitle())) {
								try {
									maintain2.increaseStock(movie.getMovieTitle());
									maintain2.update(path2);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					JFrame frame = new JFrame("Order Status");
					frame.setTitle("Order Status");
					frame.setSize(350, 200);
					JLabel cartMessage = new JLabel("Order has been set to cancelled, reopen to refresh");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setPreferredSize(new Dimension(300,250));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					String stringHolder = (String) table.getValueAt(table.getSelectedRow(), 0);
					int temp3 = Integer.valueOf(stringHolder);
					try {
						maintain.cancelOrder(temp3);
						maintain.update(path);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(temp2 == "Pending") {
					if(temp.equals("Shipped")) {
						for(movie movie : maintain2.movies) {
							if(items.contains(movie.getMovieTitle())) {
								try {
									maintain2.increaseStock(movie.getMovieTitle());
									maintain2.update(path2);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					
					JFrame frame = new JFrame("Order Status");
					frame.setTitle("Order Status");
					frame.setSize(350, 200);
					JLabel cartMessage = new JLabel("Order has been set to pending, reopen to refresh");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setPreferredSize(new Dimension(300,250));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					String stringHolder = (String) table.getValueAt(table.getSelectedRow(), 0);
					int temp3 = Integer.valueOf(stringHolder);
					try {
						maintain.pendingOrder(temp3);
						maintain.update(path);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 7);
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