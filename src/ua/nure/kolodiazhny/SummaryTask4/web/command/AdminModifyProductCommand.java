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
 * @author B.kolodiazhny
 *
 */
public class AdminModifyProductCommand extends Command {

	private static final long serialVersionUID = 4237435205803454909L;

	private static final Logger LOG = Logger.getLogger(AdminModifyProductCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_ADMIN_ACTIONS + "&action=listProducts");
		result.setTransitionRedirect();

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

		String productIdAsString = request.getParameter("productId");
		LOG.info(Messages.TRACE_REQUES_PARAMETER + productIdAsString);


		if (isParamEmpty(productIdAsString)) {
			// ERR
		}

		int productId = Integer.valueOf(productIdAsString);

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		Product product = productDAO.findProductById(productId);

		if (!isParamEmpty(name)) {
			product.setName(name);
		}
		if (!isParamEmpty(priceAsString)) {
			product.setPrice(Integer.valueOf(priceAsString));
		}
		if (!isParamEmpty(stockAsString)) {
			product.setStock(Integer.valueOf(stockAsString));
		}
		if (!isParamEmpty(dateAsString)) {
			product.setManufactoryDate(Date.valueOf(dateAsString));
		}
		if (!isParamEmpty(size)) {
			product.setSize(size);
		}
		if (!isParamEmpty(color)) {
			product.setColor(color);
		}
		if (!isParamEmpty(imageSource)) {
			product.setImageSource(imageSource);
		}
		if (!isParamEmpty(description)) {
			product.setDescription(description);
		}
		if (!isParamEmpty(categoryName)) {
			CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
			Category category = categoryDAO.findCategoryByName(categoryName);
			if(category != null){
				product.setCategoryId(category.getId());
			}
		}

		productDAO.updateProduct(product);

		return result;
	}

}
