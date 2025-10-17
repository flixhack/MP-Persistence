package control;

import java.util.List;

import dal.CustomerDB;
import dal.CustomerDAO;
import dal.DataAccessException;
import model.BusinessCustomer;
import model.Customer;
import model.PrivateCustomer;

public class CustomerController {
	private CustomerDAO customerDAO;
	public CustomerController() throws DataAccessException {
		customerDAO = new CustomerDB();
	}
	public Customer createPrivateCustomer(String name, String address, int zipcode,String city, int phoneNo, String email)
            throws DataAccessException {
           Customer c = customerDAO.findPrivateByEmail(email);
            if (c == null) {
             c = new PrivateCustomer(name, address, zipcode, city, phoneNo, 0, email);
             c = customerDAO.insert(c); // handles DB insertion and sets customerNo
           }
            return c;
            }
	public Customer createBusinessCustomer(String name, String address, int zipcode, String city, int phoneNo, int cvr)
            throws DataAccessException {
             Customer c = customerDAO.findBusinessByCvr(cvr);
              if (c == null) {
             c = new BusinessCustomer(name, address, zipcode, city, phoneNo, 0, cvr);
             c = customerDAO.insert(c);
             }
             return c;
             }
	
	public Customer findByEmail(String email) throws DataAccessException {
		return customerDAO.findPrivateByEmail(email);
	}

	public List<Customer> getAll() throws DataAccessException {
		return customerDAO.getAllCustomers();
	}
    
}