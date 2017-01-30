package ua.nure.kolodiazhny.SummaryTask4.exception;

/**
 * An exception that provides information on a database access error.
 *
 * @author B.kolodiazhny
 *
 */
public class DBException extends AppException {

	private static final long serialVersionUID = -3405588200017006098L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}
}
