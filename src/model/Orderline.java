package model;

public class Orderline {
	private Product product;
	private int qty;
	
	public Orderline(Product p, int qty) {
		this.product = p;
		this.qty = qty;
	}
	
	public void setProduct(Product p) {
		this.product = p;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setQuantity(int qty) {
		this.qty = qty;
	}
	
	public int getQty() {
		return qty;
	}

}
