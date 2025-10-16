package dal;

import java.util.List;

import model.Product;

public interface ProductDAO {

	List<Product> getAllProducts() throws DataAccessException;

	void delete(int id) throws DataAccessException;

	Product update(Product product) throws DataAccessException;

	Product findById(int id) throws DataAccessException;

	Product insert(Product product) throws DataAccessException;

}
