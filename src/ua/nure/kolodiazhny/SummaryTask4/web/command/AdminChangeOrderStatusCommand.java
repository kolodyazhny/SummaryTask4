package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Provides an administrators action to change an order status.
 *
 * @author B.kolodiazhny
 *
 */
public class AdminChangeOrderStatusCommand extends Command {

	private static final long serialVersionUID = -4488575475951458284L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminChangeOrderStatusCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_ADMIN_ACTIONS + "&action=listOrders");
		result.setTransitionRedirect();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		OrderDAO orderDAO = daoFactory.getOrderDAO();

		String orderIdAsString = request.getParameter("orderId");
		String orderStatusAsString = request.getParameter("staus");
		if (isParamsEmpty(orderIdAsString, orderStatusAsString)) {
			// throw new AppException();
		}
		int orderId = Integer.valueOf(orderIdAsString);

		Order order = orderDAO.getOrderById(orderId);
		if (order == null) {
			// ERR
		}
		order.setStatus(OrderStatus.valueOf(orderStatusAsString));
		orderDAO.updateOrder(order);

		return result;

	}

}
