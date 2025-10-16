package control;

import model.Customer;
import model.Order;
import model.Orderline;
import model.Product;

public class OrderController {
	private Order tempOrder;
	
	public OrderController() {
		
	}
	
	public void newOrder() {
		tempOrder = new Order();
	}
	
	public boolean newOrderline(Product p, int qty) {
		/*set false
		 * Check stock
		 * if available new Orderline
		 * set true
		 * tempOrder.addOrderline
		 * 
		 */
		
		return true;
	}
	
	public void addCustomer(Customer c) {
		tempOrder.setCustomer(c);
	}
	
	/*
	 * New order
	 * Customer
	 * LOOP
	 * Add Product
	 * 		check stock
	 * 		if enough	
	 * 			add Orderline to Order
	 * 			update reserved in stock
	 *#/LOOP
	 * Apply discount
	 * Delivery date
	 * total amount
	 * confirm
	 * Set Status 
	 * save order
	 * 
	 * #Start Transaction#
	 * 
	 * save order to db
	 * get orderID
	 * save orderlines to db with orderID
	 * 
	 * Commit || Rollback
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	

	
	public boolean saveOrder(Order o) {
		//OrderDB.saveOrder() #TODO
		return false;
		
	}

}
