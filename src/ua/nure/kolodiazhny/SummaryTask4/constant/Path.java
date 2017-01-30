package ua.nure.kolodiazhny.SummaryTask4.constant;

/**
 * Path to pages, commands etc.
 *
 * @author B.kolodiazhny
 *
 */
public final class Path {

	// JSPs
	public static final String PAGE_USER_OFFICE = "/WEB-INF/jsp/client/userOffice.jsp";
	public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/admin.jsp";
	public static final String PAGE_MAIN = "/WEB-INF/jsp/common/main.jsp";
	public static final String PAGE_LOGIN = "/WEB-INF/jsp/common/login.jsp";
	public static final String PAGE_ABOUT = "/WEB-INF/jsp/common/about.jsp";
	public static final String PAGE_CONTACTS = "/WEB-INF/jsp/common/contacts.jsp";
	public static final String PAGE_SHOP = "/WEB-INF/jsp/common/shop.jsp";
	public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/common/registration.jsp";

	// Commands
	public static final String COMMAND_VIEW_PLACE_ORDER = "controller?command=viewPlaceOrder";
	public static final String COMMAND_MODIFY_PRODUCT = "controller?command=modifyProduct";
	public static final String COMMAND_DELETE_PRODUCT = "controller?command=deleteProduct";
	public static final String COMMAND_ADMIN_DISPLAY_ORDERS = "controller?command=admDisplayOrders";
	public static final String COMMAND_ADMIN_ACTIONS = "controller?command=processAdminAction";
	public static final String COMMAND_SHOW_USERS = "controller?command=processShowUsers";
	public static final String COMMAND_PROCESS_PRODUCTS = "controller?command=displayProducts";
	public static final String COMMAND_ADD_TO_CART_NOT_AUTHORIZED = "controller?command=processCartNotAuthorized";
	public static final String COMMAND_ADD_TO_CART_AUTHORIZED = "controller?command=processCartAuthorized";
	public static final String COMMAND_VIEW_ADD_TO_CART = "controller?command=viewAddToCart";
	public static final String COMMAND_VIEW_ADD_PRODUCT = "controller?command=viewAddProduct";
	public static final String COMMAND_VIEW_LOGIN = "controller?command=viewLogin";
	public static final String COMMAND_VIEW_REGISTRARION = "controller?command=viewRegistration";

}
