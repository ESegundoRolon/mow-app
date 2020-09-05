package com.mow.app.exception;

/**
 * Checked exception to handle the wrong file input format
 * 
 * @author enrique.rolon
 *
 */
public class InvalidFileFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -843256209383924271L;

	public InvalidFileFormatException(final String message) {
		super(message);
	}

}
