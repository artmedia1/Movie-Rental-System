import java.util.Date;
import java.util.List;

public class movie  {
	public String movieTitle;
	public String movieActors;
	public String movieDirector;
	public String movieDescription;
	public int yearofRelease;
	public String genre;
	public double cost;
	public int stock;
	
	public movie(String movieTitle, String movieGenre, String movieActors, String movieDirector, int yearofRelease, String movieDescription, double cost, int stock) {
		super();
		this.movieTitle = movieTitle;
		this.movieActors = movieActors;
		this.movieDirector = movieDirector;
		this.movieDescription = movieDescription;
		this.yearofRelease = yearofRelease;
		this.genre = movieGenre;
		this.cost = cost;
		this.stock = stock;
	}
	
	public movie(){
		super();
	}


	public void add(movie movie) {
		// TODO Auto-generated method stub
		
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public String getGenre() {
		return genre;
	}


	public String getDescription() {
		return movieDescription;
	}
	
	public String getActors(){
		return movieActors;
	}
	
	public String getDirector() {
		return movieDirector;
	}
	
	public int getReleaseYear() {
		return yearofRelease;
	}
	
	public double getCost() {
		return cost;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setDescription(String newDescription) {
		this.movieDescription = newDescription;
	}
	
	public void setMovieTitle(String newMovieTitle) {
		this.movieTitle = newMovieTitle;
	}
	
	public void setMovieActors(String newMovieActors) {
		this.movieActors = newMovieActors;
	}

	public void setMovieDirector(String newMovieDirector) {
		this.movieDirector = newMovieDirector;
	}

	public void setReleaseYear(int newReleaseYear) {
		this.yearofRelease = newReleaseYear;
	}
	
	public void setMovieGenre(String newGenre) {
		this.genre = newGenre;
	}
	
	public void setCost(double newCost) {
		this.cost = newCost;
	}
	
	public void setStock(int newStock) {
		this.stock = newStock;
	}

	public String toString() {
		return "Movie [Title=" + movieTitle + ", Genre=" + genre + ", Actors=" + movieActors + ", Director=" + movieDirector + ", Year=" + yearofRelease + ", Description=" + movieDescription +"]";
	}


//	@Override
//	public String toString() {
//		return "User [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password + "]";
//	}

}


