package ua.nure.kolodiazhny.SummaryTask4.constant;

/**
 * Holder for names of the DataBase fields.
 *
 * @author B.kolodiazhny
 *
 */
public final class Fields {

	// common fields
	public static final String ENTITY_ID = "id";

	// roles table fields
	public static final String ROLE_NAME = "name";

	// users table fields
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_EMAIL = "email";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_PHONE = "phone";
	public static final String USER_ADDRESS = "address";
	public static final String USER_GENDER = "gender";
	public static final String USER_STATUS = "status";
	public static final String USER_ROLE_ID = "role_id";
	public static final String USER_CURRENT_ORDER_ID = "current_order_id";

	// categories table fields
	public static final String CATEGORY_NAME = "name";

	// products table fields
	public static final String PRODUCT_NAME = "name";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_STOCK = "stock";
	public static final String PRODUCT_MANUFACTORING_DATE = "manufactoring_date";
	public static final String PRODUCT_SIZE = "size";
	public static final String PRODUCT_COLOR = "color";
	public static final String PRODUCT_IMAGE_SOURCE = "img_src";
	public static final String PRODUCT_DESCRIPTION = "description";
	public static final String PRODUCT_CATEGOEY_ID = "category_id";

	// order_items table fields
	public static final String ORDER_ITEM_PRODUCT_ID = "product_id";
	public static final String ORDER_ITEM_PRODUCTS_COUNT = "products_count";
	public static final String ORDER_ITEM_PRICE = "item_price";
	public static final String ORDER_ITEM_ORDER_ID = "order_id";

	// orders table fields
	public static final String ORDER_USER_ID = "user_id";
	public static final String ORDER_STATUS = "status";
	public static final String ORDER_PAYMENT_INFO = "payment_info";
	public static final String ORDER_TOTAL_PRICE = "total_price";

	// admin_order_beans fields
	public static final String ADMIN_ORDER_BEAN_ORDER_ID = "id";
	public static final String ADMIN_ORDER_BEAN_USER_LOGIN = "login";
	public static final String ADMIN_ORDER_BEAN_USER_FIRST_NAME = "first_name";
	public static final String ADMIN_ORDER_BEAN_USER_LAST_NAME = "last_name";
	public static final String ADMIN_ORDER_BEAN_USER_EMAIL = "email";
	public static final String ADMIN_ORDER_BEAN_ORDER_PRICE = "total_price";
	public static final String ADMIN_ORDER_BEAN_ORDER_STATUS = "status";
	public static final String ADMIN_ORDER_BEAN_ORDER_PAMENT_INFO = "payment_info";

	// user_order_bean fields
	public static final String USER_ORDER_BEAN_ORDER_STATUS = "status";
	public static final String USER_ORDER_BEAN_ORDER_PAYMENT_INFO = "payment_info";
	public static final String USER_ORDER_BEAN_ORDER_ITEM_PRODUCTS_COUNT = "products_count";
	public static final String USER_ORDER_BEAN_ORDER_ITEM_PRICE = "item_price";
	public static final String USER_ORDER_BEAN_PRODUCT_NAME = "name";
	public static final String USER_ORDER_BEAN_PRODUCT_IMAGE = "img_src";
}
