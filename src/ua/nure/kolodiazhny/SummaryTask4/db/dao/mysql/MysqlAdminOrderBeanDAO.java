package ua.nure.kolodiazhny.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.DBCommands;
import ua.nure.kolodiazhny.SummaryTask4.constant.Fields;
import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.db.bean.AdminOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.AdminOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public class MysqlAdminOrderBeanDAO implements AdminOrderBeanDAO {

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(MysqlAdminOrderBeanDAO.class);

	@Override
	public List<AdminOrderBean> getAllAdminOrderBeans() throws DBException {
		List<AdminOrderBean> beans = new ArrayList<AdminOrderBean>();
		Connection con = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_ADMIN_ORDER_BEANS);
			while (rs.next()) {
				beans.add(parseAdminOrderBean(rs));
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_OBRAIN_ADMIN_ORDER_BEANS, e);
			throw new DBException(Messages.ERR_CANNOT_OBRAIN_ADMIN_ORDER_BEANS, e);
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return beans;
	}

	@Override
	public AdminOrderBean parseAdminOrderBean(ResultSet rs) throws SQLException {
		AdminOrderBean bean = new AdminOrderBean();
		bean.setOrderId(rs.getInt(Fields.ADMIN_ORDER_BEAN_ORDER_ID));
		bean.setUserLogin(rs.getString(Fields.ADMIN_ORDER_BEAN_USER_LOGIN));
		bean.setUserFName(rs.getString(Fields.ADMIN_ORDER_BEAN_USER_FIRST_NAME));
		bean.setUserLName(rs.getString(Fields.ADMIN_ORDER_BEAN_USER_LAST_NAME));
		bean.setUserEmail(rs.getString(Fields.ADMIN_ORDER_BEAN_USER_EMAIL));
		bean.setOrderTotalPrice(rs.getInt(Fields.ADMIN_ORDER_BEAN_ORDER_PRICE));
		bean.setOderStatus(OrderStatus.valueOf(rs.getString(Fields.ADMIN_ORDER_BEAN_ORDER_STATUS)));
		bean.setOrderPaymentInfo(rs.getString(Fields.ADMIN_ORDER_BEAN_ORDER_PAMENT_INFO));
		return bean;
	}

}
