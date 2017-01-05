package au.com.ioof.exceptions;

/**
 * Date difference exception<br/>
 * Abstract exception to be extended for different use<br/>
 * 
 * @author hansmong
 *
 */
public abstract class DateDifferenceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6246643820241586174L;

	public DateDifferenceException(String msg) {
		super(msg);
	}
}
