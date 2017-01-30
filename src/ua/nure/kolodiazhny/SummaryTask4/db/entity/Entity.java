package ua.nure.kolodiazhny.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Common entity.
 *
 * @author B.kolodiazhny
 *
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = 4748884051231983482L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
