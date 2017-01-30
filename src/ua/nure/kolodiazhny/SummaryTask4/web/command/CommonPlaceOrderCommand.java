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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * @author B.kolodiazhny
 *
 */
public class CommonPlaceOrderCommand extends Command {

	private static final long serialVersionUID = -2891588948179025891L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(CommonPlaceOrderCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_VIEW_PLACE_ORDER);
		result.setTransitionRedirect();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// ERR
		}

		String cardNumber = request.getParameter("card");
		if (isParamEmpty(cardNumber)) {
			request.setAttribute("message", Messages.WARNING_EMPTY_FIELDS);
			result.setDestinationURL(Path.PAGE_USER_OFFICE + "&action=placeOrder");
			result.setTransitionForward();
			return result;
		}

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		UserDAO userDAO = daoFactory.getUserDAO();

		Order order = orderDAO.getOrderById(user.getCurrentOrderId());
		order.setPaymentInfo(cardNumber);
		order.setStatus(OrderStatus.REGISTERED);
		orderDAO.updateOrder(order);

		Order cart = new Order();
		cart.setUserId(user.getId());
		orderDAO.addOrder(cart);
		cart = orderDAO.getOrderByUserStatusAndId(user.getId(), OrderStatus.PROCESSING);
		user.setCurrentOrderId(cart.getId());
		userDAO.updateUser(user);

		List<UserOrderBean> cartBeans = new ArrayList<UserOrderBean>();

		session.setAttribute("cart", cart);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cart);

		session.setAttribute("cartBeans", cartBeans);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cartBeans);

		return result;
	}

}
