package ua.nure.kolodiazhny.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.DBCommands;
import ua.nure.kolodiazhny.SummaryTask4.constant.Fields;
import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public class MysqlUserOrderBeanDAO implements UserOrderBeanDAO {

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(MysqlUserOrderBeanDAO.class);

	@Override
	public UserOrderBean parseUserOrderBean(ResultSet rs) throws SQLException {
		UserOrderBean bean = new UserOrderBean();
		bean.setProductName(rs.getString(Fields.USER_ORDER_BEAN_PRODUCT_NAME));
		bean.setProductImage(rs.getString(Fields.USER_ORDER_BEAN_PRODUCT_IMAGE));
		bean.setOrderItemProdctsCount(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_ITEM_PRODUCTS_COUNT));
		bean.setOrderItemPrice(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_ITEM_PRICE));
		bean.setOrderStatus(OrderStatus.valueOf(rs.getString(Fields.USER_ORDER_BEAN_ORDER_STATUS)));
		bean.setOrderPaymentInfo(rs.getString(Fields.USER_ORDER_BEAN_ORDER_PAYMENT_INFO));
		return bean;
	}

	@Override
	public List<UserOrderBean> getAllUserOrderBeans(int orderId) throws DBException {
		List<UserOrderBean> beans = new ArrayList<UserOrderBean>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_ALL_USER_ORDER_BEANS);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				beans.add(parseUserOrderBean(rs));
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_OBRAIN_USER_ORDER_BEANS, e);
			throw new DBException(Messages.ERR_CANNOT_OBRAIN_USER_ORDER_BEANS, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return beans;
	}

}
