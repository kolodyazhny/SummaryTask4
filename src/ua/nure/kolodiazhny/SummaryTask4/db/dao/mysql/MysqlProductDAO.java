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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Product;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Implementation of the ProductDAO for a Mysql database.
 *
 * @author B.kolodiazhny
 *
 */
public class MysqlProductDAO implements ProductDAO {

	/**
	 * Apache Log4j logger
	 */
	private final static Logger LOG = Logger.getLogger(MysqlProductDAO.class);

	public MysqlProductDAO() {
		LOG.trace(Messages.TRACE_DAO_OBJECT_CREATED + getClass().getSimpleName());
	}

	@Override
	public Product findProductByName(String name) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_PRODUCT_BY_NAME);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = parseProduct(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_FIND_PRODUCT_BY_NAME, e);
			throw new DBException(Messages.ERR_CANOT_FIND_PRODUCT_BY_NAME, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return product;
	}

	@Override
	public Product findProductById(int id) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_PRODUCT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = parseProduct(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_FIND_PRODUCT_BY_ID, e);
			throw new DBException(Messages.ERR_CANOT_FIND_PRODUCT_BY_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return product;
	}

	@Override
	public Product findProductByCategoryId(int categoryId) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_PRODUCT_BY_CATEGORY_ID);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = parseProduct(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_FIND_PRODUCT_BY_CATEGORY_ID, e);
			throw new DBException(Messages.ERR_CANOT_FIND_PRODUCT_BY_CATEGORY_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() throws DBException {
		List<Product> productsList = new ArrayList<Product>();
		Connection con = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_PRODUCTS);
			while (rs.next()) {
				Product product = parseProduct(rs);
				productsList.add(product);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_OBTAIN_PRODUCTS, e);
			throw new DBException(Messages.ERR_CANOT_OBTAIN_PRODUCTS, e);
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return productsList;
	}

	@Override
	public List<Product> getAllProductsByCategoryId(int categoryId) throws DBException {
		List<Product> productsList = new ArrayList<Product>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_ALL_PRODUCTS_BY_CATEGORY_ID);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = parseProduct(rs);
				productsList.add(product);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_OBTAIN_PRODUCTS, e);
			throw new DBException(Messages.ERR_CANOT_OBTAIN_PRODUCTS, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return productsList;
	}

	@Override
	public Product parseProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt(Fields.ENTITY_ID));
		product.setName(rs.getString(Fields.PRODUCT_NAME));
		product.setPrice(rs.getInt(Fields.PRODUCT_PRICE));
		product.setStock(rs.getInt(Fields.PRODUCT_STOCK));
		product.setManufactoryDate(rs.getDate(Fields.PRODUCT_MANUFACTORING_DATE));
		product.setSize(rs.getString(Fields.PRODUCT_SIZE));
		product.setColor(rs.getString(Fields.PRODUCT_COLOR));
		product.setImageSource(rs.getString(Fields.PRODUCT_IMAGE_SOURCE));
		product.setDescription(rs.getString(Fields.PRODUCT_DESCRIPTION));
		product.setCategoryId(rs.getInt(Fields.PRODUCT_CATEGOEY_ID));
		return product;
	}

	@Override
	public boolean addProduct(Product product) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int counter = 1;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_ADD_PRODUCT);
			pstmt.setString(counter++, product.getName());
			pstmt.setInt(counter++, product.getPrice());
			pstmt.setInt(counter++, product.getStock());
			pstmt.setDate(counter++, product.getManufactoryDate());
			pstmt.setString(counter++, product.getSize());
			pstmt.setString(counter++, product.getColor());
			pstmt.setString(counter++, product.getImageSource());
			pstmt.setString(counter++, product.getDescription());
			pstmt.setInt(counter++, product.getCategoryId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANOT_ADD_PRODUCT, e);
			throw new DBException(Messages.ERR_CANOT_ADD_PRODUCT, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}
		return result;
	}

	@Override
	public boolean deleteProduct(int productId) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_DELETE_PRODUCT);
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_PRODUCT, e);
			throw new DBException(Messages.ERR_CANNOT_DELETE_PRODUCT, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}
		return result;
	}

	@Override
	public boolean updateProduct(Product product) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		int counter = 1;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_PRODUCT);
			pstmt.setString(counter++, product.getName());
			pstmt.setInt(counter++, product.getPrice());
			pstmt.setInt(counter++, product.getStock());
			pstmt.setDate(counter++, product.getManufactoryDate());
			pstmt.setString(counter++, product.getSize());
			pstmt.setString(counter++, product.getColor());
			pstmt.setString(counter++, product.getImageSource());
			pstmt.setString(counter++, product.getDescription());
			pstmt.setInt(counter++, product.getCategoryId());
			pstmt.setInt(counter++, product.getId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_PRODUCT, e);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_PRODUCT, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}

		return result;
	}

}
