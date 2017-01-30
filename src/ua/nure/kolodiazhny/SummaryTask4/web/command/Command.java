package ua.nure.kolodiazhny.SummaryTask4.web.command;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask4.constant.Messages;
import ua.nure.kolodiazhny.SummaryTask4.exception.AppException;

/**
 * Main class of the Command pattern implementation.
 *
 * @author B.kolodiazhny
 *
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = 7204476818217250489L;

	private static final Logger LOG = Logger.getLogger(Command.class);

	/**
	 * Executes command functionality.
	 *
	 * @param request
	 *            HttpServletRequest.
	 * @param response
	 *            HttpServletResponse
	 * @return String address to forward or redirect.
	 * @throws AppException
	 */
	public abstract CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException;

	/**
	 * Checks if a requested string empty or not.
	 *
	 * @param param
	 *            String representation of a parameter.
	 * @return <b>true</b> if a parameter empty, <b>false</b> if not.
	 */
	protected static boolean isParamEmpty(String param) {
		if (param == null || param.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if a requested strings empty or not.
	 *
	 * @param param1,
	 *            param2 String representation of a parameters.
	 * @return <b>true</b> if at least one of parameters empty, <b>false</b> if
	 *         they both are not empty.
	 */
	protected static boolean isParamsEmpty(String param1, String param2) {
		if (isParamEmpty(param1) || isParamEmpty(param2)) {
			return true;
		}
		return false;
	}

	/**
	 * Method that generates a hash for a password to keep it in a database.
	 *
	 * @param textToHash
	 *            text to generate a hash for.
	 * @return String object, hashed representation of obtained attribute.
	 * @throws AppException
	 */
	protected static String generateHash(String textToHash) throws AppException {
		StringBuilder hash = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashBytes = sha.digest(textToHash.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int i = 0; i < hashBytes.length; i++) {
				byte b = hashBytes[i];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			LOG.error(Messages.ERR_PASSWORD_HASHING, e);
			throw new AppException(Messages.ERR_PASSWORD_HASHING, e);
		}
		return hash.toString();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
