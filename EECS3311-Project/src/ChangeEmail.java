import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ChangeEmail extends JFrame implements ActionListener {
	private Button btn3;
	private JTextField changeEmail;
	private JTextField confirmEmail;
	private String Username;

	public ChangeEmail(String Username){
		this.Username = Username;
		JFrame f = new JFrame("Change Email");

		f.setSize(320, 300);

		f.setLocationRelativeTo(null);
		JLabel newEmail = new JLabel("New email");
		newEmail.setBounds(50, 50, 200, 30);
		this.changeEmail = new JTextField();

		changeEmail.setBounds(50, 80, 200, 30);

		JLabel confirmNewEmail = new JLabel("Confirm new email");
		confirmNewEmail.setBounds(50, 130, 200, 30);

		this.confirmEmail = new JTextField();
		confirmEmail.setBounds(50, 160, 200, 30);

		btn3 = new Button("Change Email");
		btn3.addActionListener(this); //this refers to your current frame
		btn3.setBounds(50, 200, 100, 30);
		f.add(btn3, BorderLayout.CENTER);

		f.add(newEmail);
		f.add(changeEmail);
		f.add(confirmNewEmail);
		f.add(confirmEmail);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new loginFrame();
	}
	


	public void actionPerformed(ActionEvent e) {
		String email = changeEmail.getText();
		String confirm = confirmEmail.getText();

		
		MaintainUser maintain = new MaintainUser();
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		try {
			maintain.load(path);
		} catch (Exception ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
		}

		if(email.equals(confirm) && !email.isEmpty()) {
			for(User u: maintain.users){

				if (u.getUsername().equals(Username)){
					u.setEmail(email);
					JFrame frame = new JFrame("Change Email");
					JLabel cartMessage = new JLabel("Your email has been changed to: " + email);
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
		else if(email.isEmpty() || confirm.isEmpty()) {
			JFrame frame = new JFrame("Change Email");
			JLabel cartMessage = new JLabel("One of the fields is empty");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(200, 100);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		else {
			JFrame frame = new JFrame("Change Email");
			JLabel cartMessage = new JLabel("Emails do not match");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(200, 100);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}

	}

	
}
 