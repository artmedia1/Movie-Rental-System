import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PaymentFrame extends JFrame implements ActionListener {
	private Button btn1, btn2;

	private String Username;
	private int loyaltyPoints;
	private orders newOrder;

	public PaymentFrame(String Username, int loyaltyPoints, orders newOrder){
		this.Username = Username;
		this.loyaltyPoints = loyaltyPoints;
		this.newOrder = newOrder;
		JFrame f = new JFrame("Payment Selection");

		f.setSize(320, 200);

		f.setLocationRelativeTo(null);
		JLabel askPayment = new JLabel("Please select your payment type");
		askPayment.setBounds(50, 50, 200, 30);
		
		btn1 = new Button("Credit");
		btn1.addActionListener(this); //this refers to your current frame
		btn1.setBounds(40, 80, 100, 30);
		f.add(btn1, BorderLayout.CENTER);
		
		btn2 = new Button("Loyalty Points");
		btn2.addActionListener(this); //this refers to your current frame
		btn2.setBounds(160, 80, 100, 30);
		f.add(btn2, BorderLayout.CENTER);

		f.add(askPayment);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new loginFrame();
	}
	


	public void actionPerformed(ActionEvent e) {
		maintainOrder maintain = new maintainOrder();
		MaintainUser maintainUser = new MaintainUser();
		String path2 = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\orders.csv";
		try {
			maintain.load(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			maintainUser.load(path2);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
				
		if(e.getSource() == btn1) {
			new CreditCardFrame(Username, newOrder);
		}
		else if(e.getSource() == btn2) {
			int commas = 0;		
			for(int i = 0; i < newOrder.getShoppingCart().length(); i++) {
			    if(newOrder.getShoppingCart().charAt(i) == ',') 
			    	commas++;
			}
			JFrame frame = new JFrame("Payment");
			frame.setTitle("Payment");
			if(loyaltyPoints < ((commas+1)*10)) {
				JLabel cartMessage = new JLabel("Not enough loyalty points");
				cartMessage.setHorizontalAlignment(JLabel.CENTER);
				cartMessage.setVerticalAlignment(JLabel.CENTER);
				frame.add(cartMessage);
			}
			else {
				JLabel cartMessage = new JLabel("Your order has been placed");
				cartMessage.setHorizontalAlignment(JLabel.CENTER);
				cartMessage.setVerticalAlignment(JLabel.CENTER);
				frame.add(cartMessage);
				for(User u : maintainUser.users) {
					if(u.username.equals(Username)) {
						u.setPoints(u.getPoints() - ((commas+1)*10));
						try {
							maintainUser.update(path2);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

				maintain.orders.add(newOrder);
				try {
					maintain.update(path);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			
			
			frame.setSize(450, 200);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		}
	}

	
}
 