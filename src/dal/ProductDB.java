package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDB implements ProductDAO {
	private static final String PS_INSERT = "INSERT INTO Product VALUES (?, ?, ?, ?)";
	private static final String PS_SELECT_BY_ID = "SELECT * FROM Product WHERE productNo = ?";
	private static final String PS_SELECT = "SELECT * FROM Product";
	private static final String PS_UPDATE = "UPDATE Product SET name = ?, minStock = ?, reservedQty = ?, WHERE productNo = ?";
	private static final String PS_DELETE = "DELETE FROM Product WHERE productNo = ?";
	
	private PreparedStatement insertPS;
	private PreparedStatement selectByIdPS;
	private PreparedStatement selectPS;
	private PreparedStatement updatePS;
	private PreparedStatement deletePS;
	
	public ProductDB() throws DataAccessException {
		initPreparedStatement();
		
	}

	private void initPreparedStatement() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			insertPS = connection.prepareStatement(PS_INSERT);
			selectByIdPS = connection.prepareStatement(PS_SELECT_BY_ID);
			selectPS = connection.prepareStatement(PS_SELECT);
			updatePS = connection.prepareStatement(PS_UPDATE);
			deletePS = connection.prepareStatement(PS_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		
	}
	@Override
	public Product insert(Product product) throws DataAccessException {
		int productNo = 0;
			try {
				insertPS.setString(1, product.getName());
				insertPS.setInt(2, product.getMinStock());
				insertPS.setInt(3, product.getReservedQty());
				insertPS.setInt(4, product.getProductNo());
				insertPS.executeUpdate();
				ResultSet keyRS = insertPS.getGeneratedKeys();
				if (keyRS.next()) {
					productNo = keyRS.getInt(1);
				}
			} catch (SQLException e) {
				throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
			}
			product.setProductNo(productNo);
			return product;
	}
	
	@Override
	public Product findById(int id) throws DataAccessException {
		Product product = null;
		ResultSet resultSet;
		try {
			selectByIdPS.setInt(1, id);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		try {
			resultSet = selectByIdPS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		
		try {
			if (resultSet.next()) {
				String name = resultSet.getString("Name");
				int minStock = resultSet.getInt("MinStock");
				int reservedQty = resultSet.getInt("ReservedQty");
				int productNo = resultSet.getInt("ProductNo");
				
								
				product.setProductNo(productNo);
				product.setName(name);
				product.setMinStock(minStock);
				product.setReservedQty(reservedQty);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return product;
	}
	
	@Override
	public Product update(Product product) throws DataAccessException {
		try {
			updatePS.setString(1, product.getName());
			updatePS.setInt(2, product.getMinStock());
			updatePS.setInt(3, product.getReservedQty());
			updatePS.setInt(4, product.getProductNo());
			
			int rowsAffected = updatePS.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataAccessException("No product found with ID: " + product.getProductNo(), null);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		return product;
	}
	
	@Override
	public void delete(int id) throws DataAccessException {
		try {
			deletePS.setInt(1, id);
			int rowsAffected = deletePS.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataAccessException("No product found with ID: " + id, null);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	
	@Override
	public List<Product> getAllProducts() throws DataAccessException {
		List<Product> listProduct = new ArrayList<>();
        try {
            ResultSet rs = selectPS.executeQuery();
            while (rs.next()) {
                Product product = buildObject(rs);
                listProduct.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduct;
	}
	
	private Product buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString(1);
		int minStock = rs.getInt(2);
		int reservedQty = rs.getInt(3);
		int productNo = rs.getInt(4);
		return new Product(minStock,name, reservedQty, productNo);
	}
	}
