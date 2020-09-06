package com.mow.app.parser;

import static com.mow.app.parser.LawnFileValidator.validateLawnDimension;
import static com.mow.app.parser.LawnFileValidator.validateMower;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.enums.MovementEnum;
import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.model.behaviour.Movable;
import com.mow.app.model.entity.LawnDimension;
import com.mow.app.model.entity.Mower;;

/**
 * Class to parse input file.
 * 
 * It could parse: <br>
 * LawnDimension <br>
 * Movable mowers <br>
 * MovementEnum <br>
 * 
 * @author enrique.rolon
 *
 */
public class LawnFileParser {

	/**
	 * Only static method access
	 */
	private LawnFileParser() {
	}

	/**
	 * Create LawnDimension object given the string line. Example: <br>
	 * "5 5"
	 * 
	 * @param lawnDimensionLine
	 * @return LawnDimension with the given coordinates as dimension
	 */
	public static LawnDimension parseDimension(final String lawnDimensionLine) {

		if (lawnDimensionLine == null || lawnDimensionLine.isBlank()) {
			throw new InvalidFileFormatException("Dimensions line should not be null, empty or blank string");
		}

		String[] tokens = lawnDimensionLine.split(" ");

		try {

			validateLawnDimension(tokens);

			return LawnDimension.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

		} catch (NumberFormatException ex) {
			throw new InvalidFileFormatException("Could not parse given alphanumeric string");
		}
	}

	/**
	 * Parse the given mowerLine and validate its position given the lawn dimensions
	 * 
	 * @param mowerLine Example: "3 3 S" representing X: Y:3 Direction: South
	 * @param dimension of the lawn
	 * @return movable mower
	 */
	public static Movable parseMower(final String mowerLine, final LawnDimension dimension) {

		if (mowerLine == null || dimension == null || mowerLine.isBlank()) {
			throw new InvalidFileFormatException("Dimensions line should not be null, empty or blank string");
		}

		String[] tokens = mowerLine.split(" ");

		try {

			validateMower(tokens, dimension);
			Mower mower = Mower.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
					DirectionEnum.valueOf(tokens[2]));

			return mower;

		} catch (IllegalArgumentException ex) {
			throw new InvalidFileFormatException("Could not parse given mower line or invalid initial positions");
		}
	}

	/**
	 * Parse the given movement lines
	 * 
	 * @param movementsLine Example: "RLF"
	 * @return the List of movements
	 */
	public static List<MovementEnum> parseMovements(final String movementsLine) {

		if (movementsLine == null || movementsLine.isBlank()) {
			throw new InvalidFileFormatException("Movements line should not be null, empty or blank string");
		}

		String[] tokens = movementsLine.split("");

		try {

			return Stream.of(tokens).map(MovementEnum::valueOf).collect(Collectors.toList());

		} catch (IllegalArgumentException ex) {
			throw new InvalidFileFormatException("Could not parse given movement line");
		}
	}

}
