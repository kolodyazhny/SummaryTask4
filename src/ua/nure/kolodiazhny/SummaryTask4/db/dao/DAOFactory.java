package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation main class.
 *
 * @author B.kolodiazhny
 *
 */
public abstract class DAOFactory {

	/**
	 * Apache Log4j Logger
	 */
	private static final Logger LOG = Logger.getLogger(DAOFactory.class);

	/**
	 * JNDI connections pool data source.
	 */
	protected DataSource ds;

	/**
	 * Holder for an instance of the DAOFactory.
	 */
	protected static DAOFactory instance;

	/**
	 * Full Class Name of a certain DAOFactory implementation.
	 */
	private static String daoFactoryFCN;

	/**
	 * Sets Full Class Name of a current implementation of the DAOFactory.
	 *
	 * @param daoFactoryFCN
	 *            Full Class Name of a current implementation of the DAOFactory
	 *            to set.
	 */
	public static void setDaoFactoryFCN(String daoFactoryFCN) {
		instance = null;
		DAOFactory.daoFactoryFCN = daoFactoryFCN;
	}

	/**
	 * Creates an instance of current DAOFactory implementation using its Full
	 * Class Name. Should set daoFactoryFCN before.
	 *
	 * @return Instance of a certain DAOFactory implementation.
	 * @throws DBException
	 */
	public static synchronized DAOFactory getInstance() throws DBException {
		if (instance == null) {
			Class<?> clazz = null;
			try {
				clazz = Class.forName(DAOFactory.daoFactoryFCN);
				instance = (DAOFactory) clazz.newInstance();
				LOG.debug(Messages.SUCCESS_DAO_FACTORY_INSTANCE_OBTAINED + instance);
			} catch (Exception e) {
				LOG.error(Messages.ERR_CANNOT_INSTANTIATE_DAO_FACTORY);
				throw new DBException(Messages.ERR_CANNOT_INSTANTIATE_DAO_FACTORY, e);
			}
		}
		return instance;
	}

	protected DAOFactory() throws DBException {
		// No operations
	}

	/**
	 * Creates RoleDAO object of a certain implementation of DAOFactory.
	 *
	 * @return RoleDao object.
	 * @throws DBException
	 */
	public abstract RoleDAO getRoleDAO() throws DBException;

	/**
	 * Creates UserDAO object of a certain implementation of DAOFactory.
	 *
	 * @return UserDao object.
	 * @throws DBException
	 */
	public abstract UserDAO getUserDAO() throws DBException;

	/**
	 * Creates CategoryDAO object of a certain implementation of DAOFactory.
	 *
	 * @return CategoryDao object.
	 * @throws DBException
	 */
	public abstract CategoryDAO getCategoryDAO() throws DBException;

	/**
	 * Creates ProductDAO object of a certain implementation of DAOFactory.
	 *
	 * @return ProductDao object.
	 * @throws DBException
	 */
	public abstract ProductDAO getProductDAO() throws DBException;

	/**
	 * Creates OrderItemDAO object of a certain implementation of DAOFactory.
	 *
	 * @return OrderItemDao object.
	 * @throws DBException
	 */
	public abstract OrderItemDAO getOrderItemDAO() throws DBException;

	/**
	 * Creates OrderDAO object of a certain implementation of DAOFactory.
	 *
	 * @return OrderDao object.
	 * @throws DBException
	 */
	public abstract OrderDAO getOrderDAO() throws DBException;

	/**
	 * Creates AdminOrderBeanDAO object of a certain implementation of
	 * DAOFactory.
	 *
	 * @return AdminOrderBeanDAO object.
	 * @throws DBException
	 */
	public abstract AdminOrderBeanDAO getAdminOrderBeanDAO() throws DBException;

	/**
	 * Creates UserOrderBeanDAO object of a certain implementation of
	 * DAOFactory.
	 *
	 * @return UserOrderBeanDAO object.
	 * @throws DBException
	 */
	public abstract UserOrderBeanDAO getUserOrderBeanDAO() throws DBException;

}
