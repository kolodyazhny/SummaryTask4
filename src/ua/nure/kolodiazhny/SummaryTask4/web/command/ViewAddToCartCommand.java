package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * @author B.kolodiazhny
 *
 */
public class ViewAddToCartCommand extends Command {

	private static final long serialVersionUID = -2248547566823308880L;

	private static final Logger LOG = Logger.getLogger(ViewAddToCartCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		CommandResult result = new CommandResult(Path.COMMAND_PROCESS_PRODUCTS);//Path.PAGE_SHOP);
		LOG.debug("SDSDSDSDSDSDSDADASDASDASDASDASDASDASD!!!!!!!!!!!! " + request.getParameter("page"));
		result.setTransitionForward();
		return result;
	}

}
