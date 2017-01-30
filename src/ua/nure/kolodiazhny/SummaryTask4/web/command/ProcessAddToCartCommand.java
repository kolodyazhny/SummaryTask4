package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Command that processes product addition to a cart.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessAddToCartCommand extends Command {

	private static final long serialVersionUID = -1128310099366384175L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ProcessAddToCartCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_ADD_TO_CART_AUTHORIZED);
		result.setTransitionForward();

		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			result.setDestinationURL(Path.COMMAND_ADD_TO_CART_NOT_AUTHORIZED);
		}

		return result;
	}

}
