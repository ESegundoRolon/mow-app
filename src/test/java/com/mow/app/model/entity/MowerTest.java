package com.mow.app.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.model.Position;

public class MowerTest {

	@Test
	public void givenOf_withNegativeXPosition_thenThrowIllegalArgumentException() {
		try {
			Mower.of(-1, 1, DirectionEnum.E);

			fail("Should throw IllegalArgumentException when coordinates are negative");
		} catch (IllegalArgumentException e) {
			// success
		}
	}

	@Test
	public void givenOf_withNegativeYPosition_thenThrowIllegalArgumentException() {
		try {
			Mower.of(1, -1, DirectionEnum.E);

			fail("Should throw IllegalArgumentException when coordinates are negative");
		} catch (IllegalArgumentException e) {
			// success
		}
	}

	@Test
	public void givenTurnLeft_withEastAsCurrentDirection_thenReturnNorthDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.E);

		mower.turnLeft();

		assertEquals(DirectionEnum.N, mower.getDirection());

	}

	@Test
	public void givenTurnLeft_withNorthAsCurrentDirection_thenReturnWestDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.N);

		mower.turnLeft();

		assertEquals(DirectionEnum.W, mower.getDirection());

	}

	@Test
	public void givenTurnLeft_withSouthAsCurrentDirection_thenReturnEastDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.S);

		mower.turnLeft();

		assertEquals(DirectionEnum.E, mower.getDirection());

	}

	@Test
	public void givenTurnLeft_withWestAsCurrentDirection_thenReturnSouthDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.W);

		mower.turnLeft();

		assertEquals(DirectionEnum.S, mower.getDirection());

	}

	@Test
	public void givenTurnRight_withEastAsCurrentDirection_thenReturnSouthDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.E);

		mower.turnRight();

		assertEquals(DirectionEnum.S, mower.getDirection());

	}

	@Test
	public void givenTurnRight_withNorthAsCurrentDirection_thenReturnEastDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.N);

		mower.turnRight();

		assertEquals(DirectionEnum.E, mower.getDirection());

	}

	@Test
	public void givenTurnRight_withSouthAsCurrentDirection_thenReturnWestDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.S);

		mower.turnRight();

		assertEquals(DirectionEnum.W, mower.getDirection());

	}

	@Test
	public void givenTurnRight_withWestAsCurrentDirection_thenReturnNorthDirection() {
		Mower mower = Mower.of(0, 0, DirectionEnum.W);

		mower.turnRight();

		assertEquals(DirectionEnum.N, mower.getDirection());

	}

	@Test
	public void givenMoveForward_withNextPositionAllowed_thenReturnNextPositionAsCurrent() {
		Mower mower = Mower.of(5, 5, DirectionEnum.W);
		Lawn lawn = Lawn.of(LawnDimension.of(5, 5), Collections.singletonList(mower));
		Position nextPosition = mower.getNextPosition(lawn);

		mower.moveForward(lawn);

		assertEquals(nextPosition, mower.getPosition());

	}

	@Test
	public void givenMoveForward_withNextPositionNotAllowed_thenReturnSamePosition() {
		Mower mower = Mower.of(5, 5, DirectionEnum.W);
		Mower mower2 = Mower.of(4, 5, DirectionEnum.W);
		Lawn lawn = Lawn.of(LawnDimension.of(5, 5), Arrays.asList(mower, mower2));
		// create new position object detached from current position
		Position currentPosition = Position.of(mower.getPosition().getX(), mower.getPosition().getY());

		mower.moveForward(lawn);

		assertEquals(currentPosition, mower.getPosition());

	}

	@Test
	public void givenMoveForward_withDirection_thenReturnNewPosition() {
		Mower mower = Mower.of(5, 5, DirectionEnum.W);
		Position expected = Position.of(4, 5);

		Lawn lawn = Lawn.of(LawnDimension.of(5, 5), Arrays.asList(mower));

		Position nextPosition = mower.getNextPosition(lawn);

		assertEquals(expected, nextPosition);

	}

	@Test
	public void givenToString_thenReturnString() {
		final String expected = "1 1 S";
		Mower mower = Mower.of(1, 1, DirectionEnum.S);

		final String result = mower.toString();

		assertEquals(expected, result);
	}

}
