package model;

public class PurchasePrice extends Price {
	private String supplierId;
	
	public PurchasePrice() {
		
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}
