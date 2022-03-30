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

public class movieFrame extends JPanel {
	private Button button1;
	String shoppingCart = "";
	private String Username;
	List<String> shoppingList;
	List<movie> shoppingList2;
	shoppingCart newCart;

	public movieFrame(String Username) throws Exception {
		newCart = new shoppingCart();
		this.shoppingList = new ArrayList<String>();
		this.Username = Username;
	//	System.out.println(Username);

		String[] columnNames
		= {"Film", "Genre", "Actors", "Director", "Release Year", "Description","Cost" , "Stock", ""};

		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();
		int count = 0;

		maintain.load(path);
		for(movie u: maintain.movies){
			count++;
		}

		Object[][] data = new Object[count][9];
		int count2 = 0;
		for(movie u: maintain.movies) {
			for(int j = 0; j < 9; j++) {
				if(j == 0){
					data[count2][j] = u.getMovieTitle();					
				}
				else if(j == 1) {
					data[count2][j] = u.getGenre();
				}
				else if(j == 2) {
					data[count2][j] = u.getActors();
				}
				else if(j == 3) {
					data[count2][j] = u.getDirector();
				}
				else if(j == 4) {
					data[count2][j] = String.valueOf(u.getReleaseYear());
				}
				else if(j == 5) {
					data[count2][j] = u.getDescription();
				}
				else if(j == 6) {
					data[count2][j] = u.getCost();
				}
				else if(j == 7) {
					data[count2][j] = u.getStock();
				}
				else {
					data[count2][j] = "add to cart";
				}
			}
			count2++;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable jTable = new JTable(model);
		TableColumnModel tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(300);
		tcm.getColumn(1).setPreferredWidth(100);
		tcm.getColumn(2).setPreferredWidth(250);
		tcm.getColumn(3).setPreferredWidth(80);
		tcm.getColumn(4).setPreferredWidth(50);
		tcm.getColumn(5).setPreferredWidth(250);
		tcm.getColumn(6).setPreferredWidth(50);
		tcm.getColumn(7).setPreferredWidth(50);
		tcm.getColumn(8).setPreferredWidth(150);

		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand() );
				String temp = (String) table.getValueAt(table.getSelectedRow(), 0) + " has been added to cart";
				int temp2 = (Integer) table.getValueAt(table.getSelectedRow(), 7);

				//				System.out.println(result);
				String temp3 = (String) table.getValueAt(table.getSelectedRow(), 0);
				if(shoppingList.contains(temp3)) {
					JFrame frame = new JFrame("Already in cart");
					JLabel cartMessage = new JLabel("Item is already in cart");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if(temp2 <= 0) {
					JFrame frame = new JFrame("Out of Stock");
					JLabel cartMessage = new JLabel("Item is out of stock");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else {
					shoppingList.add(temp3);
					try {
						newCart.add(temp3);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					System.out.println(temp3);
//					newCart.display();
//					String result = String.join(", ", shoppingList);
//					System.out.println(result);
					JFrame frame = new JFrame("Added to Cart");
					JLabel cartMessage = new JLabel(temp);
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				//				System.out.println(shoppingCart);
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 8);
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
				frame.setPreferredSize(new Dimension(1250,600));
				try {
					frame.add(new movieFrame(Username));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.pack();
				//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});
	}


	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Shopping Cart");
					try {
						frame.add(new shoppingFrame(newCart, Username));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
					//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}

			});			
//			JFrame frame = new JFrame("Shopping Cart");
//			frame.setTitle("Shopping Cart");
//			frame.setSize(280, 150);
//			frame.setLocationRelativeTo(null);
//			frame.setVisible(true);
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