package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Displays login operation. Part of PRG pattern - secures from forms resubmit.
 *
 * @author B.kolodiazhny
 *
 */
public class ViewLoginCommand extends Command {

	private static final long serialVersionUID = -6086867590194386112L;

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		CommandResult result = new CommandResult();
		result.setDestinationURL(Path.PAGE_MAIN);
		result.setTransitionForward();
		return result;
	}

}
