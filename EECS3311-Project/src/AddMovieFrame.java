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

public class AddMovieFrame extends JFrame implements ActionListener{
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

	public AddMovieFrame() {

		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel newName = new JLabel("Movie Title");
		newName.setBounds(40,20,200,20);
		p.add(newName);
		
		this.changeMovieTitle = new JTextField();
		changeMovieTitle.setBounds(40, 40, 500, 20);
		p.add(changeMovieTitle);
	
		JLabel newGenre = new JLabel("Movie Genre");
		newGenre.setBounds(40,100,200,20);
		p.add(newGenre);
		
		this.changeGenre = new JTextField();
		changeGenre.setBounds(40, 120, 500, 20);
		p.add(changeGenre);
		
		JLabel newActors = new JLabel("Movie Actors");
		newActors.setBounds(40,180,200,20);
		p.add(newActors);
		
		this.changeActors = new JTextField();
		changeActors.setBounds(40, 200, 500, 20);
		p.add(changeActors);
		
		JLabel updateDirector = new JLabel("Movie Director");
		updateDirector.setBounds(40,260,200,20);
		p.add(updateDirector);
		
		this.changeDirector = new JTextField();
		changeDirector.setBounds(40, 280, 500, 20);
		p.add(changeDirector);
		
		JLabel updateYear = new JLabel("Movie Release Year");
		updateYear.setBounds(40,340,200,20);
		p.add(updateYear);
		
		this.changeYear = new JTextField();
		changeYear.setBounds(40, 360, 500, 20);
		p.add(changeYear);
		
		JLabel newDescription = new JLabel("Movie Description");
		newDescription.setBounds(40,420,200,20);
		p.add(newDescription);
		
		this.changeDescription = new JTextField();
		changeDescription.setBounds(40, 440, 500, 20);
		p.add(changeDescription);
		
		JLabel newCost = new JLabel("Movie Cost");
		newCost.setBounds(40,500,200,20);
		p.add(newCost);
		
		this.changeCost = new JTextField();
		changeCost.setBounds(40, 520, 500, 20);
		p.add(changeCost);
		
		JLabel updateStockTitle = new JLabel("Movie Stock");
		updateStockTitle.setBounds(40,580,200,20);
		p.add(updateStockTitle);
		
		this.updateStock = new JTextField();
		updateStock.setBounds(40, 600, 500, 20);
		p.add(updateStock);
		
		btn1 = new Button("Add Movie");
		btn1.addActionListener(this);
		btn1.setBounds(40,620,200,20);
		p.add(btn1);


		getContentPane().add(p);
		//setLayout(null);
		setSize(600,800);
		setLocationRelativeTo(null);
		setVisible(true);

	}



	public void actionPerformed(ActionEvent e) { //Loads a database of movies from a csv
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();
		try {
			maintain.load(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		movie newMovie = new movie(
				changeMovieTitle.getText(), 
				changeGenre.getText(), 
				changeActors.getText(), 
				changeDirector.getText(),
				Integer.valueOf(changeYear.getText()),
				changeDescription.getText(), 
				Double.valueOf(changeCost.getText()), 
				Integer.valueOf(updateStock.getText())
		);
		maintain.movies.add(newMovie);
		try {
			maintain.update(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JFrame frame = new JFrame("Added");
		JLabel cartMessage = new JLabel("Movie has been added to the database");
		cartMessage.setHorizontalAlignment(JLabel.CENTER);
		cartMessage.setVerticalAlignment(JLabel.CENTER);
		frame.add(cartMessage);
		frame.setSize(560, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}	
}

