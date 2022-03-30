import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class SystemFrame extends JFrame implements ActionListener {
	private Button btn1, btn2;



	public SystemFrame(){

		setLayout(new FlowLayout());


		btn1 = new Button("View All Accounts");
		btn1.addActionListener(this); //this refers to your current frame
		add(btn1);
		btn2 = new Button("Change Employee Account Types");
		btn2.addActionListener(this);
		add(btn2);


		setTitle("System");
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
		new SystemFrame();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn2) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Change Employee Type");
					frame.setPreferredSize(new Dimension(1250,600));
					try {
						frame.add(new EditEmployeeAccountFrame());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
					//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}

			});
		}
		else {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JFrame frame = new JFrame("Movie Database");
					frame.setPreferredSize(new Dimension(1250,600));
					try {
						frame.add(new SystemUserFrame());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.pack();
					//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}

			});
		}
	}
}    