package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * Command that process and displays to a user all products from a database.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessProductsCommand extends Command {

	private static final long serialVersionUID = -4896128391609799460L;

	/**
	 * Apache Log4j logger
	 */
	private static Logger LOG = Logger.getLogger(ProcessProductsCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_SHOP);
		if (request.getParameter("source") != null && request.getParameter("source").equals("ADMIN")) {
			result.setDestinationURL(Path.PAGE_ADMIN + "?action=listProducts");
		}
		result.setTransitionForward();

		HttpSession session = request.getSession();
		LOG.trace(Messages.TRACE_CURRENT_SESSION + session);

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		String currentCategory = request.getParameter("categoryName");
		LOG.trace(Messages.TRACE_REQUES_PARAMETER + currentCategory);
		String sortMethod = request.getParameter("sortMethod");
		LOG.trace(Messages.TRACE_REQUES_PARAMETER + sortMethod);

		CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
		List<Category> categoriesList = categoryDAO.getAllCategories();
		session.setAttribute("categoriesList", categoriesList);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + categoriesList);

		ProductDAO productDAO = daoFactory.getProductDAO();

		List<Product> productsList;

		if (!isParamEmpty(currentCategory)) {
			Category category = categoryDAO.findCategoryByName(currentCategory);
			productsList = productDAO.getAllProductsByCategoryId(category.getId());
		} else {
			productsList = productDAO.getAllProducts();
		}

		if (!isParamEmpty(sortMethod)) {
			switch (sortMethod) {
			case "name":
				Collections.sort(productsList, Comparator.comparing(Product::getName));
				break;
			case "reverseName":
				Collections.sort(productsList, Comparator.comparing(Product::getName).reversed());
				break;
			case "price":
				Collections.sort(productsList, Comparator.comparing(Product::getPrice));
				break;
			case "reversePrice":
				Collections.sort(productsList, Comparator.comparing(Product::getPrice).reversed());
				break;
			case "date":
				Collections.sort(productsList, Comparator.comparing(Product::getManufactoryDate).reversed());
				break;
			case "reverseDate":
				Collections.sort(productsList, Comparator.comparing(Product::getManufactoryDate));
				break;
			}
		}

		request.setAttribute("currentCategory", currentCategory);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_REQUEST + currentCategory);
		request.setAttribute("productsList", productsList);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_REQUEST + productsList);

		return result;
	}

}
