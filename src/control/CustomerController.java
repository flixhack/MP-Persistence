package control;

import java.util.List;

import dal.CustomerDB;
import dal.CustomerDAO;
import dal.DataAccessException;
import model.Customer;
import model.PrivateCustomer;

public class CustomerController {
	private CustomerDAO customerDAO;
	public CustomerController() throws DataAccessException {
		customerDAO = new CustomerDB();
	}
	public Customer createCustomer(String name, String email) throws DataAccessException {
		// Step 1: Check if already exists
		Customer c = customerDAO.findPrivateByEmail(email);
		if (c == null) {
			// Step 2: If not, create and insert new PrivateCustomer
			PrivateCustomer pc = new PrivateCustomer();
			pc.setName(name);
			pc.setEmail(email);
			pc.setAddress("");
			pc.setZipcode(0);
			pc.setCity("");
			pc.setPhoneNo(0);
			// save to db
			c = customerDAO.insert(pc);
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