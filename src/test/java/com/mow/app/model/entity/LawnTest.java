package com.mow.app.model.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.model.Position;

public class LawnTest {

	@Test
	public void givenAllowsNextPosition_withOccupedNextPosition_thenReturnFalse() {
		Mower mower = Mower.of(5, 5, DirectionEnum.W);
		Mower mower2 = Mower.of(4, 5, DirectionEnum.W);
		Lawn lawn = Lawn.of(LawnDimension.of(5, 5), Arrays.asList(mower, mower2));
		// current position of first mower is upper right corner
		Position currentPosition = mower.getPosition();
		// next position moving the mower to the west, will be 4 5 and there it the
		// mower 2 chilling in there
		Position nextPosition = currentPosition.getNextPosition(DirectionEnum.W, lawn.getLawnDimension());

		boolean result = lawn.allowsNextPosition(currentPosition, nextPosition);

		assertFalse(result);
	}

	@Test
	public void givenAllowsNextPosition_withFreeNextPosition_thenReturnTrue() {
		Mower mower = Mower.of(5, 5, DirectionEnum.W);
		Mower mower2 = Mower.of(4, 5, DirectionEnum.W);
		Lawn lawn = Lawn.of(LawnDimension.of(5, 5), Arrays.asList(mower, mower2));
		// current position of first mower is upper right corner
		Position currentPosition = mower.getPosition();
		// next position moving the mower to the south, will be 5 4 and there is no one
		// in there so it should be allowed to move
		Position nextPosition = currentPosition.getNextPosition(DirectionEnum.S, lawn.getLawnDimension());

		boolean result = lawn.allowsNextPosition(currentPosition, nextPosition);

		assertTrue(result);
	}

}
