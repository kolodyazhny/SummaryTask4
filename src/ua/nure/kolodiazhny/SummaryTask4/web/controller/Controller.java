package ua.nure.kolodiazhny.SummaryTask4.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;
import ua.nure.kolodiazhny.SummaryTask4.web.command.Command;
import ua.nure.kolodiazhny.SummaryTask4.web.command.CommandContainer;
import ua.nure.kolodiazhny.SummaryTask4.web.command.CommandResult;

/**
 * Main controller servlet.
 *
 * @author B.kolodiazhny
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -5177314642991749871L;

	/**
	 * Apache Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main method of Controller. Gets command by name obtained from request and
	 * executes it.
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	private static void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("Controller has been started");

		String commandName = request.getParameter("command");
		LOG.trace("Current command name from request: " + commandName);
		Command command = CommandContainer.getCommand(commandName);
		LOG.trace(Messages.SUCCESS_COMMAND_FOUNDED + command);

		try {
			CommandResult commandResult = command.execute(request, response);
			String transitionMethod = commandResult.getTransitionMethod();

			String destinationURL = commandResult.getDestinationURL();
			LOG.trace("URL: " + destinationURL);

			if (transitionMethod.equals(CommandResult.REDIRECT)) {

				LOG.debug(Messages.TRACE_CONTROLLER_FINISHED);
				response.sendRedirect(destinationURL);

			} else if (transitionMethod.equals(CommandResult.FORWARD)) {

				LOG.debug(Messages.TRACE_CONTROLLER_FINISHED);
				request.getRequestDispatcher(destinationURL).forward(request, response);

			} else {
				LOG.error(Messages.ERR_METHOD_NOT_DEFINED);
				throw new AppException(Messages.ERR_METHOD_NOT_DEFINED);
			}
		} catch (AppException e) {
			LOG.error(Messages.ERR_WHILE_EXECUTING_COMMAND);
			request.setAttribute("errorMessage", e.getMessage());
		}

	}

}
