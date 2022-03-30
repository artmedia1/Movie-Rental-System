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

public class SettingsFrame extends JFrame implements ActionListener{
	private JTextField changePassword;
	private JTextField confirmPassword;
	private JTextField changeEmail;
	private JTextField confirmEmail;
	private JTextField changeName;
	private JTextField confirmName;	private String Username;
	private Button btn1, btn2, btn3, btn4;
	// JPanel
	JPanel pnlButton = new JPanel();
	// Buttons
	Button button;

	public SettingsFrame(String Username) {
		this.Username = Username;

		JPanel p = new JPanel();
		p.setLayout(null);

		btn1 = new Button("View Loyalty Points");
		btn1.addActionListener(this);
		btn1.setBounds(20,20,150,60);
		p.add(btn1);

		btn2 = new Button("Change Password");
		btn2.addActionListener(this);
		btn2.setBounds(190,20,150,60);
		p.add(btn2);


		btn3 = new Button("Change Email");
		btn3.addActionListener(this);
		btn3.setBounds(20,100,150,60);
		p.add(btn3);

		btn4 = new Button("Change Name");
		btn4.addActionListener(this);
		btn4.setBounds(190,100,150,60);
		p.add(btn4);



		getContentPane().add(p);
		//setLayout(null);
		setSize(380,220);
		setLocationRelativeTo(null);
		setVisible(true);

	}



	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1) {
			JFrame frame = new JFrame("Loyalty Points");

			String[] columns = new String[] {"Loyal Points"};

			String [] columnNames = {"Loyalty Points"};
			String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
			MaintainUser maintain = new MaintainUser();

			try {
				maintain.load(path);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Object[][] data = new Object[1][1];
			for(User u : maintain.users) {
				if(u.getUsername().equals(Username)) {
					data[0][0] = u.getPoints();
				}
			}
			//create table with data
			JTable table = new JTable(data, columns);

			//add the table to the frame
			frame.add(new JScrollPane(table));

			frame.setLocationRelativeTo(null);
			frame.setSize(280, 150);

			frame.setTitle("Loyalty Points");    
			frame.pack();
			frame.setVisible(true);
		}
		else if(e.getSource() == btn2) {
			ChangePassword changePassword = new ChangePassword(Username);
		}
		else if (e.getSource() == btn3) {
			ChangeEmail changeEmail = new ChangeEmail(Username);
		}
		else {
			ChangeName changeName = new ChangeName(Username);
		}
	}

}

