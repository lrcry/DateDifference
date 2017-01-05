package au.com.ioof.exceptions;

/**
 * Invalid input exception<br/>
 * Thrown when input data does not pass the input check<br/>
 * 
 * @author hansmong
 *
 */
public class InvalidInputException extends DateDifferenceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6316542853441635022L;

	public InvalidInputException(String msg) {
		super(msg);
	}
}
