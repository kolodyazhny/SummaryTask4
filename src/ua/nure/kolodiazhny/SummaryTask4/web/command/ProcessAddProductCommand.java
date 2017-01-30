package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.CategoryDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Category;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Product;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Processes addition of a product to a database.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessAddProductCommand extends Command {

	private static final long serialVersionUID = -8887058620020602459L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ProcessAddProductCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_ADMIN);
		result.setTransitionForward();
		request.setAttribute("action", "addProduct");

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		String name = request.getParameter("name");
		String priceAsString = request.getParameter("price");
		String stockAsString = request.getParameter("stock");
		String dateAsString = request.getParameter("date");
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		String imageSource = request.getParameter("image");
		String description = request.getParameter("description");
		String categoryName = request.getParameter("category");

		LOG.info(Messages.TRACE_REQUES_PARAMETER + name);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + priceAsString);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + stockAsString);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + dateAsString);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + size);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + color);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + imageSource);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + description);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + categoryName);

		if (isParamsEmpty(name, priceAsString) || isParamsEmpty(stockAsString, dateAsString)
				|| isParamEmpty(categoryName)) {
			request.setAttribute("message", Messages.WARNING_EMPTY_FIELDS);
			return result;
		}

		CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
		Category category = categoryDAO.findCategoryByName(categoryName);
		if (category == null) {
			request.setAttribute("message", Messages.WARNING_NO_SUCH_CATEGORY);
			return result;
		}

		ProductDAO productDAO = daoFactory.getProductDAO();
		Product product = productDAO.findProductByName(name);
		if (product != null) {
			request.setAttribute("message", Messages.WARNING_PRODUCT_ALTEADY_EXISTS);
			return result;
		}

		{
			product = new Product();
			product.setName(name);
			product.setPrice(Integer.valueOf(priceAsString));
			product.setStock(Integer.valueOf(stockAsString));
			product.setManufactoryDate(Date.valueOf(dateAsString));
			product.setSize(size);
			product.setColor(color);
			product.setImageSource(imageSource);
			product.setDescription(description);
			product.setCategoryId(category.getId());
		}

		LOG.debug("Current product object" + product);
		productDAO.addProduct(product);

		product = productDAO.findProductByName(name);

		request.removeAttribute("action");
		result.setDestinationURL(Path.COMMAND_VIEW_ADD_PRODUCT);
		result.setTransitionRedirect();
		return result;
	}

}
