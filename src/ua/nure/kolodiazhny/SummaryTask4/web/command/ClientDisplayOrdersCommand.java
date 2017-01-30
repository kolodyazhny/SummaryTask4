package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Finds and adds to a request all users orders by.
 *
 * @author B.kolodiazhny
 *
 */
public class ClientDisplayOrdersCommand extends Command {

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ClientDisplayOrdersCommand.class);

	private static final long serialVersionUID = 1368996801789272842L;

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			LOG.error("USER IS NULL" + user);
			// ERR
		}

		CommandResult result = new CommandResult(Path.PAGE_USER_OFFICE);
		result.setTransitionForward();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		UserOrderBeanDAO beanDAO = daoFactory.getUserOrderBeanDAO();

		List<Order> orders = orderDAO.getAllOrdersByUserId(user.getId());
		List<UserOrderBean> beans = new ArrayList<UserOrderBean>();
//		List<List<UserOrderBean>> allOrderBeans = new ArrayList<List<UserOrderBean>>();
		Map<Order, List<UserOrderBean>> allOrderBeans = new HashMap<Order, List<UserOrderBean>>();

		for (Order o : orders) {
			beans = beanDAO.getAllUserOrderBeans(o.getId());
			if (!beans.isEmpty()) {
//				allOrderBeans.add(beans);
				allOrderBeans.put(o, beans);
			}
		}

		request.setAttribute("allOrders", allOrderBeans);
		LOG.debug(Messages.TRACE_ATRIBUTE_ADDED_TO_REQUEST + allOrderBeans);
		request.setAttribute("action", "listAllOrders");
		return result;
	}

}
