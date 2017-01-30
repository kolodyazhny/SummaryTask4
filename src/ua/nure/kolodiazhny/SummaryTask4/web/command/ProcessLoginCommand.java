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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderItemDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.OrderItem;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Role;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.status.UserStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Command that processes login operation.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessLoginCommand extends Command {

	private static final long serialVersionUID = 1145768516376082548L;

	/**
	 * Apache Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(ProcessLoginCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_LOGIN);
		result.setTransitionForward();

		HttpSession session = request.getSession();
		LOG.trace(Messages.TRACE_CURRENT_SESSION + session);

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		OrderDAO orderDAO = daoFactory.getOrderDAO();
		OrderItemDAO orderItemDAO = daoFactory.getOrderItemDAO();
		UserDAO userDAO = daoFactory.getUserDAO();

		String login = request.getParameter("login");
		LOG.trace(Messages.TRACE_REQUES_PARAMETER + login);
		String password = request.getParameter("password");
		LOG.trace(Messages.TRACE_REQUES_PARAMETER + password);

		if (isParamsEmpty(login, password)) {
			request.setAttribute("message", Messages.WARNING_EMPTY_FIELDS);
			return result;
		}

		User user = userDAO.findUserByLogin(login);
		LOG.trace(Messages.TRACE_FOUNDED_IN_DATA_BASE + user);

		if (user == null || !generateHash(password).equals(user.getPassword())) {
			request.setAttribute("message", Messages.WARNING_NO_SUCH_USER);
			return result;
		}
		if (user.getStatus().equals(UserStatus.BLOCKED)) {
			request.setAttribute("message", Messages.WARNING_USER_BLOCKED);
			return result;
		}

		Order cart = new Order();

		if (user.getCurrentOrderId() != null) {
			cart = orderDAO.getOrderById(user.getCurrentOrderId());
			LOG.trace(Messages.TRACE_FOUNDED_IN_DATA_BASE + cart);
		}
		if (session.getAttribute("items") != null) {

			if (user.getCurrentOrderId() == null) {
				cart.setUserId(user.getId());
				orderDAO.addOrder(cart);
				cart = orderDAO.getOrderByUserStatusAndId(user.getId(), OrderStatus.PROCESSING);
				user.setCurrentOrderId(cart.getId());
				userDAO.updateUser(user);
			}

			List<OrderItem> items = new ArrayList<OrderItem>();
			items = orderItemDAO.getAllOrderItemsByOrder(cart.getId());

			List<OrderItem> itemsFromSession = (List<OrderItem>) session.getAttribute("items");
			for (OrderItem ifs : itemsFromSession) {
				cart.setTotalPrice(cart.getTotalPrice() + ifs.getPrice());
				ifs.setOrderId(user.getCurrentOrderId());
				/**
				 * Checks if order_item already exists. If true - updates
				 * current order_item, if false - adds a new one to a database.
				 *
				 */
				boolean flag = true;
				for (OrderItem i : items) {
					if (i.getProductId() == ifs.getProductId()) {
						i.setPrice(i.getPrice() + ifs.getPrice());
						i.setProductsCount(i.getProductsCount() + ifs.getProductsCount());
						orderItemDAO.updateOrderItem(i);
						flag = false;
						break;
					}
				}
				if (flag) {
					orderItemDAO.addOrderItem(ifs);
				}
			}
			orderDAO.updateOrder(cart);
		}

		List<UserOrderBean> cartBeans = new ArrayList<UserOrderBean>();
		if (cart.getId() != 0) {
			UserOrderBeanDAO userOrderBeanDAO = daoFactory.getUserOrderBeanDAO();
			cartBeans = userOrderBeanDAO.getAllUserOrderBeans(cart.getId());
		}

		session.setAttribute("cart", cart);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cart);

		session.setAttribute("cartBeans", cartBeans);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cartBeans);

		Role role = Role.getRole(user);
		LOG.trace(Messages.TRACE_USER_ROLE + role);

		session.setAttribute("user", user);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + user);
		session.setAttribute("role", role);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + role);

		result.setDestinationURL(Path.COMMAND_VIEW_LOGIN);
		result.setTransitionRedirect();
		return result;
	}

}
