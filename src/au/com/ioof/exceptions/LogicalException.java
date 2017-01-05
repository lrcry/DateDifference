package au.com.ioof.exceptions;

/**
 * Business logic exception<br/>
 * Thrown when the program violates certain business logics<br/>
 * 
 * @author hansmong
 *
 */
public class LogicalException extends DateDifferenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7651400888992513632L;

	public LogicalException(String msg) {
		super(msg);
	}

}
