package ua.nure.kolodiazhny.SummaryTask4.exception;

/**
 * An exception that provides information on an application error.
 *
 * @author B.kolodiazhny
 *
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 2849814187910263369L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}
}
