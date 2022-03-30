import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainUser {
	public ArrayList<User> users = new ArrayList<User>();
	public String path;
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			User user = new User();
			//name,id,email,password
			user.setName(reader.get("name"));
			user.setId(Integer.valueOf(reader.get("id")));
			user.setEmail(reader.get("email"));
			user.setPassword(reader.get("password"));
			user.setUsername(reader.get("username"));
			user.setAddress(reader.get("ShippingAddress"));
			user.setPoints(Integer.valueOf(reader.get("points")));
			user.setAccountType(AccountType.valueOf(reader.get("AccountType")));
			users.add(user);
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("name");
				csvOutput.write("id");
		    	csvOutput.write("email");
				csvOutput.write("password");
				csvOutput.write("username");
				csvOutput.write("ShippingAddress");
				csvOutput.write("points");
				csvOutput.write("AccountType");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(User u: users){
					csvOutput.write(u.getName());
					csvOutput.write(String.valueOf(u.getId()));
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPassword());
					csvOutput.write(u.getUsername());
					csvOutput.write(u.getAddress());
					csvOutput.write(String.valueOf(u.getPoints()));
					csvOutput.write(String.valueOf(u.getAccountType()));
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void main(String [] args) throws Exception{
		String path = "C:\\Users\\randy\\Documents\\School\\EECS3311\\proj\\CSV\\user.csv";
		MaintainUser maintain = new MaintainUser();
		
		maintain.load(path);
		for(User u: maintain.users){
			if (u.getEmail().equals("t4@yorku.ca")){
			//	System.out.println(u.getName());
				
			
//				maintain.users.remove(u);
			}
		//	System.out.println(u.toString());
		}
		
//		User newUser = new User("t5", 4, "t4@yorku.ca", null);
//		maintain.users.add(newUser);

		System.out.println();
		
		maintain.update(path);
	}
}
