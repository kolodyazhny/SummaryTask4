package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.bean.AdminOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.AdminOrderBeanDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Finds and adds to a request all orders in a system.
 *
 * @author B.kolodiazhny
 *
 */
public class AdminDisplayOrdersCommand extends Command {

	private static final long serialVersionUID = 2408493421462892275L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminDisplayOrdersCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_ADMIN + "?action=listOrders");
		result.setTransitionForward();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		AdminOrderBeanDAO adminOrderBeanDAO = daoFactory.getAdminOrderBeanDAO();

		List<AdminOrderBean> adminOrderBeans = adminOrderBeanDAO.getAllAdminOrderBeans();

		HttpSession session = request.getSession();
		session.setAttribute("adminOrderBeans", adminOrderBeans);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + adminOrderBeans);

		return result;
	}

}
