package com.mow.app.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.enums.MovementEnum;
import com.mow.app.exception.InvalidLawnException;
import com.mow.app.model.behaviour.Movable;
import com.mow.app.model.entity.LawnDimension;

public class LawnFileParserTest {

	@Test
	public void givenParseDimension_withNullDimensionLine_thenThrowInvalidLawnException() {

		try {
			LawnFileParser.parseDimension(null);

			fail("Should throw InvalidLawnException when dimension lines are null");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withEmptyDimensionLine_thenThrowInvalidLawnException() {

		try {
			LawnFileParser.parseDimension("");

			fail("Should throw InvalidLawnException when dimension lines are empty");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withBlankDimensionLine_thenThrowInvalidLawnException() {
		try {
			LawnFileParser.parseDimension(" ");

			fail("Should throw InvalidLawnException when dimension lines are blank");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withAlphaStringDimensions_thenThrowInvalidLawnException() {

		try {
			LawnFileParser.parseDimension("A B");

			fail("Should throw InvalidLawnException when dimension lines are not numbers");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseDimension_withNumericStringDimensions_theneturnLawnDimension() {
		LawnDimension dimension = LawnFileParser.parseDimension("1 1");

		assertEquals(1, dimension.getX());
		assertEquals(1, dimension.getY());
	}

	@Test
	public void givenParseMower_withEmptyMowerLine_thenThrowInvalidLawnException() {
		try {

			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower("", dimension);

			fail("Should throw InvalidLawnException when mower lines is empty");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withBlankMowerLine_thenThrowInvalidLawnException() {
		try {
			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower(" ", dimension);
			fail("Should throw InvalidLawnException when mower lines is blank");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withNullMowerLine_thenThrowInvalidLawnException() {
		try {
			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower(null, dimension);
			fail("Should throw InvalidLawnException when mower lines is null");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withNullDimension_thenThrowInvalidLawnException() {
		try {
			LawnDimension dimension = null;
			LawnFileParser.parseMower("1 1 S", dimension);
			fail("Should throw InvalidLawnException when dimension is null");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withInvalidDirection_thenThrowInvalidLawnException() {
		try {
			LawnDimension dimension = LawnDimension.of(1, 1);
			LawnFileParser.parseMower("1 1 Z", dimension);
			fail("Should throw InvalidLawnException when ower direction is invalid");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMower_withNumericStringDimensions_theneturnLawnDimension() {
		final String mowerLine = "1 1 S";
		LawnDimension dimension = LawnDimension.of(1, 1);

		Movable mower = LawnFileParser.parseMower(mowerLine, dimension);

		assertEquals(1, dimension.getX());
		assertEquals(1, dimension.getY());
		assertEquals(DirectionEnum.S, mower.getDirection());
	}

	@Test
	public void givenParseMovements_withNullLine_thenThrowInvalidLawnException() {
		try {
			LawnFileParser.parseMovements(null);
			fail("Should throw InvalidLawnException when movement line is null");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withEmptyLine_thenThrowInvalidLawnException() {
		try {
			LawnFileParser.parseMovements("");
			fail("Should throw InvalidLawnException when movement line is empty");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withBlankLine_thenThrowInvalidLawnException() {
		try {
			LawnFileParser.parseMovements(" ");
			fail("Should throw InvalidLawnException when movement line is blank");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withAlphaStringLine_thenThrowInvalidLawnException() {
		try {
			LawnFileParser.parseMovements("A B");
			fail("Should throw InvalidLawnException when movement line are invalid");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withInvalidDirection_thenThrowInvalidLawnException() {
		try {
			LawnFileParser.parseMovements("1 1 Z");
			fail("Should throw InvalidLawnException when movement line are invalid");
		} catch (InvalidLawnException e) {
			// success
		}
	}

	@Test
	public void givenParseMovements_withValidMovements_theneturnMovementList() {
		final String movementsLine = "LFR";
		final List<MovementEnum> expected = Arrays.asList(MovementEnum.L, MovementEnum.F, MovementEnum.R);

		List<MovementEnum> result = LawnFileParser.parseMovements(movementsLine);

		assertEquals(expected, result);
	}

}
