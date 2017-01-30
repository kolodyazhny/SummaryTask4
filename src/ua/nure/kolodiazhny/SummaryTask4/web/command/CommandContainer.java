package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;

/**
 * Holds command objects according to their names.
 *
 * @author B.kolodiazhny
 *
 */
public class CommandContainer {

	/**
	 * Apache Log4j logger.
	 */
	private static Logger LOG = Logger.getLogger(CommandContainer.class);

	/**
	 * Map that contains commands according to their names. Actual
	 * "CommandContainer".
	 */
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	/**
	 * Adding commands to the CommandsContainer.
	 */
	static {
		// PUT COMMANDS HERE
		commands.put("navigator", new CommonNavigatorCommand());
		commands.put("changeOrderStatus", new AdminChangeOrderStatusCommand());
		commands.put("findAllOrders", new ClientDisplayOrdersCommand());
		commands.put("viewPlaceOrder", new ViewPlaceOrderCommand());
		commands.put("choseAction", new CommonChoseActionCommand());
		commands.put("placeOrder", new CommonPlaceOrderCommand());
		commands.put("modifyProduct", new AdminModifyProductCommand());
		commands.put("deleteProduct", new AdminDeleteProductCommand());
		commands.put("admDisplayOrders", new AdminDisplayOrdersCommand());
		commands.put("changeUserStatus", new AdminChangeUserStatusCommand());
		commands.put("processShowUsers", new AdminShowUsersCommand());
		commands.put("processAdminAction", new AdminChoseActionCommand());
		commands.put("processUserOffice", new ProcessUserOfficeCommand());
		commands.put("viewAddToCart", new ViewAddToCartCommand());
		commands.put("processAddToCart", new ProcessAddToCartCommand());
		commands.put("processCartNotAuthorized", new ProcessAddToCartNotAuthorizedCommand());
		commands.put("processCartAuthorized", new ProcessAddToCartAuthorizedCommand());
		commands.put("processLogin", new ProcessLoginCommand());
		commands.put("viewLogin", new ViewLoginCommand());
		commands.put("processLogout", new ProcessLogoutCommand());
		commands.put("processRegistrarion", new ProcessRegistrationCommand());
		commands.put("viewRegistration", new ViewRegistrationCommand());
		commands.put("displayProducts", new ProcessProductsCommand());
		commands.put("processAddProduct", new ProcessAddProductCommand());
		commands.put("viewAddProduct", new ViewAddProductCommand());
		LOG.debug(Messages.SUCCESS_COMMANDS_INITIALIZED);
		LOG.trace("Commands in the list: " + commands.keySet());
	}

	/**
	 * Returns Command object from CommandContainer by the name.
	 *
	 * @param commandName
	 *            name of the Command.
	 * @return Command object.
	 */
	public static Command getCommand(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace(Messages.ERR_CANNOT_FIND_COMMAND + commandName);
			return commands.get("noCommand");
		}
		LOG.trace(Messages.SUCCESS_COMMAND_FOUNDED + commandName);
		return commands.get(commandName);
	}

}
