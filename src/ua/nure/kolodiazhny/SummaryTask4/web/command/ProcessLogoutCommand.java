package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Command that process logout operation.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessLogoutCommand extends Command {

	private static final long serialVersionUID = -819621880779448881L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ProcessLogoutCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.PAGE_MAIN);
		result.setTransitionForward();

		HttpSession session = request.getSession(false);
		LOG.trace(Messages.TRACE_CURRENT_SESSION + session);

		if (session != null) {
			session.invalidate();
		}

		return result;
	}

}
