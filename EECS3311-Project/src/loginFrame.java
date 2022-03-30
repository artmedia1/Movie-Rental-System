import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class loginFrame extends JFrame implements ActionListener {
	private Button btn3;
	private JTextField askUsername;
	private JTextField askPassword;


	public loginFrame(){

		//    	dispose(); //closes last JFrame
		JFrame f = new JFrame("Login");
		//set size and location of frame
		f.setSize(320, 300);
		//make sure it quits when x is clicked
	//	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set look and feel
		f.setLocationRelativeTo(null);
		JLabel username = new JLabel("Username or Email Address");
		username.setBounds(50, 50, 200, 30);
		this.askUsername = new JTextField();
		//set size of the text box
		askUsername.setBounds(50, 80, 200, 30);

		JLabel password = new JLabel("Password");
		password.setBounds(50, 130, 200, 30);
		//set size of the text box
		this.askPassword = new JTextField();
		askPassword.setBounds(50, 160, 200, 30);
		//add elements to the frame
		btn3 = new Button("Login");
		btn3.addActionListener(this); //this refers to your current frame
		btn3.setBounds(100, 200, 100, 30);
		f.add(btn3, BorderLayout.CENTER);


//		btn3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				String userOrEmail = askUsername.getText();
//				String password = askPassword.getText();
//				
//				MaintainUser maintain = new MaintainUser();
//				String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
//				try {
//					maintain.load(path);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				for(User u: maintain.users){
//					if (u.getEmail().equals(userOrEmail) || u.getUsername().equals(userOrEmail)){
//						if(u.getPassword().equals(password)) {
//							System.out.println("test");
//						}
//					}
//				}
//			}
//		});

		f.add(username);
		f.add(askUsername);
		f.add(password);
		f.add(askPassword);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new loginFrame();
	}
	


	public void actionPerformed(ActionEvent e) {
		Boolean found = false;
		String userOrEmail = askUsername.getText();
		String password = askPassword.getText();
		
		MaintainUser maintain = new MaintainUser();
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		try {
			maintain.load(path);
		} catch (Exception ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
		}

		for(User u: maintain.users){
			if (u.getEmail().equals(userOrEmail) || u.getUsername().equals(userOrEmail)){
				if(u.getPassword().equals(password)) {
					found = true;
					if(u.getAccountType().equals(AccountType.Customer)) {
						new userFrame(u.getUsername());
						break;
					}
					else if(u.getAccountType().equals(AccountType.Admin)) {
						new AdminFrame(u.getUsername());
						break;
					}
				}
			}
		}
		if(found == false) {
			JFrame frame = new JFrame("Login");
			frame.setSize(280, 150);
			JLabel message = new JLabel("Invalid Username or Password");
			message.setHorizontalAlignment(JLabel.CENTER);
			message.setVerticalAlignment(JLabel.CENTER);
			frame.add(message);		
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}	
	}	
}
 