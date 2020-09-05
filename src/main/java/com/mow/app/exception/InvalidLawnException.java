package com.mow.app.exception;

/**
 * Runtime exception to handle invalid parameter values
 * 
 * @author enrique.rolon
 *
 */
public class InvalidLawnException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2915837351383637782L;

	public InvalidLawnException(final String message) {
		super(message);
	}

}
