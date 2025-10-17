package dal;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Orderline;

public interface OrderDAO {

	List<Order> getAll() throws DataAccessException;

	Order findById(int id) throws DataAccessException;

	void delete(int id) throws DataAccessException;

	Order update(Order order) throws DataAccessException;

	Order insert(Order order) throws DataAccessException;
	
	void insertOrderLines(Order order) throws DataAccessException;

}
