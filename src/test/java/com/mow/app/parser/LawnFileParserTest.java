package com.mow.app.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.enums.MovementEnum;
import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.model.behaviour.Movable;
import com.mow.app.model.entity.LawnDimension;

public class LawnFileParserTest {

	@Test
	public void givenParseDimension_withNullDimensionLine_thenThrowInvalidFileFormatException() {

		try {
			LawnFileParser.parseDimension(null);

			fail("Should throw InvalidFileFormatException when dimension lines are null");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withEmptyDimensionLine_thenThrowInvalidFileFormatException() {

		try {
			LawnFileParser.parseDimension("");

			fail("Should throw InvalidFileFormatException when dimension lines are empty");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withBlankDimensionLine_thenThrowInvalidFileFormatException() {
		try {
			LawnFileParser.parseDimension(" ");

			fail("Should throw InvalidFileFormatException when dimension lines are blank");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withAlphaStringDimensions_thenThrowInvalidFileFormatException() {

		try {
			LawnFileParser.parseDimension("A B");

			fail("Should throw InvalidFileFormatException when dimension lines are not numbers");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withNumericStringDimensions_thenReturnLawnDimension() {
		LawnDimension dimension = LawnFileParser.parseDimension("1 1");

		assertEquals(1, dimension.getX());
		assertEquals(1, dimension.getY());
	}

	@Test
	public void givenParseMower_withEmptyMowerLine_thenThrowInvalidFileFormatException() {
		try {

			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower("", dimension);

			fail("Should throw InvalidFileFormatException when mower lines is empty");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withBlankMowerLine_thenThrowInvalidFileFormatException() {
		try {
			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower(" ", dimension);
			fail("Should throw InvalidFileFormatException when mower lines is blank");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withNullMowerLine_thenThrowInvalidFileFormatException() {
		try {
			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower(null, dimension);
			fail("Should throw InvalidFileFormatException when mower lines is null");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withNullDimension_thenThrowInvalidFileFormatException() {
		try {
			LawnDimension dimension = null;
			LawnFileParser.parseMower("1 1 S", dimension);
			fail("Should throw InvalidFileFormatException when dimension is null");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withInvalidDirection_thenThrowInvalidFileFormatException() {
		try {
			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower("1 1 Z", dimension);
			fail("Should throw InvalidFileFormatException when ower direction is invalid");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withNumericStringDimensions_thenReturnLawnDimension() {
		final String mowerLine = "1 1 S";
		LawnDimension dimension = LawnDimension.of(1, 1);

		Movable mower = LawnFileParser.parseMower(mowerLine, dimension);

		assertEquals(1, dimension.getX());
		assertEquals(1, dimension.getY());
		assertEquals(DirectionEnum.S, mower.getDirection());
	}

	@Test
	public void givenParseMovements_withNullLine_thenThrowInvalidFileFormatException() {
		try {
			LawnFileParser.parseMovements(null);
			fail("Should throw InvalidFileFormatException when movement line is null");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withEmptyLine_thenThrowInvalidFileFormatException() {
		try {
			LawnFileParser.parseMovements("");
			fail("Should throw InvalidFileFormatException when movement line is empty");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withBlankLine_thenThrowInvalidFileFormatException() {
		try {
			LawnFileParser.parseMovements(" ");
			fail("Should throw InvalidFileFormatException when movement line is blank");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withAlphaStringLine_thenInvalidFileFormatException() {
		try {
			LawnFileParser.parseMovements("A B");
			fail("Should throw InvalidFileFormatException when movement line are invalid");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withInvalidDirection_thenThrowInvalidFileFormatException() {
		try {
			LawnFileParser.parseMovements("1 1 Z");
			fail("Should throw InvalidFileFormatException when movement line are invalid");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withValidMovements_thenReturnMovementList() {
		final String movementsLine = "LFR";
		final List<MovementEnum> expected = Arrays.asList(MovementEnum.L, MovementEnum.F, MovementEnum.R);

		List<MovementEnum> result = LawnFileParser.parseMovements(movementsLine);

		assertEquals(expected, result);
	}

}
