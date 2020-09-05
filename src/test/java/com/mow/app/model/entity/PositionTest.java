package com.mow.app.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.model.Position;
import com.mow.app.model.Vector;

public class PositionTest {

	@Test
	public void givenOf_withNegativeXPosition_thenThrowIllegalArgumentException() {
		try {
			Position.of(-1, 1);

			fail("Should throw IllegalArgumentException when coordinates are negative");
		} catch (IllegalArgumentException e) {
			// success
		}
	}

	@Test
	public void givenOf_withNegativeYPosition_thenThrowIllegalArgumentException() {
		try {
			Position.of(1, -1);

			fail("Should throw IllegalArgumentException when coordinates are negative");
		} catch (IllegalArgumentException e) {
			// success
		}
	}

	@Test
	public void givenMove_withNullDirection_thenDoNotMove() {
		Position position = Position.of(0, 0);
		DirectionEnum direction = null;
		Vector vector = LawnDimension.of(1, 1);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(0, position.getY());

	}

	@Test
	public void givenMove_withEastDirection_andNoLawnBoundary_thenMoveXcoordinate() {
		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.E;
		Vector vector = LawnDimension.of(1, 1);

		position.move(direction, vector);

		assertEquals(1, position.getX());
		assertEquals(0, position.getY());

	}

	@Test
	public void givenMove_withEastDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.E;
		Vector vector = LawnDimension.of(0, 0);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(0, position.getY());

	}

	@Test
	public void givenMove_withNorthDirection_andNoLawnBoundary_thenMoveYcoordinate() {
		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.N;
		Vector vector = LawnDimension.of(1, 1);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(1, position.getY());

	}

	@Test
	public void givenMove_withNorthDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.N;
		Vector vector = LawnDimension.of(0, 0);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(0, position.getY());

	}

	@Test
	public void givenMove_withWestDirection_andNoLawnBoundary_thenMoveXcoordinate() {
		Position position = Position.of(1, 1);
		DirectionEnum direction = DirectionEnum.W;
		Vector vector = LawnDimension.of(2, 2);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(1, position.getY());

	}

	@Test
	public void givenMove_withWestDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(0, 1);
		DirectionEnum direction = DirectionEnum.W;
		Vector vector = LawnDimension.of(1, 1);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(1, position.getY());

	}

	@Test
	public void givenMove_withSouthDirection_andNoLawnBoundary_thenMoveYcoordinate() {
		Position position = Position.of(0, 1);
		DirectionEnum direction = DirectionEnum.S;
		Vector vector = LawnDimension.of(2, 2);

		position.move(direction, vector);

		assertEquals(0, position.getX());
		assertEquals(0, position.getY());

	}

	@Test
	public void givenMove_withSouthDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(1, 0);
		DirectionEnum direction = DirectionEnum.S;
		Vector vector = LawnDimension.of(1, 1);

		position.move(direction, vector);

		assertEquals(1, position.getX());
		assertEquals(0, position.getY());

	}

	@Test
	public void givenGetNextPosition_withNullDirection_thenReturnCurrentPosition() {

		Position position = Position.of(1, 0);
		DirectionEnum direction = null;
		Vector vector = LawnDimension.of(1, 1);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(position, result);

	}

	@Test
	public void givenGetNextPosition_withEastDirection_andNoLawnBoundary_thenMoveXcoordinate() {
		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.E;
		Vector vector = LawnDimension.of(1, 1);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(1, result.getX());
		assertEquals(0, result.getY());

	}

	@Test
	public void givenGetNextPosition_withEastDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.E;
		Vector vector = LawnDimension.of(0, 0);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(0, result.getX());
		assertEquals(0, result.getY());

	}

	@Test
	public void givenGetNextPosition_withNorthDirection_andNoLawnBoundary_thenMoveYcoordinate() {
		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.N;
		Vector vector = LawnDimension.of(1, 1);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(0, result.getX());
		assertEquals(1, result.getY());

	}

	@Test
	public void givenGetNextPosition_withNorthDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(0, 0);
		DirectionEnum direction = DirectionEnum.N;
		Vector vector = LawnDimension.of(0, 0);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(0, result.getX());
		assertEquals(0, result.getY());

	}

	@Test
	public void givenGetNextPosition_withWestDirection_andNoLawnBoundary_thenMoveXcoordinate() {
		Position position = Position.of(1, 1);
		DirectionEnum direction = DirectionEnum.W;
		Vector vector = LawnDimension.of(2, 2);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(0, result.getX());
		assertEquals(1, result.getY());

	}

	@Test
	public void givenGetNextPosition_withWestDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(0, 1);
		DirectionEnum direction = DirectionEnum.W;
		Vector vector = LawnDimension.of(1, 1);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(0, result.getX());
		assertEquals(1, result.getY());

	}

	@Test
	public void givenGetNextPosition_withSouthDirection_andNoLawnBoundary_thenMoveYcoordinate() {
		Position position = Position.of(0, 1);
		DirectionEnum direction = DirectionEnum.S;
		Vector vector = LawnDimension.of(2, 2);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(0, result.getX());
		assertEquals(0, result.getY());

	}

	@Test
	public void givenGetNextPosition_withSouthDirection_andLawnBoundaryPosition_thenReturnSamePosition() {

		Position position = Position.of(1, 0);
		DirectionEnum direction = DirectionEnum.S;
		Vector vector = LawnDimension.of(1, 1);

		Position result = position.getNextPosition(direction, vector);

		assertEquals(1, result.getX());
		assertEquals(0, result.getY());

	}

	@Test
	public void givenEquals_withSameCoordinates_thenReturnTrue() {
		Position randomPosition = Position.of(1, 0);
		Position sameRandomPosition = Position.of(1, 0);

		assertTrue(randomPosition.equals(sameRandomPosition));
	}

	@Test
	public void givenEquals_withNullObject_thenReturnFalse() {
		Position randomPosition = Position.of(1, 0);

		assertFalse(randomPosition.equals(null));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void givenEquals_withOtherClassType_thenReturnFalse() {
		Position randomPosition = Position.of(1, 0);

		assertFalse(randomPosition.equals(new PositionTest()));
	}

}
