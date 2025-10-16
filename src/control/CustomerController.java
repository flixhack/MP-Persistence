package control;

import dal.CustomerDB;
import dal.CustomerDAO;
import dal.DataAccessException;

public class CustomerController {
	public CustomerController() throws DataAccessException {
		CustomerDAO customerDAO;
		customerDAO = new CustomerDB();
	}
}
