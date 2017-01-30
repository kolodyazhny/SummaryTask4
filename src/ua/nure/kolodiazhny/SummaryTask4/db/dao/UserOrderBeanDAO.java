package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public interface UserOrderBeanDAO {

	/**
	 * Parses all UserOrderBean fields from a database tables users and orders
	 * to UserOrderBean object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return UserOrderBean object.
	 * @throws SQLException
	 */
	public UserOrderBean parseUserOrderBean(ResultSet rs) throws SQLException;

	/**
	 * Creates UserOrderBean for all orders in a database.
	 *
	 * @return List of UserOrderBean objects.
	 * @throws DBException
	 */
	public List<UserOrderBean> getAllUserOrderBeans(int orderId) throws DBException;

}
