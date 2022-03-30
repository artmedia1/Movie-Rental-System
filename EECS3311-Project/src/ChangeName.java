import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ChangeName extends JFrame implements ActionListener {
	private Button btn3;
	private JTextField changeName;
	private JTextField confirmName;
	private String Username;

	public ChangeName(String Username){
		this.Username = Username;
		JFrame f = new JFrame("Change Name");

		f.setSize(320, 300);

		f.setLocationRelativeTo(null);
		JLabel newName = new JLabel("New Name");
		newName.setBounds(50, 50, 200, 30);
		this.changeName = new JTextField();

		changeName.setBounds(50, 80, 200, 30);

		JLabel confirmNewName = new JLabel("Confirm new Name");
		confirmNewName.setBounds(50, 130, 200, 30);

		this.confirmName = new JTextField();
		confirmName.setBounds(50, 160, 200, 30);

		btn3 = new Button("Change Name");
		btn3.addActionListener(this); //this refers to your current frame
		btn3.setBounds(50, 200, 100, 30);
		f.add(btn3, BorderLayout.CENTER);

		f.add(newName);
		f.add(changeName);
		f.add(confirmNewName);
		f.add(confirmName);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args){
		new loginFrame();
	}
	


	public void actionPerformed(ActionEvent e) {
		String name = changeName.getText();
		String confirm = confirmName.getText();

		
		MaintainUser maintain = new MaintainUser();
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		try {
			maintain.load(path);
		} catch (Exception ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
		}
		
		if(name.equals(confirm) && !name.isEmpty()) {
			for(User u: maintain.users){
				if (u.getUsername().equals(Username)){
					u.setName(name);
					JFrame frame = new JFrame("Change Name");
					JLabel cartMessage = new JLabel("Your name has been changed");
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
		else if(name.isEmpty() || confirm.isEmpty()) {
			JFrame frame = new JFrame("Change Name");
			JLabel cartMessage = new JLabel("One of the fields is empty");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(200, 100);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		else {
			JFrame frame = new JFrame("Change Name");
			JLabel cartMessage = new JLabel("Names do not match");
			cartMessage.setHorizontalAlignment(JLabel.CENTER);
			cartMessage.setVerticalAlignment(JLabel.CENTER);
			frame.add(cartMessage);
			frame.setSize(200, 100);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}

	}

	
}
 