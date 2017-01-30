package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * @author B.kolodiazhny
 *
 */
public class ViewPlaceOrderCommand extends Command {

	private static final long serialVersionUID = -6912344080069910214L;

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		CommandResult result = new CommandResult(Path.PAGE_MAIN);
		result.setTransitionForward();
		return result;
	}

}
