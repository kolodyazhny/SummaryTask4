package ua.nure.kolodiazhny.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.DBCommands;
import ua.nure.kolodiazhny.SummaryTask4.constant.Fields;
import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public class MysqlOrderDAO implements OrderDAO {

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(MysqlOrderDAO.class);

	public MysqlOrderDAO() {
		LOG.trace(Messages.TRACE_DAO_OBJECT_CREATED + getClass().getSimpleName());
	}

	@Override
	public Order getOrderById(int id) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order order = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_ORDER_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order = parseOrder(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_FIND_ORDER_BY_ID, e);
			throw new DBException(Messages.ERR_CANOT_FIND_ORDER_BY_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return order;
	}

	@Override
	public Order parseOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt(Fields.ENTITY_ID));
		order.setUserId(rs.getInt(Fields.ORDER_USER_ID));
		order.setTotalPrice(rs.getInt(Fields.ORDER_TOTAL_PRICE));
		order.setStatus(OrderStatus.valueOf(rs.getString(Fields.ORDER_STATUS)));
		order.setPaymentInfo(rs.getString(Fields.ORDER_PAYMENT_INFO));
		return order;
	}

	@Override
	public boolean addOrder(Order order) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_ADD_ORDER);
			pstmt.setInt(1, order.getUserId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_INSERT_ORDER, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_ORDER, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}
		return result;
	}

	@Override
	public boolean updateOrder(Order order) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int counter = 1;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_ORDER);
			pstmt.setString(counter++, String.valueOf(order.getStatus()));
			pstmt.setInt(counter++, order.getUserId());
			pstmt.setInt(counter++, order.getTotalPrice());
			pstmt.setString(counter++, order.getPaymentInfo());
			pstmt.setInt(counter, order.getId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_ORDER, e);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER, e);
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return result;
	}

	@Override
	public Order getOrderByUserStatusAndId(int userId, OrderStatus status) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order order = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_ORDER_BY_STATUS_AND_USER_ID);
			pstmt.setInt(1, userId);
			pstmt.setString(2, String.valueOf(status));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order = parseOrder(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_FIND_ORDER_BY_STATUS_AND_SUER_ID, e);
			throw new DBException(Messages.ERR_CANNOT_FIND_ORDER_BY_STATUS_AND_SUER_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return order;
	}

	@Override
	public List<Order> getAllOrders() throws DBException {
		Connection con = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_ORDERS);
			while (rs.next()) {
				Order order = parseOrder(rs);
				orders.add(order);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_OBRAIN_ORDERS, e);
			throw new DBException(Messages.ERR_CANNOT_OBRAIN_ORDERS, e);
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return orders;
	}

	@Override
	public List<Order> getAllOrdersByUserId(int userId) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_ALL_ORDERS_BY_USER_ID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = parseOrder(rs);
				orders.add(order);
			}
			con.commit();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_FIND_ALL_ORDERS_BY_USER_ID, e);
			throw new DBException(Messages.ERR_CANNOT_FIND_ALL_ORDERS_BY_USER_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return orders;
	}

}
