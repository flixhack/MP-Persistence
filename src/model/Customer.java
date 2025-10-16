package model;

public class Customer {
	private String name;
	private String address;
	private int zipcode;
	private String city;
	private int phoneNo;
	private int customerNo;
	private int CustomerID;
	
	public Customer(int CustomerID) {
		this.CustomerID = CustomerID;
		
	}
	public Customer() {
		
	}
	
	public Customer(String name, String address, int zipcode, String city, int phoneNo, int customerNo) {
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.phoneNo = phoneNo;
		this.customerNo = customerNo;
	}
	
	public Customer(int CustomerID, String name, String address, int zipcode, String city, int phoneNo, int customerNo) {
		this(name, address, zipcode, city, phoneNo, customerNo);
		this.setCustomerID(CustomerID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

}
