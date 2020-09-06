package com.mow.app.exception;

/**
 * Runtime exception to handle functional conditions
 * 
 * @author enrique.rolon
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2915837351383637782L;

	public BusinessException(final String message) {
		super(message);
	}

}
