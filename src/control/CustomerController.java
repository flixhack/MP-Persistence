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
	public Customer createPrivateCustomer(String name, String email) throws DataAccessException {
		// Step 1: Check if already exists
		Customer c = customerDAO.findPrivateByEmail(email);
		if (c == null) {
			// Step 2: If not, create and insert new PrivateCustomer
			c = new PrivateCustomer();
			c = CustomerDB.insert(c); //save to db
		}
		return c;
		
	}
	public Customer createBusinessCustomer(String name, int cvr) throws DataAccessException {
		// Step 1: Check if already exists
		Customer c = customerDAO.findBusinessByCvr(cvr);
		if (c == null) {
			c = new BusinessCustomer();
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