package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderItemDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.OrderItem;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Product;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Modifies fields of a product.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessUserOfficeCommand extends Command {

	private static final long serialVersionUID = 3048180175380530176L;

	/**
	 * Apache Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(ProcessUserOfficeCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_USER_OFFICE);
		result.setTransitionForward();

		HttpSession session = request.getSession();
		LOG.trace(Messages.TRACE_CURRENT_SESSION + session);

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		User user = (User) session.getAttribute("user");
		if (user == null) {
			LOG.error("asdfasdfasdfas");// ERR
		}

		OrderDAO orderDAO = daoFactory.getOrderDAO();
		UserDAO userDAO = daoFactory.getUserDAO();

		int usersCurrentOrderId = 0;
		if (!(user.getCurrentOrderId() == null)) {
			usersCurrentOrderId = user.getCurrentOrderId();
		}

		Order cart = null;
		if (usersCurrentOrderId == 0) {
			cart = new Order();
			cart.setUserId(user.getId());
			orderDAO.addOrder(cart);
			cart = orderDAO.getOrderByUserStatusAndId(user.getId(), OrderStatus.PROCESSING);
			user.setCurrentOrderId(cart.getId());
			userDAO.updateUser(user);
		} else {
			cart = orderDAO.getOrderById(usersCurrentOrderId);
		}

		OrderItemDAO itemDAO = daoFactory.getOrderItemDAO();
		List<OrderItem> items = itemDAO.getAllOrderItemsByOrder(cart.getId());

		ProductDAO productDAO = daoFactory.getProductDAO();
		List<Product> products = new ArrayList<Product>();

		for (OrderItem item : items) {
			products.add(productDAO.findProductById(item.getId()));
		}

		session.setAttribute("items", items);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + items);
		session.setAttribute("products", products);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + products);
		session.setAttribute("cart", cart);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cart);

		return result;
	}

}
