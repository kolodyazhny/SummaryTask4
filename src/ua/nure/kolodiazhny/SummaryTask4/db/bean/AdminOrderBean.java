package ua.nure.kolodiazhny.SummaryTask4.db.bean;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.Entity;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;

/**
 * Provides records for virtual table.
 *
 * <pre>
 * |order.id|user.login|user.first_name|user.last_name|user.email|order.total_price|order.payment_info|order.status|
 * </pre>
 *
 * @author B.Selin
 *
 */
public class AdminOrderBean extends Entity {

	private static final long serialVersionUID = -6921584208196536307L;

	private int orderId;

	private String userLogin;

	private String userFName;

	private String userLName;

	private String userEmail;

	private int orderTotalPrice;

	private String orderPaymentInfo;

	private OrderStatus oderStatus;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public String getUserLName() {
		return userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderPaymentInfo() {
		return orderPaymentInfo;
	}

	public void setOrderPaymentInfo(String orderPaymentInfo) {
		this.orderPaymentInfo = orderPaymentInfo;
	}

	public OrderStatus getOrderStatus() {
		return oderStatus;
	}

	public void setOderStatus(OrderStatus oderStatus) {
		this.oderStatus = oderStatus;
	}

	@Override
	public String toString() {
		return "AdminOrderBean [orderId: " + orderId + ", userFName: " + userFName + ", userLName: " + userLName
				+ ", userEmail: " + userEmail + ", userLogin: " + userLogin + ", orderTotalPrice:" + orderTotalPrice
				+ ", orderPaymentInfo: " + orderPaymentInfo + "orderStatus: " + oderStatus + "]";
	}
}
