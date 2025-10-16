package control;

import dal.CustomerDB;
import dal.DataAccessException;

public class CustomerController {
	public CustomerController() throws DataAccessException {
		customerDao = new CustomerDB();
		
	}
}
