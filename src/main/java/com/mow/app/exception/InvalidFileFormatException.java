package com.mow.app.exception;

/**
 * Unchecked exception to handle the wrong file input format and contract
 * 
 * @author enrique.rolon
 *
 */
public class InvalidFileFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -843256209383924271L;

	public InvalidFileFormatException(final String message) {
		super(message);
	}

	public InvalidFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
