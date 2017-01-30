package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation for users table.
 *
 * @author B.kolodiazhny
 *
 */
public interface UserDAO {

	/**
	 * Finds user in a database by its id.
	 *
	 * @param id
	 *            of a user to find.
	 * @return User object.
	 * @throws DBException
	 */
	public User findUserById(int id) throws DBException;

	/**
	 * Finds user in a database by its login.
	 *
	 * @param login
	 *            of a user to find.
	 * @return User object.
	 * @throws DBException
	 */
	public User findUserByLogin(String login) throws DBException;

	/**
	 * Parses all User fields from database to User object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return User object.
	 * @throws SQLException
	 */
	public User parseUser(ResultSet rs) throws SQLException;

	/**
	 * Inserts a User into database.
	 *
	 * @param user
	 *            to be inserted.
	 * @return <b>true</b> if user has been successfully inserted in database or
	 *         <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean addUser(User user) throws DBException;

	/**
	 * Updates a user in database.
	 *
	 * @param user
	 *            to update.
	 * @return <b>true</b> if user has been successfully updated or <b>false</b>
	 *         if not.
	 * @throws DBException
	 */
	public boolean updateUser(User user) throws DBException;

	/**
	 * Finds all users in a database.
	 *
	 * @return List of User objects.
	 * @throws DBException
	 */
	public List<User> getAllUsers() throws DBException;

}
