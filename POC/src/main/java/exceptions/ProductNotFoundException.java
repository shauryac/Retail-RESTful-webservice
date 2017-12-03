package exceptions;

public class ProductNotFoundException extends Exception {
	
	/**
	 * default serial  version id
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
