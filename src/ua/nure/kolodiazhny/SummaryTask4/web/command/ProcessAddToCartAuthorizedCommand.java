package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.bean.UserOrderBean;
//import ua.nure.kolodiazhny.SummaryTask4.db.dao.CategoryDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderItemDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserOrderBeanDAO;
//import ua.nure.kolodiazhny.SummaryTask4.db.entity.Category;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.OrderItem;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Product;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Adds a product to a logged user's cart.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessAddToCartAuthorizedCommand extends Command {

	private static final long serialVersionUID = 5930914703710773119L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ProcessAddToCartAuthorizedCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_VIEW_ADD_TO_CART);
		result.setTransitionRedirect();

		HttpSession session = request.getSession();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		ProductDAO productDAO = daoFactory.getProductDAO();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		OrderItemDAO orderItemDAO = daoFactory.getOrderItemDAO();
		UserDAO userDAO = daoFactory.getUserDAO();
		UserOrderBeanDAO beanDAO = daoFactory.getUserOrderBeanDAO();

		String productIdAsString = request.getParameter("productId");
		String categoryName = request.getParameter("currentCategory");
		LOG.info(Messages.TRACE_REQUES_PARAMETER + productIdAsString);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + categoryName);

		if (!isParamEmpty(categoryName)) {
			result.setDestinationURL(Path.COMMAND_VIEW_ADD_TO_CART + "&categoryName=" + categoryName);
		}

		if (isParamEmpty(productIdAsString)) {
			LOG.debug("ERR id null");
			// ERR
		}

		int productId = Integer.valueOf(productIdAsString);
		Product product = productDAO.findProductById(productId);

		if (product == null) {
			LOG.debug("ERR product null");
			// ERR
		}

		if (product.getStock() < 1) {
			// CANT BUY ERR
		}

		Order cart = new Order();
		User user = (User) session.getAttribute("user");
		List<OrderItem> items = new ArrayList<OrderItem>();
		if (user.getCurrentOrderId() != null) {
			cart = orderDAO.getOrderById(user.getCurrentOrderId());
			items = orderItemDAO.getAllOrderItemsByOrder(cart.getId());
		} else {
			cart.setUserId(user.getId());
			orderDAO.addOrder(cart);
			cart = orderDAO.getOrderByUserStatusAndId(user.getId(), OrderStatus.PROCESSING);
			user.setCurrentOrderId(cart.getId());
			userDAO.updateUser(user);
		}

		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setProductId(productId);
		// ADD PRODUCTS COUNT?
		newOrderItem.setProductsCount(1);
		// * PRODUCTS COUNT!
		newOrderItem.setPrice(product.getPrice());
		newOrderItem.setOrderId(user.getCurrentOrderId());

		/**
		 * Checks if order_item already exists. If true - updates current
		 * order_item, if false - adds a new one to a database.
		 */
		boolean flag = true;
		for (OrderItem i : items) {
			if (i.getProductId() == newOrderItem.getProductId()) {
				i.setPrice(i.getPrice() + newOrderItem.getPrice());
				i.setProductsCount(i.getProductsCount() + newOrderItem.getProductsCount());
				orderItemDAO.updateOrderItem(i);
				flag = false;
				break;
			}
		}
		if (flag) {
			items.add(newOrderItem);
			orderItemDAO.addOrderItem(newOrderItem);
		}
		cart.setTotalPrice(cart.getTotalPrice() + newOrderItem.getPrice());
		orderDAO.updateOrder(cart);

		List<UserOrderBean> cartBeans = new ArrayList<UserOrderBean>();
		cartBeans = beanDAO.getAllUserOrderBeans(cart.getId());

		session.setAttribute("cartBeans", cartBeans);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cartBeans);
		session.setAttribute("cart", cart);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cart);

		return result;
	}

}
