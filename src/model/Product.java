package model;

public class Product {
	private int productNo;
	private String name;
	private int minStock;
	private int reservedQty;
	
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

}
