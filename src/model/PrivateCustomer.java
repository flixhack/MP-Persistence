package model;

public class PrivateCustomer extends Customer {
	private String Email;
	
	public PrivateCustomer() {
		super();
	}
	
	public PrivateCustomer(String name, String address, int zipcode, String city, int phoneNo, int customerNo, String Email) {
		super(name, address, zipcode, city, phoneNo, customerNo);
		this.Email = Email;
		
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

}
