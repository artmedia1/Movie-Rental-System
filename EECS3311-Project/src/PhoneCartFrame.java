import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PhoneCartFrame extends JPanel {
	private Button button1;
	shoppingCart shoppingCart;
	private String Username;

	public PhoneCartFrame(shoppingCart shoppingCart) throws Exception {
		this.shoppingCart = shoppingCart;
		String[] columnNames
		= {"Movie", "Total", ""};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();


		Object[][] data = new Object[shoppingCart.size()][3];
		int count2 = 0;
		for(movie u: shoppingCart.shoppingCart) {
			for(int j = 0; j < 3; j++) {
				if(j == 0){
					data[count2][j] = u.getMovieTitle();					
				}
				else if(j == 1) {
					data[count2][j] = String.valueOf(u.getCost());
				}
				else {
					data[count2][j] = "Remove from Cart";
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
				String temp = (String) table.getValueAt(table.getSelectedRow(), 0) + " has been removed from the cart";
				String temp2 = (String) table.getValueAt(table.getSelectedRow(), 0);
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
				try {
					shoppingCart.remove(temp2);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	//			shoppingCart.display();
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

		JScrollPane js=new JScrollPane(jTable);
		js.setVisible(true);
		add(js);

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());

		JTextField jtfFilter = new JTextField();
		JButton jbtFilter = new JButton("Filter");
		jTable.setRowSorter(rowSorter);


		JPanel panel = new JPanel(new BorderLayout());
		button1 = new Button("Place Order");
		button1.addActionListener(actionListener);
		panel.add(button1, BorderLayout.CENTER);
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

	public void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new JFrame("Movie Database");
				try {
					frame.add(new shoppingFrame(shoppingCart, Username));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.pack();

				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});
	}


	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\orders.csv";
			String path3 = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
			maintainOrder maintain = new maintainOrder();
			MaintainUser maintain2 = new MaintainUser();
			maintainMovie maintain3 = new maintainMovie();
			
			try {
				maintain3.load(path3);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
						
			try {
				maintain.load(path);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String shippingAddress = "";
			int loyaltyPoints = 0;

			double total = 0;
			ArrayList<String> tempList = new ArrayList<String>();
			for(int i = 0; i < shoppingCart.shoppingCart.size(); i++) {
				tempList.add(shoppingCart.shoppingCart.get(i).getMovieTitle());
				total += Double.valueOf(shoppingCart.shoppingCart.get(i).getCost());
			}
			
			String result = String.join(", ", tempList);
			
			orders newOrder = new orders(maintain.orders.size() + 1, result, total, orderStatus.Pending, shippingAddress, Username);

			new PhoneCreditCardFrame(newOrder);
		}
	};

	
}
	