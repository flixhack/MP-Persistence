package model;

import java.time.LocalDate;
import java.util.ArrayList;


public class Order {
	private int orderNo;
	private ArrayList<Orderline> items;
	private LocalDate date;
	private double amount;
	private int deliveryStatus;
	private LocalDate deliveryDate;
	private Customer customer;
	
	public Order() {
		
	}
	public boolean addOrderLine(Orderline o) {
		items.add(o);
		return true;
		
	}
	
	public ArrayList<Orderline> getItems() {
		return items;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setCustomer(Customer c) {
		this.customer = c;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setDateToCurrent() {
		this.date = LocalDate.now();
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setOrderNo(int OrderNo) {
		this.orderNo = orderNo;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	
	
	

}
