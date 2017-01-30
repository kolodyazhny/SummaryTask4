package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Command that selects an action to include it on an administrators page.
 *
 * @author B.kolodiazhny
 *
 */
public class AdminChoseActionCommand extends Command {

	private static final long serialVersionUID = -4165602525812132134L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminChoseActionCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult();
		result.setTransitionForward();

		String action = request.getParameter("action");
		LOG.trace(Messages.TRACE_REQUES_PARAMETER + action);

		switch (action) {
		case "addProduct":
			result.setDestinationURL(Path.PAGE_ADMIN);
			break;
		case "listUsers":
			result.setDestinationURL(Path.COMMAND_SHOW_USERS);
			break;
		case "listOrders":
			result.setDestinationURL(Path.COMMAND_ADMIN_DISPLAY_ORDERS);
			break;
		case "listProducts":
			result.setDestinationURL(Path.COMMAND_PROCESS_PRODUCTS + "&source=ADMIN");
			break;
		case "deleteProduct":
			result.setDestinationURL(Path.COMMAND_DELETE_PRODUCT);
			break;
		case "modifyProduct":
			result.setDestinationURL(Path.PAGE_ADMIN);
			break;
		default:
			// result.setDestinationURL(Path.ADMIN_PAGE);
			break;
		}

		request.setAttribute("action", action);
		return result;
	}

}
