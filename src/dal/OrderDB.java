package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderDB implements OrderDAO {
	
	
	private static final String PS_INSERT = "INSERT INTO Order VALUES (?, ?, ?, ?, ?, ?)";
	private static final String PS_SELECT_BY_ID = "SELECT * FROM Order WHERE CustomerNo = ?";
	private static final String PS_SELECT = "SELECT * FROM Order";
	private static final String PS_UPDATE = "UPDATE Order SET Date = ?, Items = ?, Amount = ?, DeliveryStatus = ?, DeliveryDate = ? WHERE Customer = ?";
	private static final String PS_DELETE = "DELETE FROM Order WHERE CustomerNo = ?";
	
	private PreparedStatement insertPS;
	private PreparedStatement selectByIdPS;
	private PreparedStatement selectPS;
	private PreparedStatement updatePS;
	private PreparedStatement deletePS;
	
	public OrderDB() throws DataAccessException {
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
	
	/*
	 * #TODO
	 * #Start Transaction#
	 * 
	 * save order to db
	 * get orderID
	 * save orderlines to db with orderID //Order i db indholder ikke products/item/Orderlines, orderlines gemmes i et table for sig, med orderNO // For at rebuild object, select * from orderline where OrderNO = ? | byg orderlines og add til order.
	 * 
	 * Commit || Rollback
	 */
	
	@Override
	public Order insert(Order order) throws DataAccessException {
		int orderNo = 0;
			try {
				insertPS.setString(1, order.getDate().toString());
				//insertPS.setArray(2, order.getItems());
				insertPS.setDouble(3, order.getAmount());
				insertPS.setString(4, order.getDeliveryStatus());
				updatePS.setDate(5, java.sql.Date.valueOf(order.getDeliveryDate()));
				insertPS.setInt(6, order.getCustomer().getCustomerNo());
				
				int rowsAffected = insertPS.executeUpdate();
				if (rowsAffected == 0) {
					throw new DataAccessException(DBMessages.COULD_NOT_INSERT, null);
				}
				
				// Get the generated customer number
				try (ResultSet generatedKeys = insertPS.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						orderNo = generatedKeys.getInt(1);
						order.setOrderNo(orderNo);
					} else {
						throw new DataAccessException(DBMessages.COULD_NOT_GET_GENERATED_KEY, null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
			}
			return order;
	}
	
	@Override
	public Order update(Order order) throws DataAccessException {
		try {
			updatePS.setString(1, order.getDate().toString());
			//updatePS.setArray(2, order.getItems());
			updatePS.setDouble(3, order.getAmount());
			updatePS.setString(4, order.getDeliveryStatus());
			updatePS.setDate(5, java.sql.Date.valueOf(order.getDate()));
			updatePS.setInt(6, order.getCustomer().getCustomerNo());
			
			int rowsAffected = updatePS.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataAccessException(DBMessages.COULD_NOT_UPDATE, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_UPDATE, e);
		}
		return order;
	}
	
	@Override
	public void delete(int id) throws DataAccessException {
		try {
			deletePS.setInt(1, id);
			int rowsAffected = deletePS.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataAccessException("No order found with ID: " + id, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	@Override
	public Order findById(int id) throws DataAccessException {
		Order order = null;
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
				order = new Order();
				order.setOrderNo(resultSet.getInt("OrderNo"));
				//order.setItems(resultSet.getArray("Items"));
				order.setAmount(resultSet.getDouble("Amount"));
				order.setDeliveryStatus(resultSet.getString("DeliveryStatus"));
				order.setDateToCurrent(); // Placeholder, should convert from SQL date
				order.setCustomer(null); // Placeholder, should fetch customer by ID
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return order;
	}
	
	@Override
	public List<Order> getAll() throws DataAccessException {
	    List<Order> listOrder = new ArrayList<>();
	    try {
	        ResultSet rs = selectPS.executeQuery();
	        while (rs.next()) {
	            Order order = buildObject(rs);
	            listOrder.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
	    }
	    return listOrder;
	}


	private Order buildObject(ResultSet rs) {
		Order order = null;
		try {
			order = new Order();
			order.setOrderNo(rs.getInt("OrderNo"));
			//order.setItems(rs.getArray("Items"));
			order.setAmount(rs.getDouble("Amount"));
			order.setDeliveryStatus(rs.getString("DeliveryStatus"));
			order.setDateToCurrent(); // Placeholder, should convert from SQL date
			order.setCustomer(null); // Placeholder, should fetch customer by ID
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	

}
