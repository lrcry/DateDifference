package au.com.ioof.exceptions;

/**
 * Unsupported date format exception<br/>
 * Thrown when the date format given could not be supported<br/>
 * 
 * @author hansmong
 *
 */
public class UnsupportedDateFormatException extends DateDifferenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4203934019315636711L;

	public UnsupportedDateFormatException(String msg) {
		super(msg);
	}

}
