package ua.nure.kolodiazhny.SummaryTask4.web.command;

/**
 * Container for result of a command execution. Contains two fields:
 * <b>destinationURL</b> - address which pass a control and
 * <b>transitionMethod</b> - method for passing.
 *
 * @author B.kolodiazhny
 *
 */
public final class CommandResult {

	/**
	 * Holds a value for the transition method.
	 */
	public static final String REDIRECT = "REDIRECT";

	/**
	 * Holds a value for the transition method.
	 */
	public static final String FORWARD = "FORWARD";

	/**
	 * Address to pass a control.
	 */
	private String destinationURL;

	/**
	 * Type of a method to pass a control.
	 */
	private String transitionMethod;

	public CommandResult() {
		// No operations
	}

	public CommandResult(String destinationURL) {
		this.destinationURL = destinationURL;
	}

	public String getDestinationURL() {
		return destinationURL;
	}

	public void setDestinationURL(String destinationURL) {
		this.destinationURL = destinationURL;
	}

	public String getTransitionMethod() {
		return transitionMethod;
	}

	public void setTransitionRedirect() {
		this.transitionMethod = REDIRECT;
	}

	public void setTransitionForward() {
		this.transitionMethod = FORWARD;
	}

}
