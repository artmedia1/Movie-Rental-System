import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class maintainMovie {
	public ArrayList<movie> movies = new ArrayList<movie>();
	public String path;
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			movie movie = new movie();
			//name,id,email,password
			movie.setMovieTitle(reader.get("Film"));
			movie.setMovieGenre(reader.get("Genre"));
			movie.setMovieActors(reader.get("Actors"));
			movie.setMovieDirector(reader.get("Director"));
			movie.setReleaseYear(Integer.valueOf(reader.get("Year")));
			movie.setDescription(reader.get("Description"));
			movie.setCost(Double.valueOf(reader.get("Cost")));
			movie.setStock(Integer.valueOf(reader.get("Stock")));
			movies.add(movie);
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("Film");
				csvOutput.write("Genre");
		    	csvOutput.write("Actors");
				csvOutput.write("Director");
				csvOutput.write("Year");
				csvOutput.write("Description");
				csvOutput.write("Cost");
				csvOutput.write("Stock");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(movie u: movies){
					csvOutput.write(u.getMovieTitle());
					csvOutput.write(u.getGenre());
					csvOutput.write(u.getActors());
					csvOutput.write(u.getDirector());
					csvOutput.write(String.valueOf(u.getReleaseYear()));
					csvOutput.write(u.getDescription());
					csvOutput.write(String.valueOf(u.getCost()));
					csvOutput.write(String.valueOf(u.getStock()));
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void increaseStock(String movieTitle) throws Exception {
		for(movie movie: this.movies){
			if(movie.getMovieTitle().equals(movieTitle)) {
				movie.setStock(movie.getStock() + 1);
				String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
				this.update(path);
			}
		}
	}
	
	public void decreaseStock(String movieTitle) throws Exception {
		for(movie movie: this.movies){
			if(movie.getMovieTitle().equals(movieTitle)) {
				movie.setStock(movie.getStock() - 1);
				String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
				this.update(path);
			}
		}
	}
	
	
	public static void main(String [] args) throws Exception{
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();
	
		maintain.load(path);
//		for(movie u: maintain.movies){
//			
//			System.out.println(u.toString());
//		}
//		
////		User newUser = new User("t5", 4, "t4@yorku.ca", null);
////		maintain.users.add(newUser);
//
//		System.out.println();
		
		maintain.update(path);
	}
}
