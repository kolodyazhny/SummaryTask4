package ua.nure.kolodiazhny.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * @author B.kolodiazhny
 *
 */
public class CommonNavigatorCommand extends Command {

	private static final long serialVersionUID = 5894199981261265422L;

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		String page = request.getParameter("page");

		CommandResult result = new CommandResult();
		result.setTransitionForward();

		if (isParamEmpty(page)) {
			// ERR
		}

		switch (page) {
		case "main":
			result.setDestinationURL(Path.PAGE_MAIN);
			break;
		case "contacts":
			result.setDestinationURL(Path.PAGE_CONTACTS);
			break;
		case "about":
			result.setDestinationURL(Path.PAGE_ABOUT);
			break;
		case "admin":
			result.setDestinationURL(Path.PAGE_ADMIN);
			break;
		case "login":
			result.setDestinationURL(Path.PAGE_LOGIN);
			break;
		case "registration":
			result.setDestinationURL(Path.PAGE_REGISTRATION);
			break;
		case "office":
			result.setDestinationURL(Path.PAGE_USER_OFFICE);
			break;
		default:
			break;
		}

		return result;
	}

}
