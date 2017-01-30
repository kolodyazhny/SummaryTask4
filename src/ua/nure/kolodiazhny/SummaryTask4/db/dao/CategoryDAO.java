package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.Category;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation for categories table.
 *
 * @author B.kolodiazhny
 *
 */
public interface CategoryDAO {

	/**
	 * Finds category in a database by its id.
	 *
	 * @param id
	 *            of a category to find.
	 * @return Category object.
	 * @throws DBException
	 */
	public Category findCategoryById(int id) throws DBException;

	/**
	 * Finds category in a database by its name.
	 *
	 * @param name
	 *            of a category to find.
	 * @return Category object.
	 * @throws DBException
	 */
	public Category findCategoryByName(String name) throws DBException;

	/**
	 * Finds all categories in a database.
	 *
	 * @return list of Category objects.
	 * @throws DBException
	 */
	public List<Category> getAllCategories() throws DBException;

	/**
	 * Parses all Category fields from database to Category object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return Category object.
	 * @throws SQLException
	 */
	public Category parseCategory(ResultSet rs) throws SQLException;

	/**
	 * Adds a category to a database.
	 *
	 * @param category
	 *            to be added to a database.
	 * @return <b>true</b> if category has been successfully inserted in
	 *         database or <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean addCategory(Category category) throws DBException;

}
