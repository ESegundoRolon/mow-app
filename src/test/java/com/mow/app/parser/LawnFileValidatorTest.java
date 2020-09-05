package com.mow.app.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.mow.app.exception.InvalidLawnException;
import com.mow.app.model.entity.LawnDimension;

public class LawnFileValidatorTest {

	@Test
	public void givenValidateLawnDimension_with3Tokens_thenThrowInvalidLawnException() {
		try {
			final String[] tokens = { "1", "1", "1" };
			LawnFileValidator.validateLawnDimension(tokens);
			fail("Should throw InvalidLawnException when dimension line has more numbers that needed");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenValidateLawnDimension_withNullTokens_thenThrowInvalidLawnException() {
		try {
			final String[] tokens = null;
			LawnFileValidator.validateLawnDimension(tokens);
			fail("Should throw InvalidLawnException when dimension line is null");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_with2Tokens_thenThrowInvalidLawnException() {
		try {
			final String[] tokens = { "1", "1" };
			LawnFileValidator.validateMower(tokens, null);
			fail("Should throw InvalidLawnException when dimension line is null");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_withNullDimension_thenThrowInvalidLawnException() {
		try {
			final String[] tokens = { "1", "1", "1" };
			LawnFileValidator.validateMower(tokens, null);
			fail("Should throw InvalidLawnException when mower line has invalid direction");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_withHorizontalPositionOutOfBounds_thenThrowInvalidLawnException() {
		try {
			final String[] tokens = { "1", "1", "S" };
			final LawnDimension dimension = LawnDimension.of(0, 0);

			LawnFileValidator.validateMower(tokens, dimension);
			fail("Should throw InvalidLawnException when mower line has invalid position");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_withVerticalPositionOutOfBounds_thenThrowInvalidLawnException() {
		try {
			final String[] tokens = { "0", "1", "1" };
			final LawnDimension dimension = LawnDimension.of(0, 0);

			LawnFileValidator.validateMower(tokens, dimension);
			fail("Should throw InvalidLawnException when mower line has invalid position");
		} catch (InvalidLawnException e) {
			// success
		}
	}
}
