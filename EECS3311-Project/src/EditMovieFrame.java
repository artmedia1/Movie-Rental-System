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

public class EditMovieFrame extends JFrame implements ActionListener{
	private JTextField changeGenre;
	private JTextField changeActors;
	private JTextField changeMovieTitle;
	private JTextField changeDirector;
	private JTextField changeYear;
	private JTextField changeDescription;
	private JTextField changeCost;
	private JTextField updateStock;
	private String movieTitle;
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
	// JPanel
	JPanel pnlButton = new JPanel();
	// Buttons
	Button button;

	public EditMovieFrame(String movieTitle) {
		this.movieTitle = movieTitle;

		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel newName = new JLabel("Update Movie Title");
		newName.setBounds(40,20,200,20);
		p.add(newName);
		
		this.changeMovieTitle = new JTextField();
		changeMovieTitle.setBounds(40, 40, 500, 20);
		p.add(changeMovieTitle);
	
		btn1 = new Button("Update Movie Title");
		btn1.addActionListener(this);
		btn1.setBounds(40,60,200,20);
		p.add(btn1);
//
		JLabel newGenre = new JLabel("Update Movie Genre");
		newGenre.setBounds(40,100,200,20);
		p.add(newGenre);
		
		this.changeGenre = new JTextField();
		changeGenre.setBounds(40, 120, 500, 20);
		p.add(changeGenre);
		
		btn2 = new Button("Update Movie Genre");
		btn2.addActionListener(this);
		btn2.setBounds(40,140,200,20);
		p.add(btn2);
//	
		JLabel newActors = new JLabel("Update Movie Actors");
		newActors.setBounds(40,180,200,20);
		p.add(newActors);
		
		this.changeActors = new JTextField();
		changeActors.setBounds(40, 200, 500, 20);
		p.add(changeActors);
		
		btn3 = new Button("Update Movie Actors");
		btn3.addActionListener(this);
		btn3.setBounds(40,220,200,20);
		p.add(btn3);
//
		JLabel updateDirector = new JLabel("Update Movie Director");
		updateDirector.setBounds(40,260,200,20);
		p.add(updateDirector);
		
		this.changeDirector = new JTextField();
		changeDirector.setBounds(40, 280, 500, 20);
		p.add(changeDirector);
		
		btn4 = new Button("Update Movie Director");
		btn4.addActionListener(this);
		btn4.setBounds(40,300,200,20);
		p.add(btn4);
//
		JLabel updateYear = new JLabel("Update Movie Release Year");
		updateYear.setBounds(40,340,200,20);
		p.add(updateYear);
		
		this.changeYear = new JTextField();
		changeYear.setBounds(40, 360, 500, 20);
		p.add(changeYear);
		
		btn5 = new Button("Update Movie Release Year");
		btn5.addActionListener(this);
		btn5.setBounds(40,380,200,20);
		p.add(btn5);
//
		JLabel newDescription = new JLabel("Update Movie Description");
		newDescription.setBounds(40,420,200,20);
		p.add(newDescription);
		
		this.changeDescription = new JTextField();
		changeDescription.setBounds(40, 440, 500, 20);
		p.add(changeDescription);
		
		btn6 = new Button("Update Movie Description");
		btn6.addActionListener(this);
		btn6.setBounds(40,460,200,20);
		p.add(btn6);
//
		JLabel newCost = new JLabel("Update Movie Cost");
		newCost.setBounds(40,500,200,20);
		p.add(newCost);
		
		this.changeCost = new JTextField();
		changeCost.setBounds(40, 520, 500, 20);
		p.add(changeCost);
		
		btn7 = new Button("Update Movie Cost");
		btn7.addActionListener(this);
		btn7.setBounds(40,540,200,20);
		p.add(btn7);
//
		JLabel updateStockTitle = new JLabel("Update Movie Stock");
		updateStockTitle.setBounds(40,580,200,20);
		p.add(updateStockTitle);
		
		this.updateStock = new JTextField();
		updateStock.setBounds(40, 600, 500, 20);
		p.add(updateStock);
		
		btn8 = new Button("Update Movie Stock");
		btn8.addActionListener(this);
		btn8.setBounds(40,620,200,20);
		p.add(btn8);


		getContentPane().add(p);
		//setLayout(null);
		setSize(600,800);
		setLocationRelativeTo(null);
		setVisible(true);

	}



	public void actionPerformed(ActionEvent e) {
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();
		try {
			maintain.load(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(movie movie : maintain.movies) {
		//	System.out.println(movieTitle);
			if(movie.getMovieTitle().equals(movieTitle)) {
				if(e.getSource() == btn1) {
					System.out.println("1");
					movie.setMovieTitle(changeMovieTitle.getText());
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Movie title is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if(e.getSource() == btn2) {
					movie.setMovieGenre(changeGenre.getText());
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Genre is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if (e.getSource() == btn3) {
					movie.setMovieActors(changeActors.getText());
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Actors are Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if(e.getSource() == btn4){
					movie.setMovieDirector(changeDirector.getText());
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Director is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if(e.getSource() == btn5) {
					String temp = changeYear.getText();
					movie.setReleaseYear(Integer.valueOf(temp));
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Release Year is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if(e.getSource() == btn6) {
					movie.setDescription(changeDescription.getText());
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Description is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else if (e.getSource() == btn7) {
					String temp = changeCost.getText();
					movie.setCost(Double.valueOf(temp));
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Cost is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				else {
					String temp = updateStock.getText();
					movie.setStock(Integer.valueOf(temp));
					JFrame frame = new JFrame("Updated");
					JLabel cartMessage = new JLabel("Stock is Updated, reopen movie DB to update");
					cartMessage.setHorizontalAlignment(JLabel.CENTER);
					cartMessage.setVerticalAlignment(JLabel.CENTER);
					frame.add(cartMessage);
					frame.setSize(560, 300);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
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
}

