package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * @author B.kolodiazhny
 *
 */
public class CommonChoseActionCommand extends Command {

	private static final long serialVersionUID = -1815312539893566718L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(CommonChoseActionCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult();
		result.setTransitionForward();

		String action = request.getParameter("action");
		LOG.trace(Messages.TRACE_REQUES_PARAMETER + action);

		switch (action) {
		case "placeOrder":
			result.setDestinationURL(Path.PAGE_USER_OFFICE);
			break;
		default:
			break;
		}

		request.setAttribute("action", action);

		return result;
	}

}
