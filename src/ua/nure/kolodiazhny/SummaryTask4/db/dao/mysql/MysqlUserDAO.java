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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.status.UserStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * @author B.kolodiazhny
 *
 */
public class MysqlUserDAO implements UserDAO {

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(MysqlUserDAO.class);

	public MysqlUserDAO() {
		LOG.trace(Messages.TRACE_DAO_OBJECT_CREATED + getClass().getSimpleName());
	}

	@Override
	public User findUserByLogin(String login) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = parseUser(rs);
			}
			con.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return user;
	}

	@Override
	public User findUserById(int id) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_FIND_USER_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = parseUser(rs);
			}
			con.commit();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int counter = 1;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_USER);
			pstmt.setString(counter++, user.getLogin());
			pstmt.setString(counter++, user.getPassword());
			pstmt.setString(counter++, user.getEmail());
			pstmt.setString(counter++, user.getFirstName());
			pstmt.setString(counter++, user.getLastName());
			pstmt.setString(counter++, user.getPhone());
			pstmt.setString(counter++, user.getAddress());
			pstmt.setString(counter++, user.getGender());
			pstmt.setString(counter++, String.valueOf(user.getStatus()));
			pstmt.setObject(counter++, user.getCurrentOrderId());
//			pstmt.setInt(counter++, user.getCurrentOrderId());
			pstmt.setInt(counter++, user.getId());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			DBUtil.rollBack(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, e);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, e);
		}
		return result;
	}

	@Override
	public boolean addUser(User user) throws DBException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int counter = 1;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBCommands.SQL_ADD_USER);
			pstmt.setString(counter++, user.getLogin());
			pstmt.setString(counter++, user.getPassword());
			pstmt.setString(counter++, user.getEmail());
			pstmt.setString(counter++, user.getFirstName());
			pstmt.setString(counter++, user.getLastName());
			pstmt.setString(counter++, user.getPhone());
			pstmt.setString(counter++, user.getAddress());
			pstmt.setString(counter++, user.getGender());
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_CREATE_USER, e);
			throw new DBException(Messages.ERR_CANNOT_CREATE_USER, e);
		} finally {
			DBUtil.close(con);
			DBUtil.close(pstmt);
		}
		return result;

	}

	@Override
	public User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setPhone(rs.getString(Fields.USER_PHONE));
		user.setAddress(rs.getString(Fields.USER_ADDRESS));
		user.setGender(rs.getString(Fields.USER_GENDER));
		user.setStatus(UserStatus.valueOf(rs.getString(Fields.USER_STATUS)));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		user.setCurrentOrderId((Integer) rs.getObject(Fields.USER_CURRENT_ORDER_ID));
//		user.setCurrentOrderId(rs.getInt(Fields.USER_CURRENT_ORDER_ID));
		return user;
	}

	@Override
	public List<User> getAllUsers() throws DBException {
		List<User> users = new ArrayList<User>();
		Connection con = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_USERS);
			while (rs.next()) {
				User user = parseUser(rs);
				users.add(user);
			}
			con.commit();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBRAIN_USERS, e);
			throw new DBException(Messages.ERR_CANNOT_OBRAIN_USERS, e);
		} finally {
			DBUtil.close(con, stmt, rs);
		}

		return users;
	}

}
