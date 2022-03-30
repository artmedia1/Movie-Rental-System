import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class loginScreen extends JFrame implements ActionListener {
	private Button btn1, btn2;



	public loginScreen(){

		setLayout(new FlowLayout());


		btn1 = new Button("Login");
		btn1.addActionListener(this); //this refers to your current frame
		add(btn1);
		btn2 = new Button("Register");
		btn2.addActionListener(this);
		add(btn2);


		setTitle("Login");
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
	public static void main(String[] args){
		new loginScreen();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn2) {
			new RegisterFrame();
		}
		else {
			new loginFrame();




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