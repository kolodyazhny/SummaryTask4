package ua.nure.kolodiazhny.SummaryTask4.db.dao.mysql;

import ua.nure.kolodiazhny.SummaryTask4.db.dao.CategoryDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderItemDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.RoleDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.AdminOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Implementation of DAOFactory for MySQL DataBase.
 *
 * @author B.kolodiazhny
 *
 */
public class MysqlDAOFactory extends DAOFactory {

	public MysqlDAOFactory() throws DBException {
		// No operations
	}

	@Override
	public RoleDAO getRoleDAO() throws DBException {
		return new MysqlRoleDao();
	}

	@Override
	public UserDAO getUserDAO() throws DBException {
		return new MysqlUserDAO();
	}

	@Override
	public CategoryDAO getCategoryDAO() throws DBException {
		return new MysqlCategoryDao();
	}

	@Override
	public ProductDAO getProductDAO() throws DBException {
		return new MysqlProductDAO();
	}

	@Override
	public OrderItemDAO getOrderItemDAO() throws DBException {
		return new MysqlOrderItemDAO();
	}

	@Override
	public OrderDAO getOrderDAO() throws DBException {
		return new MysqlOrderDAO();
	}

	@Override
	public AdminOrderBeanDAO getAdminOrderBeanDAO() throws DBException {
		return new MysqlAdminOrderBeanDAO();
	}

	@Override
	public UserOrderBeanDAO getUserOrderBeanDAO() throws DBException {
		return new MysqlUserOrderBeanDAO();
	}

}
