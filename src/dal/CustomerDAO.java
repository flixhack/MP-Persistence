package dal;

import java.util.List;

import model.Customer;

public interface CustomerDAO {
Customer insert(Customer customer) throws DataAccessException;
	
	Customer findById(int id) throws DataAccessException;
	
	Customer update(Customer customer) throws DataAccessException;

	Customer findPrivateByEmail(String email) throws DataAccessException;

	Customer findBusinessByCvr(int cvr) throws DataAccessException;

	List<Customer> getAllCustomers() throws DataAccessException;

	void delete(int id) throws DataAccessException;
	
}
