import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CreditCardFrame extends JFrame implements ActionListener{
	private JTextField getCreditCardNumber;
	private JTextField getCreditCardExpiration;
	private JTextField getCreditCardName;
	private JTextField getCreditCardCVV;
	private orders newOrder;
	private String Username;
	private Button btn1;
	// JPanel
	JPanel pnlButton = new JPanel();
	// Buttons
	Button button;

	public CreditCardFrame(String Username, orders newOrder) {
		this.Username = Username;
		this.newOrder = newOrder;
		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel creditCardName = new JLabel("Name");
		creditCardName.setBounds(40,20,200,20);
		p.add(creditCardName);
		
		this.getCreditCardName = new JTextField();
		getCreditCardName.setBounds(40, 40, 500, 20);
		p.add(getCreditCardName);
	
		JLabel creditCardNumber = new JLabel("Credit Card Number");
		creditCardNumber.setBounds(40,100,200,20);
		p.add(creditCardNumber);
		
		this.getCreditCardNumber = new JTextField();
		getCreditCardNumber.setBounds(40, 120, 500, 20);
		p.add(getCreditCardNumber);
		
		JLabel creditCardExpiration = new JLabel("Expiration Date");
		creditCardExpiration.setBounds(40,180,200,20);
		p.add(creditCardExpiration);
		
		this.getCreditCardExpiration = new JTextField();
		getCreditCardExpiration.setBounds(40, 200, 500, 20);
		p.add(getCreditCardExpiration);
		
		JLabel creditCardCVV = new JLabel("CVV");
		creditCardCVV.setBounds(40,260,200,20);
		p.add(creditCardCVV);
		
		this.getCreditCardCVV = new JTextField();
		getCreditCardCVV.setBounds(40, 280, 500, 20);
		p.add(getCreditCardCVV);
		
		btn1 = new Button("Place Order");
		btn1.addActionListener(this);
		btn1.setBounds(190,320,200,40);
		p.add(btn1);


		getContentPane().add(p);
		//setLayout(null);
		setSize(600,420);
		setLocationRelativeTo(null);
		setVisible(true);

	}



	public void actionPerformed(ActionEvent e){
		String userPath = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		String orderPath = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\orders.csv";
		MaintainUser maintainUsers = new MaintainUser();
		maintainOrder maintainOrder = new maintainOrder();

	
		try {
			maintainUsers.load(userPath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			maintainOrder.load(orderPath);
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		for(User u : maintainUsers.users) {
			if(u.getUsername().equals(Username)) {
				u.setPoints(u.getPoints() + 1);
			}
		}
		
		try {
			maintainUsers.update(userPath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		maintainOrder.orders.add(newOrder);
		
		try {
			maintainOrder.update(orderPath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFrame frame = new JFrame("Order Received");
		JLabel cartMessage = new JLabel("Order has been placed");
		cartMessage.setHorizontalAlignment(JLabel.CENTER);
		cartMessage.setVerticalAlignment(JLabel.CENTER);
		frame.add(cartMessage);
		frame.setSize(560, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}	
}

