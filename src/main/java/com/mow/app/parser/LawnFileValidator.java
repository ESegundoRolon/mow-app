package com.mow.app.parser;

import com.mow.app.exception.InvalidLawnException;
import com.mow.app.model.entity.LawnDimension;

/**
 * Class validator to check object constraints from the input file
 * 
 * @author enrique.rolon
 *
 */
public class LawnFileValidator {

	/**
	 * Only static method access
	 */
	private LawnFileValidator() {
	}

	/**
	 * Validate dimension line. It expects X Y
	 * 
	 * @param tokens
	 */
	public static void validateLawnDimension(final String[] tokens) {
		if (tokens == null || tokens.length != 2) {
			throw new InvalidLawnException("Lawn dimension should have 2 dimensions");
		}
	}

	/**
	 * Validate mower line. It expects X Y D and that the coordinates X Y are inside
	 * the lawn dimension boundaries
	 * 
	 * @param tokens
	 * @param dimension
	 */
	public static void validateMower(final String[] tokens, final LawnDimension dimension) {

		if (tokens.length != 3 || dimension == null) {
			throw new InvalidLawnException("Mower position line is not valid or dimension is null");
		}

		int x = Integer.parseInt(tokens[0]);
		int y = Integer.parseInt(tokens[1]);

		if (x > dimension.getX() || y > dimension.getY()) {
			throw new InvalidLawnException("Mower is specified out of the lwn boundaries");
		}
	}

}
