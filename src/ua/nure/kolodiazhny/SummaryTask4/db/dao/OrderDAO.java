package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation for orders table.
 *
 * @author B.kolodiazhny
 *
 */
public interface OrderDAO {

	/**
	 * Finds order by id.
	 *
	 * @param id
	 *            of order to find;
	 * @return Order object.
	 * @throws DBException
	 */
	public abstract Order getOrderById(int id) throws DBException;

	/**
	 * Adds an order to a database.
	 *
	 * @param order
	 *            to be added to a database.
	 * @return <b>true</b> if order has been successfully inserted in database
	 *         or <b>false</b> if not.
	 * @throws DBException
	 */
	public abstract boolean addOrder(Order order) throws DBException;

	/**
	 * Finds all orders in a database.
	 *
	 * @return List of Order objects.
	 * @throws DBException
	 */
	public List<Order> getAllOrders() throws DBException;

	/**
	 * Finds all orders for a certain user in a database.
	 *
	 * @return List of Order objects.
	 * @throws DBException
	 */
	public List<Order> getAllOrdersByUserId(int userId) throws DBException;

	/**
	 * Updates an order.
	 *
	 * @param order
	 *            to update.
	 * @return <b>true</b> if order has been successfully updated or
	 *         <b>false</b> if not.
	 * @throws DBException
	 */
	public abstract boolean updateOrder(Order order) throws DBException;

	/**
	 * Finds an order by users id and status.
	 *
	 * @param userId
	 *            id of user whose order to find.
	 * @param status
	 *            of order to find.
	 * @return Order object.
	 * @throws DBException
	 */
	public abstract Order getOrderByUserStatusAndId(int userId, OrderStatus status) throws DBException;

	/**
	 * Parses all Order fields from database to Order object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return Order object.
	 * @throws SQLException
	 */
	public Order parseOrder(ResultSet rs) throws SQLException;
}
