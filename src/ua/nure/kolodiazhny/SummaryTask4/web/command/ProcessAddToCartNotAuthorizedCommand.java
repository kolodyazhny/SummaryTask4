package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.constant.Path;
import ua.nure.kolodiazhny.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.CategoryDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.DAOFactory;
//import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderDAO;
//import ua.nure.kolodiazhny.SummaryTask4.db.dao.OrderItemDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.dao.ProductDAO;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Category;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Order;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.OrderItem;
import ua.nure.kolodiazhny.SummaryTask4.db.entity.Product;
import ua.nure.kolodiazhny.SummaryTask4.db.util.DBUtil;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Processes an action of adding products to a cart wile not logged in.
 *
 * @author B.kolodiazhny
 *
 */
public class ProcessAddToCartNotAuthorizedCommand extends Command {

	private static final long serialVersionUID = 9043908635678962322L;

	/**
	 * Apache Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ProcessAddToCartNotAuthorizedCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug(Messages.SUCCESS_COMMAND_STARTED + getClass().getSimpleName());

		CommandResult result = new CommandResult(Path.COMMAND_VIEW_ADD_TO_CART);
		result.setTransitionRedirect();

		HttpSession session = request.getSession();

		DAOFactory.setDaoFactoryFCN(DBUtil.MYSQL_DAO_FACTORY_FCN);
		DAOFactory daoFactory = DAOFactory.getInstance();

		String productIdAsString = request.getParameter("productId");
		String categoryName = request.getParameter("currentCategory");
		LOG.info(Messages.TRACE_REQUES_PARAMETER + productIdAsString);
		LOG.info(Messages.TRACE_REQUES_PARAMETER + categoryName);

		if (!isParamEmpty(categoryName)) {
			CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
			Category category = categoryDAO.findCategoryByName(categoryName);
			result.setDestinationURL(Path.COMMAND_VIEW_ADD_TO_CART + "&categoryId=" + category.getId()
					+ "&categoryName=" + categoryName);
		}

		if (isParamEmpty(productIdAsString)) {
			LOG.debug("ERR id null");
			// ERR
		}

		int productId = Integer.valueOf(productIdAsString);

		ProductDAO productDAO = daoFactory.getProductDAO();
		Product product = productDAO.findProductById(productId);

		if (product == null) {
			LOG.debug("ERR product null");
			// ERR
		}

		if (product.getStock() < 1) {
			// CANT BUY
		}

		Order cart = new Order();

		List<OrderItem> items = new ArrayList<OrderItem>();
		LOG.debug("ORDER ITEMS FORM REQUEST!!!!!!!!!!!!" + session.getAttribute("items"));
		if (session.getAttribute("items") != null) {
			items = (List<OrderItem>) session.getAttribute("items");
		}

		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setProductId(productId);
		// ADD PRODUCTS COUNT?
		newOrderItem.setProductsCount(1);
		// * PRODUCTS COUNT!
		newOrderItem.setPrice(product.getPrice());

		/**
		 * Checks if order_item already exists. If true - updates current
		 * order_item, if false - adds a new one to a database.
		 */
		boolean flag = true;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProductId() == newOrderItem.getProductId()) {
				items.get(i).setPrice(items.get(i).getPrice() + newOrderItem.getPrice());
				items.get(i).setProductsCount(items.get(i).getProductsCount() + newOrderItem.getProductsCount());
				flag = false;
				break;
			}
		}
		if (flag) {
			items.add(newOrderItem);
		}

		int price = 0;
		for (OrderItem item : items) {
			price += item.getPrice();
		}
		cart.setTotalPrice(price);

		List<UserOrderBean> cartBeans = new ArrayList<UserOrderBean>();
		LOG.debug("BEAN FROM EQUEST!!!!!!!!!!!!" + session.getAttribute("cartBeans"));
		if (session.getAttribute("cartBeans") != null) {
			cartBeans = (List<UserOrderBean>) session.getAttribute("cartBeans");
		}

		UserOrderBean newCartBean = new UserOrderBean();
		newCartBean.setOrderItemPrice(newOrderItem.getPrice());
		newCartBean.setOrderItemProdctsCount(newOrderItem.getProductsCount());
		newCartBean.setProductName(product.getName());

		/**
		 * Checks if order_item_bean is already exists. If true - updates
		 * current order_item, if false - adds a new one to a database.
		 */
		flag = true;
		for (int i = 0; i < cartBeans.size(); i++) {
			if (cartBeans.get(i).getProductName().equals(newCartBean.getProductName())) {
				cartBeans.get(i).setOrderItemProdctsCount(
						cartBeans.get(i).getOrderItemProdctsCount() + newCartBean.getOrderItemProdctsCount());
				cartBeans.get(i)
						.setOrderItemPrice(cartBeans.get(i).getOrderItemPrice() + newCartBean.getOrderItemPrice());
				flag = false;
				break;
			}
		}
		if (flag) {
			cartBeans.add(newCartBean);
		}

		session.setAttribute("cartBeans", cartBeans);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cartBeans);
		session.setAttribute("cart", cart);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + cart);
		session.setAttribute("items", items);
		LOG.trace(Messages.TRACE_ATRIBUTE_ADDED_TO_SESSION + items);

		return result;
	}

}
