package ua.nure.kolodiazhny.SummaryTask4.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.Product;
import ua.nure.kolodiazhny.SummaryTask4.exception.DBException;

/**
 * Data Access Object pattern implementation for products table.
 *
 * @author B.kolodiazhny
 *
 */
public interface ProductDAO {

	/**
	 * Finds product in a database by its name.
	 *
	 * @param name
	 *            of a product to find.
	 * @return Product object.
	 * @throws DBException
	 */
	public Product findProductByName(String name) throws DBException;

	/**
	 * Finds product in a database by its id.
	 *
	 * @param id
	 *            of a product to find.
	 * @return Product object.
	 * @throws DBException
	 */
	public Product findProductById(int id) throws DBException;

	/**
	 * Finds a product in a database by its category id.
	 *
	 * @param categoryId
	 *            of a product to find.
	 * @return Product object.
	 * @throws DBException
	 */
	public Product findProductByCategoryId(int categoryId) throws DBException;

	/**
	 * Finds all the products in a database.
	 *
	 * @return List of Product objects.
	 * @throws DBException
	 */
	public List<Product> getAllProducts() throws DBException;

	/**
	 * Finds all products of a certain category in a database.
	 *
	 * @param categoryId
	 *            id of a category of products.
	 * @return List of Product objects.
	 * @throws DBException
	 */
	public List<Product> getAllProductsByCategoryId(int categoryId) throws DBException;

	/**
	 * Parses all Product fields from database to Product object.
	 *
	 * @param rs
	 *            ResultSet of values from database.
	 * @return Product object.
	 * @throws SQLException
	 */
	public Product parseProduct(ResultSet rs) throws SQLException;

	/**
	 * Adds a product to a database.
	 *
	 * @param product
	 *            to be added to a database.
	 * @return <b>true</b> if product has been successfully inserted in database
	 *         or <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean addProduct(Product product) throws DBException;

	/**
	 * Deletes a product from a database.
	 *
	 * @param id
	 *            of a product to delete.
	 * @return <b>true</b> if product has been successfully deleted from
	 *         database or <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean deleteProduct(int productId) throws DBException;

	/**
	 * Updates a product.
	 *
	 * @param product
	 *            to update.
	 * @return <b>true</b> if product has been successfully updated or
	 *         <b>false</b> if not.
	 * @throws DBException
	 */
	public boolean updateProduct(Product product) throws DBException;

}
