package com.mow.app.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.model.entity.LawnDimension;

public class LawnFileValidatorTest {

	@Test
	public void givenValidateLawnDimension_with3Tokens_thenThrowInvalidFileFormatException() {
		try {
			final String[] tokens = { "1", "1", "1" };
			LawnFileValidator.validateLawnDimension(tokens);
			fail("Should throw InvalidFileFormatException when dimension line has more numbers that needed");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenValidateLawnDimension_withNullTokens_thenThrowInvalidFileFormatException() {
		try {
			final String[] tokens = null;
			LawnFileValidator.validateLawnDimension(tokens);
			fail("Should throw InvalidFileFormatException when dimension line is null");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_with2Tokens_thenThrowInvalidFileFormatException() {
		try {
			final String[] tokens = { "1", "1" };
			LawnFileValidator.validateMower(tokens, null);
			fail("Should throw InvalidFileFormatException when dimension line is null");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_withNullDimension_thenThrowInvalidFileFormatException() {
		try {
			final String[] tokens = { "1", "1", "1" };
			LawnFileValidator.validateMower(tokens, null);
			fail("Should throw InvalidFileFormatException when mower line has invalid direction");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_withHorizontalPositionOutOfBounds_thenThrowInvalidFileFormatException() {
		try {
			final String[] tokens = { "1", "1", "S" };
			final LawnDimension dimension = LawnDimension.of(0, 0);

			LawnFileValidator.validateMower(tokens, dimension);
			fail("Should throw InvalidFileFormatException when mower line has invalid position");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenValidateMower_withVerticalPositionOutOfBounds_thenThrowInvalidFileFormatException() {
		try {
			final String[] tokens = { "0", "1", "1" };
			final LawnDimension dimension = LawnDimension.of(0, 0);

			LawnFileValidator.validateMower(tokens, dimension);
			fail("Should throw InvalidFileFormatException when mower line has invalid position");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}
}
