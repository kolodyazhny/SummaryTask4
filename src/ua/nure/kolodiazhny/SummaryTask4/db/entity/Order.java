package ua.nure.kolodiazhny.SummaryTask4.db.entity;

import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;

/**
 * Order entity.
 *
 * @author B.kolodiazhny
 *
 */
public class Order extends Entity {

	private static final long serialVersionUID = 5402852709839806288L;

	private int userId;

	private OrderStatus status;

	private int totalPrice;

	private String paymentInfo;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	@Override
	public String toString() {
		return "Order [id: " + getId() + ", userId: " + userId + ", status: " + status + ", totalPrice: " + totalPrice
				+ ", paymantInfo: " + paymentInfo + "]";
	}

}
