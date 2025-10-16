package model;

public class SalePrice extends Price {
	private String saleId;
	
	public SalePrice() {
		
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

}
