package ua.nure.kolodiazhny.SummaryTask4.db.entity;

import ua.nure.kolodiazhny.SummaryTask4.db.status.UserStatus;

/**
 * User entity.
 *
 * @author B.kolodiazhny
 *
 */
public class User extends Entity {

	private static final long serialVersionUID = 3217271575450945876L;

	private String login;

	private String password;

	private String email;

	private String firstName;

	private String lastName;

	private String phone;

	private String address;

	private String gender;

	private UserStatus status;

	private int roleId;

	// private int currentOrderId;
	private Integer currentOrderId;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	// public int getCurrentOrderId() {
	// return currentOrderId;
	// }
	//
	// public void setCurrentOrderId(int currentOrderId) {
	// this.currentOrderId = currentOrderId;
	// }

	public Integer getCurrentOrderId() {
		return currentOrderId;
	}

	public void setCurrentOrderId(Integer currentOrderId) {
		this.currentOrderId = currentOrderId;
	}

	@Override
	public String toString() {
		return "User [id: " + getId() + ", login: " + login + ", password: " + password + ", email: " + email
				+ ", firstName: " + firstName + ", lastName: " + lastName + ", phone: " + phone + ", address: "
				+ address + ", gender:" + gender + ", status: " + status + ", roleId: " + roleId + ", currentOrderId: "
				+ currentOrderId + "];";
	}

}
