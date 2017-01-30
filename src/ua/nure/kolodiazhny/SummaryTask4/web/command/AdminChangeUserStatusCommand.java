package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.UserDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Role;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.User;
import ua.nure.kolodiazhny.SummaryTask4.db.status.UserStatus;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Administrators action to change users status.
 *
 * @author B.kolodiazhny
 *
 */
public class AdminChangeUserStatusCommand extends Command {

	private static final long serialVersionUID = 7087419008864710610L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminChangeUserStatusCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_ADMIN_ACTIONS + "&action=listUsers");
		result.setTransitionRedirect();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		UserDAO userDAO = daoFactory.getUserDAO();

		String userIdAsString = request.getParameter("userId");
		if (isParamEmpty(userIdAsString)) {
			// ERR
		}

		User user = userDAO.findUserById(Integer.valueOf(userIdAsString));

		if (!Role.getRole(user).equals(Role.ADMIN)) {
			// request.setAttribute("message", "You don't have a permission to
			// change admins status!");
			// } else {
			if (user.getStatus().equals(UserStatus.OPEN)) {
				user.setStatus(UserStatus.BLOCKED);
			} else if (user.getStatus().equals(UserStatus.BLOCKED)) {
				user.setStatus(UserStatus.OPEN);
			}
		}
		userDAO.updateUser(user);

		return result;
	}

}
