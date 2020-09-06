package com.mow.app.exception;

/**
 * Checked exception to handle the wrong file extension
 * 
 * @author enrique.rolon
 *
 */
public class InvalidFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -843256209383924271L;

	public InvalidFileException(final String message) {
		super(message);
	}

	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
