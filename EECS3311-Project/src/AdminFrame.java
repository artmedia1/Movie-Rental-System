import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame implements ActionListener {
	private Button btn1, btn2, btn3, btn4, btn5, btn6;
	private String Username;


	public AdminFrame(String Username){
		this.Username = Username;
		setLayout(new FlowLayout());


		btn1 = new Button("View Movies");
		btn1.addActionListener(this); //this refers to your current frame
		add(btn1);
		btn2 = new Button("View Orders");
		btn2.addActionListener(this);
		add(btn2);
		btn3 = new Button("User Settings");
		btn3.addActionListener(this);
		add(btn3);
		btn4 = new Button("Add Movie");
		btn4.addActionListener(this);
		add(btn4);
		btn5 = new Button("Phone Order");
		btn5.addActionListener(this);
		add(btn5);
		btn6 = new Button("View Users");
		btn6.addActionListener(this);
		add(btn6);


		setTitle("Options");
		setSize(280, 150);
		setLocationRelativeTo(null);
		setVisible(true);

		// close the window
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
				System.exit(0); //calling the method is a must
			}
		});

	}
	public void main(String[] args){
		new userFrame(Username);
	}

	public void actionPerformed(ActionEvent e) {  //creates the AdminMovieFrame
		if (e.getSource() == btn1) { 
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Movie Database");
					frame.setPreferredSize(new Dimension(1250,600));
					try {
						frame.add(new AdminMovieFrame(Username));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
	//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});
		}
		else if (e.getSource() == btn2) { //creates the AdminOrderFrame
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Orders");
					try {
						frame.add(new AdminOrderFrame());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
		//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});

		}
		else if(e.getSource() == btn4) {
			AddMovieFrame addMovieFrame = new AddMovieFrame();
		}
		else if(e.getSource() == btn5) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Movie Database");
					frame.setPreferredSize(new Dimension(1250,600));
					try {
						frame.add(new PhoneOrderFrame());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
	//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});
		}
		else if(e.getSource() == btn6) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Users Database");
					frame.setPreferredSize(new Dimension(1250,600));
					try {
						frame.add(new EditUsersFrame());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
	//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});
		}
		else {
			SettingsFrame settingsFrame = new SettingsFrame(Username);
		}
	}
}    