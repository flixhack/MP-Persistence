package model;

public class BusinessCustomer extends Customer {
    private int cvrNo;

   
    public BusinessCustomer() {
        super();
    }

    
    public BusinessCustomer(String name, String address, int zipcode, String city, int phoneNo, int customerNo, int cvrNo) {
        super(name, address, zipcode, city, phoneNo, customerNo);
        this.cvrNo = cvrNo;
    }

    public int getCvrNo() {
        return cvrNo;
    }

    public void setCvrNo(int cvrNo) {
        this.cvrNo = cvrNo;
    }
}
