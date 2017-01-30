package ua.nure.kolodiazhny.SummaryTask4.db.bean;

import ua.nure.kolodiazhny.SummaryTask4.db.entity.Entity;
import ua.nure.kolodiazhny.SummaryTask4.db.status.OrderStatus;

/**
 * Provides records for virtual table.
 *
 * <pre>
 * |product.name|product.img_src|order_item.item_price|order_item.products_count|order.payment_info|order.status|order.payment_info|
 * </pre>
 *
 * @author B.Selin
 *
 */
public class UserOrderBean extends Entity {

	private static final long serialVersionUID = -6921584208196536307L;

	private String productName;

	private String productImage;

	private int orderItemPrice;

	private int orderItemProdctsCount;

	private String orderPaymentInfo;

	private OrderStatus orderStatus;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getOrderItemPrice() {
		return orderItemPrice;
	}

	public void setOrderItemPrice(int orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public int getOrderItemProdctsCount() {
		return orderItemProdctsCount;
	}

	public void setOrderItemProdctsCount(int orderItemProdctsCount) {
		this.orderItemProdctsCount = orderItemProdctsCount;
	}

	public String getOrderPaymentInfo() {
		return orderPaymentInfo;
	}

	public void setOrderPaymentInfo(String orderPaymentInfo) {
		this.orderPaymentInfo = orderPaymentInfo;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "UserOrderBean [productName: " + productName + ", productImage: " + productImage + ", orderItemPrice: "
				+ orderItemPrice + ", orderItemProductsCount: " + orderItemProdctsCount + ", orderPaymentInfo: "
				+ orderPaymentInfo + "orderStatus: " + orderStatus + "]";
	}
}
