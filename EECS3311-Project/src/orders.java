import java.util.Date;
import java.util.List;

public class orders {
	public int orderID;
	public String shoppingCart;
	public double orderTotal;
	public orderStatus orderStatus;
	public String shippingAddress;
	public String username;
	
	public orders(int orderID, String shoppingCart, double orderTotal, orderStatus orderStatus, String shippingAddress, String username) {
		super();
		this.orderID = orderID;
		this.shoppingCart = shoppingCart;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
		this.shippingAddress = shippingAddress;	
		this.username = username;
	}
	
	public orders(){
		super();
	}

//	@Override
//	public void add(movie movie) {
//		// TODO Auto-generated method stub
//		
//	}

	public int getOrderID() {
		return orderID;
	}

	public String getShoppingCart() {
		return shoppingCart;
	}


	public double getOrderTotal() {
		return orderTotal;
	}
	
	public orderStatus getOrderStatus(){
		return orderStatus;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setOrderID(int newOrderID) {
		this.orderID = newOrderID;
	}
	
	public void setShoppingCart(String newShoppingCart) {
		this.shoppingCart = newShoppingCart;
	}
	
	public void setOrderTotal(double newOrderTotal) {
		this.orderTotal = newOrderTotal;
	}

	public void setOrderStatus(orderStatus newOrderStatus) {
		this.orderStatus = newOrderStatus;
	}

	public void setShippingAddress(String newShippingAddress) {
		this.shippingAddress = newShippingAddress;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return "Order [OrderID=" + orderID + ", Items=" + shoppingCart + ", Total=" + orderTotal + ", Status=" + orderStatus + ", Shipping Address=" + shippingAddress + ", =" + "]";
	}


//	@Override
//	public String toString() {
//		return "User [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password + "]";
//	}

}


