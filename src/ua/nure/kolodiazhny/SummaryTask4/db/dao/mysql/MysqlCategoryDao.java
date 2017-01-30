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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.CategoryDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Category;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public class MysqlCategoryDao implements CategoryDAO {

	/**
	 * Apache Log4j logger
	 */
	private final static Logger LOG = Logger.getLogger(MysqlCategoryDao.class);

	public MysqlCategoryDao() {
		LOG.trace(Messages.TRACE_DAO_OBJECT_CREATED + getClass().getSimpleName());
	}

	@Override
	public Category findCategoryById(int id) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category category = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_CATEGORY_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				category = parseCategory(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_FIND_CATEGORY_BY_ID, e);
			throw new DBException(Messages.ERR_CANOT_FIND_CATEGORY_BY_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return category;
	}

	@Override
	public Category findCategoryByName(String name) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category category = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_CATEGORY_BY_NAME);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				category = parseCategory(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_FIND_CATEGORY_BY_NAME, e);
			throw new DBException(Messages.ERR_CANOT_FIND_CATEGORY_BY_NAME, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return category;
	}

	@Override
	public List<Category> getAllCategories() throws DBException {
		List<Category> categoriesList = new ArrayList<Category>();
		Connection con = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_CATEGORIES);
			while (rs.next()) {
				Category category = parseCategory(rs);
				categoriesList.add(category);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANOT_OBTAIN_CATEGORIES, e);
			throw new DBException(Messages.ERR_CANOT_OBTAIN_CATEGORIES, e);
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return categoriesList;
	}

	@Override
	public Category parseCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt(Fields.ENTITY_ID));
		category.setName(rs.getString(Fields.CATEGORY_NAME));
		return category;
	}

	@Override
	public boolean addCategory(Category category) throws DBException {
		Connection con = DBUtil.getConnection();
		;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_ADD_CATEGORY);
			pstmt.setString(1, category.getName());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANOT_ADD_CATEGORY, e);
			throw new DBException(Messages.ERR_CANOT_ADD_CATEGORY, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}
		return result;
	}

}
