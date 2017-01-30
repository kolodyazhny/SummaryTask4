package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Deletes a product from a database. Actually just changes product status on
 * "DELETED" and hides it from all users.
 *
 * @author B.kolodiazhny
 *
 */
public class AdminDeleteProductCommand extends Command {

	private static final long serialVersionUID = -6016537830163995095L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminDeleteProductCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_ADMIN_ACTIONS + "&action=listProducts");
		// CommandResult result = new
		// CommandResult(Path.COMMAND_PROCESS_PRODUCTS + "&source=ADMIN");
		result.setTransitionRedirect();

		String productIdAsString = request.getParameter("productId");
		if (productIdAsString == null) {
			// ERR
		}
		int productId = Integer.valueOf(productIdAsString);

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		productDAO.deleteProduct(productId);

		return result;
	}

}
