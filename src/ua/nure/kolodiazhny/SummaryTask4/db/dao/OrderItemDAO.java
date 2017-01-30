package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.OrderItem;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation for order_items table.
 *
 * @author B.kolodiazhny
 *
 */
public interface OrderItemDAO {

	/**
	 * Finds all OrderItem from certain Order.
	 *
	 * @param orderId
	 *            - id of order whose OrderItems to find.
	 * @return List of OrderItem objects.
	 * @throws DBException
	 */
	public List<OrderItem> getAllOrderItemsByOrder(int orderId) throws DBException;

	/**
	 * Inserts order item into database.
	 *
	 * @param order
	 *            to be inserted.
	 * @return <b>true</b> if order has been successfully inserted in database
	 *         or <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean addOrderItem(OrderItem item) throws DBException;

	/**
	 * Parses all OrderItem fields from database to OrderItem object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return OrderItem object.
	 * @throws SQLException
	 */
	public OrderItem parseOrderItem(ResultSet rs) throws SQLException;

	/**
	 * Updates an orderItem.
	 *
	 * @param orderItem
	 *            to update.
	 * @return <b>true</b> if orderItem has been successfully updated or
	 *         <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean updateOrderItem(OrderItem item) throws DBException;
}
