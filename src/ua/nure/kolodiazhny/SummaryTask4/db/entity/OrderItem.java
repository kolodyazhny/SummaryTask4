package ua.nure.kolodiazhny.SummaryTask4.db.entity;

/**
 * OrderItem entity.
 *
 * @author B.kolodiazhny
 *
 */
public class OrderItem extends Entity {

	private static final long serialVersionUID = 9137178883650267878L;

	private int productId;

	private int orderId;

	private int productsCount;

	private int price;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductsCount() {
		return productsCount;
	}

	public void setProductsCount(int productsCount) {
		this.productsCount = productsCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem [id: " + getId() + ", productId: " + productId + ", orderId: " + orderId + ", productsCount: "
				+ productsCount + ", price: " + price + "]";
	}

}
