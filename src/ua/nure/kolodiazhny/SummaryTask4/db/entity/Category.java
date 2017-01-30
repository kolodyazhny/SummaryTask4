package ua.nure.kolodiazhny.SummaryTask4.db.entity;

/**
 * Category entity.
 *
 * @author B.kolodiazhny
 *
 */
public class Category extends Entity {

	private static final long serialVersionUID = 5564393615661006870L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id: " + getId() + ", name: " + name + "]";
	}

}
