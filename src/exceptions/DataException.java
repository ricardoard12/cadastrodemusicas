package exceptions;

public class DataException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2318790490159027863L;

	public DataException() {
		super("Data Exception");
	}
	
	public DataException(String message) {
		super(message);
	}
}
