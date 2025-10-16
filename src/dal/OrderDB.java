package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDB implements OrderDAO {
	
	
	private static final String PS_INSERT = "INSERT INTO Order VALUES (?, ?, ?, ?, ?, ?)";
	private static final String PS_SELECT_BY_ID = "SELECT * FROM Order WHERE CustomerNo = ?";
	private static final String PS_SELECT = "SELECT * FROM Order";
	private static final String PS_UPDATE = "UPDATE Order SET OrderNo = ?, items = ?, date = ?, amount = ?, deliveryStatus = ? WHERE deliveryDate = ?";
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

}
