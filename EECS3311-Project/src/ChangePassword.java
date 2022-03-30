import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ChangePassword extends JFrame implements ActionListener {
	private Button btn3;
	private JTextField changePassword;
	private JTextField confirmPassword;
	private String Username;

	public ChangePassword(String Username){
		this.Username = Username;
		JFrame f = new JFrame("Change Password");

		f.setSize(320, 300);

		f.setLocationRelativeTo(null);
		JLabel newPassword = new JLabel("New Password");
		newPassword.setBounds(50, 50, 200, 30);
		this.changePassword = new JTextField();

		changePassword.setBounds(50, 80, 200, 30);

		JLabel confirmNewPassword = new JLabel("Confirm new Password");
		confirmNewPassword.setBounds(50, 130, 200, 30);

		this.confirmPassword = new JTextField();
		confirmPassword.setBounds(50, 160, 200, 30);

		btn3 = new Button("Change Password");
		btn3.addActionListener(this); //this refers to your current frame
		btn3.setBounds(50, 200, 100, 30);
		f.add(btn3, BorderLayout.CENTER);

		f.add(newPassword);
		f.add(changePassword);
		f.add(confirmNewPassword);
		f.add(confirmPassword);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new loginFrame();
	}
	


	public void actionPerformed(ActionEvent e) {
		String password = changePassword.getText();
		String confirm = confirmPassword.getText();

		
		MaintainUser maintain = new MaintainUser();
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		try {
			maintain.load(path);
		} catch (Exception ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
		}
		
		if(password.equals(confirm) && !password.isEmpty()) {
			for(User u: maintain.users){
				if (u.getUsername().equals(Username)){
					u.setPassword(password);
					JFrame frame = new JFrame("Change Password");
					JLabel cartMessage = new JLabel("Your password has been changed");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(400, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					try {
						maintain.update(path);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		}
		else if(password.isEmpty() || confirm.isEmpty()) {
			JFrame frame = new JFrame("Change Password");
			JLabel cartMessage = new JLabel("One of the fields is empty");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(200, 100);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		else {
			JFrame frame = new JFrame("Change Password");
			JLabel cartMessage = new JLabel("Passwords do not match");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(200, 100);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}

	}

	
}
 