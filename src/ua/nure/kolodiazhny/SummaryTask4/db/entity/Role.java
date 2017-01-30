package ua.nure.kolodiazhny.SummaryTask4.db.entity;

/**
 * Role entity.
 *
 * @author B.kolodiazhny
 *
 */
public enum Role {
	ADMIN, CLIENT;

	/**
	 * Returns user role by his roleId.
	 *
	 * @param user
	 * @return role
	 */
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId - 1];
	}
}
