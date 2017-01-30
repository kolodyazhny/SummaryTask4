package ua.nure.kolodiazhny.SummaryTask4.constant;

/**
 * Messages to describe exceptions, errors and log the program.
 *
 * @author B.kolodiazhny
 *
 */
public final class Messages {

	// Trace messages
	public static final String TRACE_COOKIE_FOUNDED = "Value of a cookie that has been founded in a response: ";
	public static final String TRACE_COOKIE_CHANGED = "Cookie had initialized or changed. Curren cookie value: ";
	public static final String TRACE_CURRENT_SESSION = "Current session object is: ";
	public static final String TRACE_DAO_OBJECT_CREATED = "DAOFactory object has been created: ";
	public static final String TRACE_CONTROLLER_FINISHED = "Controller has finished it's work.";
	public static final String TRACE_DESTINATION_URL = "Current destination URL: ";
	public static final String TRACE_ATRIBUTE_ADDED_TO_REQUEST = "Attribute has added to the request scope: ";
	public static final String TRACE_ATRIBUTE_ADDED_TO_SESSION = "Attribute has added to the session scope: ";
	public static final String TRACE_FOUNDED_IN_DATA_BASE = "Objct founded in database: ";
	public static final String TRACE_USER_ROLE = "User role obtained by user.roleId: ";
	public static final String TRACE_REQUES_PARAMETER = "Parameter obtained from the request: ";

	// Successes messages
	public static final String SUCCESS_DATA_SOURCE_OBTAINED = "Data source has been successfully obtained: ";
	public static final String SUCCESS_DAO_FACTORY_INSTANCE_OBTAINED = "DAOFActory instance has been successfuly obtained: ";
	public static final String SUCCESS_COMMAND_STARTED = "Command has started it's work: ";
	public static final String SUCCESS_COMMAND_FOUNDED = "Command has been successfuly founded in the CommandContainer: ";
	public static final String SUCCESS_COMMANDS_INITIALIZED = "Commands has been successfully added to the CommandContainer";
	public static final String SUCCESS_CONNECTION_CLOSED = "Connection has been successfuly closed";
	public static final String SUCCESS_STATEMENT_CLOSED = "Statement has been successfuly closed";
	public static final String SUCCESS_RESULTSET_CLOSED = "ResultSet has been successfuly closed";
	public static final String SUCCESS_CONNECTION_ROLLED_BACK = "Connection has been rolled back";

	// Error messages
	public static final String ERR_CANNOT_FIND_ALL_ORDERS_BY_USER_ID = "Cannot obtain all orders by user id";
	public static final String ERR_CANNOT_UPDATE_ITEM = "Cannot update an order_item";
	public static final String ERR_CANNOT_UPDATE_PRODUCT = "Cannot update a product";
	public static final String ERR_CANNOT_DELETE_PRODUCT = "Cannot change stauts of product on 'DELETED'";
	public static final String ERR_PASSWORD_HASHING = "Cannot generate a hash for a password";
	public static final String ERR_CANNOT_OBRAIN_ADMIN_ORDER_BEANS = "Cannot obtain AdminOrderBean from users and orders tables from a database";
	public static final String ERR_CANNOT_OBRAIN_USER_ORDER_BEANS = "Cannot obtain UserOrderBean from products and order_items tables from a database";
	public static final String ERR_CANNOT_OBRAIN_ORDERS = "Cannot obtain orders from a database";
	public static final String ERR_CANNOT_OBRAIN_USERS = "Cannot obtain users from a database";
	public static final String ERR_CANNOT_UPDATE_ORDER = "Cannot update a order";
	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
	public static final String ERR_CANNOT_FIND_ORDER_BY_STATUS_AND_SUER_ID = "Cannot find an order by its status and user id";
	public static final String ERR_CANNOT_INSERT_ORDER = "Cannot add an order to a database";
	public static final String ERR_METHOD_NOT_DEFINED = "Transition method is not defined";
	public static final String ERR_CANOT_FIND_CATEGORY_BY_NAME = "Cannot find a category by its name";
	public static final String ERR_CANOT_FIND_CATEGORY_BY_ID = "Cannot find a category by its id";
	public static final String ERR_CANOT_ADD_PRODUCT = "Cannot add a product to a database";
	public static final String ERR_CANOT_ADD_CATEGORY = "Cannot add a category to a database";
	public static final String ERR_CANOT_OBTAIN_CATEGORIES = "Cannot obtain categories list from a database";
	public static final String ERR_CANOT_OBTAIN_PRODUCTS = "Cannot obtain products list from a database";
	public static final String ERR_CANOT_FIND_PRODUCT_BY_NAME = "Cannot find a product by its name";
	public static final String ERR_CANOT_FIND_PRODUCT_BY_ID = "Cannot find a product by its id";
	public static final String ERR_CANOT_FIND_PRODUCT_BY_CATEGORY_ID = "Cannot find a product by its category id";
	public static final String ERR_CANNOT_CREATE_USER = "Cannot add a user to a database";
	public static final String ERR_CANNOT_ADD_ORDER_ITEM = "Cannot add orderItem to a database";
	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot find a user by its id";
	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot find a user by its login";
	public static final String ERR_CANNOT_FIND_COMMAND = "Cannot find such command in the ComandContainer: ";
	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	public static final String ERR_CANNOT_INSTANTIATE_DAO_FACTORY = "Cannot instantiate DAOFactory object";
	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
	public static final String ERR_CANNOT_ROLL_BACK_CONNECTION = "Cannot roll back a connection";
	public static final String ERR_WHILE_EXECUTING_COMMAND = "Error was occured in execute method";
	public static final String ERR_CANOT_FIND_ORDER_BY_ID = "Cannot find an order by its id";
	public static final String ERR_CANNOT_OBTAIN_ORDER_ITEMS = "Cannot obtain order items from database";

	// Warning messages
	public static final String WARNING_PASSWORDS_NOT_MATCHES = "Passwodrs are not matches. Please, cheack you input and try again.";
	public static final String WARNING_PRODUCT_ALTEADY_EXISTS = "Product with such name are already exist";
	public static final String WARNING_NO_SUCH_CATEGORY = "There is no category with such name in the system.";
	public static final String WARNING_EMPTY_FIELDS = "One or more of required fields are empty";
	public static final String WARNING_NO_SUCH_USER = "There is no user with such login and password in the system. Try again.";
	public static final String WARNING_USER_BLOCKED = "Unfortunately, user with such login has been blocked";
	public static final String WARNING_USER_ALREADY_EXISTS = "User with such login are already exist";

}
