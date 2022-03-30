import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class maintainOrder {
	public ArrayList<orders> orders = new ArrayList<orders>();
	public String path;
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			orders order = new orders();
			//name,id,email,password
			order.setOrderID(Integer.valueOf(reader.get("OrderID")));
			order.setShoppingCart(reader.get("Items"));
			order.setOrderTotal(Double.valueOf(reader.get("Total")));
			order.setOrderStatus(orderStatus.valueOf(reader.get("Status")));
			order.setShippingAddress(reader.get("Shipping Address"));
			order.setUsername(reader.get("Username"));
			orders.add(order);
			
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("OrderID");
				csvOutput.write("Items");
		    	csvOutput.write("Total");
				csvOutput.write("Status");
				csvOutput.write("Shipping Address");
				csvOutput.write("Username");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(orders u: orders){
					csvOutput.write(String.valueOf(u.getOrderID()));
					csvOutput.write(u.getShoppingCart());
					csvOutput.write(String.valueOf(u.getOrderTotal()));
					csvOutput.write(String.valueOf(u.getOrderStatus()));
					csvOutput.write(u.getShippingAddress());
					csvOutput.write(u.getUsername());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void cancelOrder(int OrderID) throws Exception {
		for(orders u: this.orders){
			if(u.getOrderID() == OrderID) {
				u.setOrderStatus(orderStatus.Cancelled);
			}
		}
	}
	
	public void shipOrder(int OrderID) throws Exception {
		for(orders u: this.orders){
			if(u.getOrderID() == OrderID) {
				u.setOrderStatus(orderStatus.Shipped);
			}
		}
	}
	
	public void pendingOrder(int OrderID) throws Exception {
		for(orders u: this.orders){
			if(u.getOrderID() == OrderID) {
				u.setOrderStatus(orderStatus.Pending);
			}
		}
	}
	
	public static void main(String [] args) throws Exception{
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\movies.csv";
		maintainOrder maintain = new maintainOrder();
		
		maintain.load(path);
		for(orders u: maintain.orders){
//			if (u.getEmail().equals("t4@yorku.ca")){
//			//	System.out.println(u.getName());
//				
//			
////				maintain.orderss.remove(u);
//			}
//		//	System.out.println(u.toString());
		}
		
//		orders neworders = new orders("t5", 4, "t4@yorku.ca", null);
//		maintain.orderss.add(neworders);

//		System.out.println();
		
		maintain.update(path);
	}
}
