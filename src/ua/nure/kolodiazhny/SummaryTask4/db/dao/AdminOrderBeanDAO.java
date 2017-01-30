package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.bean.AdminOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation for virtual table that contains
 * information from users and orders real tables.
 *
 * @author B.kolodiazhny
 *
 */
public interface AdminOrderBeanDAO {

	/**
	 * Creates AdminOrderBean for all orders in a database.
	 *
	 * @return List of AdminOrderBean objects.
	 * @throws DBException
	 */
	public List<AdminOrderBean> getAllAdminOrderBeans() throws DBException;

	/**
	 * Parses all AdminOrderBean fields from a database tables users and orders
	 * to AdminOrderBean object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return AdminOrderBean object.
	 * @throws SQLException
	 */
	public AdminOrderBean parseAdminOrderBean(ResultSet rs) throws SQLException;

}
