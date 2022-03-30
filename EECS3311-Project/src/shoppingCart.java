import java.util.ArrayList;
import java.util.List;

public class shoppingCart {
	public ArrayList<movie> shoppingCart = new ArrayList<movie>();
	
	public void add(String movieName) throws Exception {
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();
		maintain.load(path);
		
		for(movie u : maintain.movies) {

			if(u.getMovieTitle().equals(movieName)) {
				shoppingCart.add(u);
//				System.out.println("we in1");
	//			System.out.println(shoppingCart.toString());
			}
		}
	}
	
	public void remove(String movieName) throws Exception {
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainMovie maintain = new maintainMovie();
		maintain.load(path);
		
		for(int i = 0; i < shoppingCart.size(); i++) {
			if(shoppingCart.get(i).getMovieTitle().equals(movieName)) {
		//		System.out.println("We IN");
				shoppingCart.remove(i);
			}
		}
	}
	
	public void display() {
		for(int i = 0; i < shoppingCart.size(); i++) {
			System.out.println(shoppingCart.get(i).getMovieTitle());
		}
	}
	
	public int size() {
		return shoppingCart.size();
	}
}




//
//this.shoppingCart = shoppingCart;
//
//
//String[] columnNames
//= {"Movie", "Total"};
//
//String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
//maintainMovie maintain = new maintainMovie();
//
//
//Object[][] data = new Object[shoppingCart.size()][3];
//int count2 = 0;
//for(movie u: shoppingCart.shoppingCart) {
//	for(int j = 0; j < 3; j++) {
//		if(j == 0){
//			data[count2][j] = u.getMovieTitle();					
//		}
//		else if(j == 1) {
//			data[count2][j] = u.getCost();
//		}
//		else {
//			data[count2][j] = "Remove from Cart";
//		}
//	}
//	count2++;
//}