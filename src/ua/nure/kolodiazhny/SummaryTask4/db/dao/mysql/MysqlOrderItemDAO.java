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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderItemDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.OrderItem;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public class MysqlOrderItemDAO implements OrderItemDAO {

	private static final Logger LOG = Logger.getLogger(MysqlOrderItemDAO.class);

	public MysqlOrderItemDAO() {
		LOG.trace(Messages.TRACE_DAO_OBJECT_CREATED + getClass().getSimpleName());
	}

	@Override
	public List<OrderItem> getAllOrderItemsByOrder(int orderId) throws DBException {
		List<OrderItem> items = new ArrayList<OrderItem>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_ALL_ORDER_ITEMS_BY_ORDER_ID);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderItem item = parseOrderItem(rs);
				items.add(item);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ORDER_ITEMS, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDER_ITEMS, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return items;
	}

	@Override
	public OrderItem parseOrderItem(ResultSet rs) throws SQLException {
		OrderItem item = new OrderItem();
		item.setId(rs.getInt(Fields.ENTITY_ID));
		item.setOrderId(rs.getInt(Fields.ORDER_ITEM_ORDER_ID));
		item.setPrice(rs.getInt(Fields.ORDER_ITEM_PRICE));
		item.setProductId(rs.getInt(Fields.ORDER_ITEM_PRODUCT_ID));
		item.setProductsCount(rs.getInt(Fields.ORDER_ITEM_PRODUCTS_COUNT));
		return item;
	}

	@Override
	public boolean addOrderItem(OrderItem item) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int counter = 1;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_ADD_ORDER_ITEM);
			pstmt.setInt(counter++, item.getProductId());
			pstmt.setInt(counter++, item.getProductsCount());
			pstmt.setInt(counter++, item.getPrice());
			pstmt.setInt(counter++, item.getOrderId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_ADD_ORDER_ITEM, e);
			throw new DBException(Messages.ERR_CANNOT_ADD_ORDER_ITEM, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}
		return result;
	}

	@Override
	public boolean updateOrderItem(OrderItem item) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		int counter = 1;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_ORDER_ITEM);
			pstmt.setInt(counter++, item.getProductsCount());
			pstmt.setInt(counter++, item.getPrice());
			pstmt.setInt(counter++, item.getId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_ITEM, e);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ITEM, e);
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return result;
	}

}
