package ua.nure.kolodiazhny.SummaryTask4.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Utility class for common database operations.
 *
 * @author B.kolodiazhny
 *
 */
public class DBUtil {

	/**
	 * JNDI connections pool data source.
	 */
	private static DataSource ds;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(DBUtil.class);

	/**
	 * Full Class Name of Mysql implementation of DAOFactory.
	 */
	public static final String MYSQL_DAO_FACTORY_FCN = "ua.nure.kolodiazhny.SummaryTask4.db.dao.mysql.MysqlDAOFactory";

	/**
	 * Creates (or takes from a pool) Data Base connection.
	 *
	 * @return Connection object.
	 * @throws DBException
	 */
	public static Connection getConnection() throws DBException {
		initDatasource();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			LOG.trace("Connection ==> " + connection);
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
		}
		return connection;
	}

	/**
	 * Initializes a data source object.
	 *
	 * @throws DBException
	 */
	private static void initDatasource() throws DBException {
		if (ds == null) {
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context) initialContext.lookup("java:/comp/env");
				ds = (DataSource) envContext.lookup("jdbc/eshop");
				LOG.trace(Messages.SUCCESS_DATA_SOURCE_OBTAINED + ds);
			} catch (NamingException e) {
				LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE);
				throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
			}
		}
	}

	/**
	 * Closes a connection.
	 *
	 * @param con
	 *            to be closed.
	 */
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
				LOG.trace(Messages.SUCCESS_CONNECTION_CLOSED);
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, e);
			}
		}
	}

	/**
	 * Closes a Statement object.
	 *
	 * @param stmt
	 *            Statement object to be closed.
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				LOG.trace(Messages.SUCCESS_STATEMENT_CLOSED);
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, e);
			}
		}
	}

	/**
	 * Closes a ResultSet object.
	 *
	 * @param rs
	 *            ResultSet object to be closed.
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				LOG.trace(Messages.SUCCESS_RESULTSET_CLOSED);
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
			}
		}
	}

	/**
	 * Closes multiple resources.
	 *
	 * @param con
	 *            to be closed.
	 * @param stmt
	 *            Statement object to be closed.
	 * @param rs
	 *            ResultSet object to be closed.
	 */
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		close(con);
		close(stmt);
		close(rs);
	}

	/**
	 * Rolling back the Connection object.
	 *
	 * @param con
	 *            Connection object.
	 */
	public static void rollBack(Connection con) {
		if (con != null) {
			try {
				con.rollback();
				LOG.trace(Messages.SUCCESS_CONNECTION_ROLLED_BACK);
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANNOT_ROLL_BACK_CONNECTION, e);
			}
		}
	}
}
