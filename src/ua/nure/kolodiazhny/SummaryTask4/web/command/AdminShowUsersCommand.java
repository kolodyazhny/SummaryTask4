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
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Shows all users.
 *
 * @author B.kolodiazhny
 *
 */
public class AdminShowUsersCommand extends Command {

	private static final long serialVersionUID = -4375259771270495143L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminShowUsersCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_ADMIN + "?action=listUsers");
		result.setTransitionForward();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		UserDAO userDAO = daoFactory.getUserDAO();

		List<User> users = new ArrayList<User>();
		users = userDAO.getAllUsers();

		HttpSession session = request.getSession();

		session.setAttribute("users", users);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + users);

		return result;
	}

}
