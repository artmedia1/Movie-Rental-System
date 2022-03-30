
public class User {
	public String name;
	public int id;
	public String email;
	public String password;
	public String username;
	public String shippingAddress;
	public int loyaltyPoints;
	public AccountType accountType;
	
	public User(
			String name, 
			int id, 
			String email, 
			String password, 
			String username, 
			String shippingAddress, 
			int loyaltyPoints, 
			AccountType accountType) {
		
		super();
		this.name = name;
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.shippingAddress = shippingAddress;
		this.loyaltyPoints = loyaltyPoints;
		this.accountType = accountType;
	}
	
	public User(){
		super();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getAddress() {
		return shippingAddress;
	}
	
	public void setAddress(String newAddress) {
		this.shippingAddress = newAddress;
	}
	
	public int getPoints() {
		return loyaltyPoints;
	}
	
	public void setPoints(int newPoints) {
		this.loyaltyPoints = newPoints;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
}
