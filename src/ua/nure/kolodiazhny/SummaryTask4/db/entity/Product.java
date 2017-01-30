package ua.nure.kolodiazhny.SummaryTask4.db.entity;

import java.sql.Date;

import ua.nure.kolodiazhny.SummaryTask4.db.status.ProductStatus;

/**
 * Product entity.
 *
 * @author B.kolodiazhny
 *
 */
public class Product extends Entity {

	private static final long serialVersionUID = 8752783102703834253L;

	private String name;

	private int price;

	private int stock;

	private Date manufactoryDate;

	private String size;

	private String color;

	private String imageSource;

	private String description;

	private int categoryId;

	private ProductStatus status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getManufactoryDate() {
		return manufactoryDate;
	}

	public void setManufactoryDate(Date manufactoryDate) {
		this.manufactoryDate = manufactoryDate;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Product [id: " + getId() + ", name: " + name + ", price: " + price + ", stock: " + stock
				+ ", manufactoringDate: " + manufactoryDate + ", size: " + size + ", color: " + color
				+ ", imageSource: " + imageSource + ", description: " + description + ", categoryId: " + categoryId
				+ ", status: " + status + "]";
	}

}
