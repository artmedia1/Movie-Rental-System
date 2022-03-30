import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class userFrame extends JFrame implements ActionListener {
	private Button btn1, btn2, btn3;
	private String Username;


	public userFrame(String Username){
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
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
	//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});
		}
		else if (e.getSource() == btn2) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Orders");
					try {
						frame.add(new userOrderFrame(Username));
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
		else {
			SettingsFrame settingsFrame = new SettingsFrame(Username);




			////        try {
			////			frame.add(new movieFrame());
			////		} catch (Exception e1) {
			////			// TODO Auto-generated catch block
			////			e1.printStackTrace();
			////		}
			//
			//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//        frame.setLocationRelativeTo(null);
			//        frame.setVisible(true);
		}
	}
}    