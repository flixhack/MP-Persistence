package model;

public class Product {
	private int productNo;
	private String name;
	private int minStock;
	private int reservedQty;
	private double price;
	public Product(int productNo, String name, int minStock, int reservedQty, double price) {
		this.productNo = productNo;
		this.minStock = minStock;
		this.name = name;
		this.reservedQty = reservedQty;
		this.price = price;
	}
	
	public Product() {
		
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getReservedQty() {
		return reservedQty;
	}

	public void setReservedQty(int reservedQty) {
		this.reservedQty = reservedQty;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
