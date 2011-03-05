package exceptions;

public class DiretorioBaseInvalidoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5510521756602124411L;

	public DiretorioBaseInvalidoException() {
		super("DiretorioBaseInvalidoException");
	}
	
	public DiretorioBaseInvalidoException(String message) {
		super(message);
	}
}
