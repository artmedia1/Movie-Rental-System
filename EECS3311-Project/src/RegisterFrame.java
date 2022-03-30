import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class RegisterFrame extends JFrame implements ActionListener{
	private JTextField registerName;
	private JTextField registerEmail;
	private JTextField registerPassword;
	private JTextField registerUsername;
	private JTextField registerShippingAddress;

	private Button btn1;
	// JPanel
	JPanel pnlButton = new JPanel();
	// Buttons
	Button button;

	public RegisterFrame() {

		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel name = new JLabel("Name");
		name.setBounds(40,20,200,20);
		p.add(name);
		
		this.registerName = new JTextField();
		registerName.setBounds(40, 40, 500, 20);
		p.add(registerName);
	
		JLabel username = new JLabel("Username");
		username.setBounds(40,100,200,20);
		p.add(username);
		
		this.registerUsername = new JTextField();
		registerUsername.setBounds(40, 120, 500, 20);
		p.add(registerUsername);
		
		JLabel Email = new JLabel("Password");
		Email.setBounds(40,180,200,20);
		p.add(Email);
		
		this.registerPassword = new JTextField();
		registerPassword.setBounds(40, 200, 500, 20);
		p.add(registerPassword);
		
		JLabel email = new JLabel("Email");
		email.setBounds(40,260,200,20);
		p.add(email);
		
		this.registerEmail = new JTextField();
		registerEmail.setBounds(40, 280, 500, 20);
		p.add(registerEmail);
		
		JLabel shippingAddress = new JLabel("Shipping Address");
		shippingAddress.setBounds(40,340,200,20);
		p.add(shippingAddress);
		
		this.registerShippingAddress = new JTextField();
		registerShippingAddress.setBounds(40, 360, 500, 20);
		p.add(registerShippingAddress);

			
		btn1 = new Button("Register");
		btn1.addActionListener(this);
		btn1.setBounds(200,400,200,20);
		p.add(btn1);


		getContentPane().add(p);
		//setLayout(null);
		setSize(600,480);
		setLocationRelativeTo(null);
		setVisible(true);

	}



	public void actionPerformed(ActionEvent e) {
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		MaintainUser maintain = new MaintainUser();
		Boolean registered = false;
		String temp = "";
		try {
			maintain.load(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(User u: maintain.users) {
			if(u.getUsername().equals(registerUsername.getText()) && u.getEmail().equals(registerEmail.getText())){
				registered = true;
				temp = "Username and Email are already registered";
			}
			else if(u.getEmail().equals(registerEmail.getText())) {
				registered = true;
				temp = "Email is already registered";
			}
			else if(u.getUsername().equals(registerUsername.getText())){
				registered = true;
				temp = "Username is already registered";
			}
		}
		if(registered == true) {
			JFrame frame = new JFrame("Already Registered");
			JLabel cartMessage = new JLabel(temp);
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(560, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		else {
			User newUser = new User(
					registerName.getText(), 
					maintain.users.size() + 1, 
					registerEmail.getText(),
					registerPassword.getText(),
					registerUsername.getText(),
					registerShippingAddress.getText(),
					0,
					AccountType.Customer
			);
			
			maintain.users.add(newUser);
			try {
				maintain.update(path);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JFrame frame = new JFrame("Added");
			JLabel cartMessage = new JLabel("User has been registered");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(300, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}	
}

