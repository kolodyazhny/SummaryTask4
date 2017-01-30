package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Displays adding a product to a database operation.
 *
 * @author B.kolodiazhny
 *
 */
public class ViewAddProductCommand extends Command {

	private static final long serialVersionUID = -986896759938106514L;

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		CommandResult result = new CommandResult(Path.COMMAND_PROCESS_PRODUCTS);//Path.PAGE_SHOP);
		result.setTransitionForward();
		return result;
	}

}
