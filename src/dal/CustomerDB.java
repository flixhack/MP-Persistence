package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BusinessCustomer;
import model.Customer;
import model.PrivateCustomer;

public class CustomerDB implements CustomerDAO {
	
	private static final String PS_INSERT = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?)";
	private static final String PS_SELECT_BY_ID = "SELECT * FROM Customer WHERE CustomerNo = ?";
	private static final String PS_SELECT = "SELECT * FROM Customer";
	private static final String PS_UPDATE = "UPDATE Customer SET Name = ?, Address = ?, Zipcode = ?, City = ?, PhoneNo = ? WHERE CustomerNo = ?";
	private static final String PS_DELETE = "DELETE FROM Customer WHERE CustomerNo = ?";
	private static final String PS_SELECT_PRIVATE = "SELECT * FROM PrivateCustomer WHERE cvr = ?";
	private static final String PS_SELECT_BUSINESS = "SELECT * FROM BusinessCustomer WHERE email = ?";
	
	private PreparedStatement insertPS;
	private PreparedStatement selectByIdPS;
	private PreparedStatement selectPS;
	private PreparedStatement updatePS;
	private PreparedStatement deletePS;
	private PreparedStatement selectPrivatePS;
	private PreparedStatement selectBusinessPS;
	
	public CustomerDB() throws DataAccessException {
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
			selectPrivatePS = connection.prepareStatement(PS_SELECT_PRIVATE);
			selectBusinessPS = connection.prepareStatement(PS_SELECT_BUSINESS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		
	}
	@Override
	public Customer insert(Customer customer) throws DataAccessException {
		int customerNo = 0;
			try {
				insertPS.setString(1, customer.getName());
				insertPS.setString(2, customer.getAddress());
				insertPS.setInt(3, customer.getZipcode());
				insertPS.setString(4, customer.getCity());
				insertPS.setInt(5, customer.getPhoneNo());
				if (customer instanceof PrivateCustomer) {
					PrivateCustomer pc = (PrivateCustomer) customer;
					insertPS.setString(6, pc.getEmail());
				} else if (customer instanceof BusinessCustomer) {
					BusinessCustomer bc = (BusinessCustomer) customer;
					insertPS.setString(6, Integer.toString(bc.getCvrNo()));
				} else {
					throw new DataAccessException("Unknown customer type", null);
				}
				
				insertPS.executeUpdate();
				// get hold of the generated key
				ResultSet keyRS = insertPS.getGeneratedKeys();
				if (keyRS.next()) {
					customerNo = keyRS.getInt(1);
				}
			} catch (SQLException e) {
				throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
			}
			customer.setCustomerNo(customerNo);
			return customer;
	}
	
	@Override
	public Customer findById(int id) throws DataAccessException {
		Customer customer = null;
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
				String address = resultSet.getString("Address");
				int zipcode = resultSet.getInt("Zipcode");
				String city = resultSet.getString("City");
				int phoneNo = resultSet.getInt("PhoneNo");
				String emailOrCvr = resultSet.getString(6); // 6th column is either email or cvr
				
				// Determine if it's a PrivateCustomer or BusinessCustomer
				if (emailOrCvr.contains("@")) { // Simple check for email
					customer = new PrivateCustomer();
					((PrivateCustomer) customer).setEmail(emailOrCvr);
				} else { // Assume it's a CVR number
					customer = new BusinessCustomer();
					((BusinessCustomer) customer).setCvrNo(Integer.parseInt(emailOrCvr));
				}
				
				customer.setCustomerNo(id);
				customer.setName(name);
				customer.setAddress(address);
				customer.setZipcode(zipcode);
				customer.setCity(city);
				customer.setPhoneNo(phoneNo);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return customer;
	}
	
	@Override
	public Customer update(Customer customer) throws DataAccessException {
		try {
			updatePS.setString(1, customer.getName());
			updatePS.setString(2, customer.getAddress());
			updatePS.setInt(3, customer.getZipcode());
			updatePS.setString(4, customer.getCity());
			updatePS.setInt(5, customer.getPhoneNo());
			updatePS.setInt(6, customer.getCustomerNo());
			
			int rowsAffected = updatePS.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataAccessException("No customer found with ID: " + customer.getCustomerNo(), null);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		return customer;
	}
	
	@Override
	public void delete(int id) throws DataAccessException {
		try {
			deletePS.setInt(1, id);
			int rowsAffected = deletePS.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataAccessException("No customer found with ID: " + id, null);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	@Override
	public Customer findPrivateByEmail(String email) throws DataAccessException {
		Customer customer = null;
		ResultSet resultSet;
		try {
			selectPrivatePS.setString(1, email);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		try {
			resultSet = selectPrivatePS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		
		try {
			if (resultSet.next()) {
				int customerNo = resultSet.getInt("CustomerNo");
				String name = resultSet.getString("Name");
				String address = resultSet.getString("Address");
				int zipcode = resultSet.getInt("Zipcode");
				String city = resultSet.getString("City");
				int phoneNo = resultSet.getInt("PhoneNo");
				
				customer = new PrivateCustomer();
				((PrivateCustomer) customer).setEmail(email);
				customer.setCustomerNo(customerNo);
				customer.setName(name);
				customer.setAddress(address);
				customer.setZipcode(zipcode);
				customer.setCity(city);
				customer.setPhoneNo(phoneNo);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return customer;
	}
	
	@Override
	public Customer findBusinessByCvr(int cvr) throws DataAccessException {
		Customer customer = null;
		ResultSet resultSet;
		try {
			selectBusinessPS.setInt(1, cvr);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
		try {
			resultSet = selectBusinessPS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		
		try {
			if (resultSet.next()) {
				int customerNo = resultSet.getInt("CustomerNo");
				String name = resultSet.getString("Name");
				String address = resultSet.getString("Address");
				int zipcode = resultSet.getInt("Zipcode");
				String city = resultSet.getString("City");
				int phoneNo = resultSet.getInt("PhoneNo");
				
				customer = new BusinessCustomer();
				((BusinessCustomer) customer).setCvrNo(cvr);
				customer.setCustomerNo(customerNo);
				customer.setName(name);
				customer.setAddress(address);
				customer.setZipcode(zipcode);
				customer.setCity(city);
				customer.setPhoneNo(phoneNo);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return customer;
	}
	
	@Override
	public List<Customer> getAllCustomers() throws DataAccessException {
		List<Customer> listCustomer = new ArrayList<>();
        try {
            ResultSet rs = selectPS.executeQuery();
            while (rs.next()) {
                Customer customer = buildObject(rs);
                listCustomer.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCustomer;
	}
	
	private Customer buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		String address = rs.getString(3);
		int zipcode = rs.getInt(4);
		String city = rs.getString(5);
		int phoneNo = rs.getInt(6);
		int customerNo = rs.getInt(7);
		return new Customer(id, name, address, zipcode, city, phoneNo, customerNo);
	}
	}
