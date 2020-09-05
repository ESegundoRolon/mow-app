package com.mow.app.model.behaviour;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.model.Position;
import com.mow.app.model.entity.Lawn;

/**
 * Interface describing the movable behaviour. <br>
 * The possible actions that the movable behaviour allows are: <br>
 * turnLeft 90 degrees <br>
 * turnRight 90 degrees <br>
 * moveForward on the given Lawn 1 spatial unit <br>
 * 
 * Also it is possible to calculate the next position and get current direction
 * and position
 * 
 * @author enrique.rolon
 *
 */
public interface Movable {

	/**
	 * Turns the movable 90° left
	 */
	void turnLeft();

	/**
	 * Turns the movable 90° right
	 */
	void turnRight();

	/**
	 * Tries to move this movable object on the given lawn <br>
	 * 
	 * @param lawn Lawn to move the movable on
	 */
	void moveForward(final Lawn lawn);

	/**
	 * Gives the next calculated position of the movable on the given lwn <br>
	 * 
	 * @param lawn Lawn to move the movable on
	 * @return Position position representing the calculated position. Could be the
	 *         same current position if it is not possible to move because of the
	 *         boundary limits
	 */
	Position getNextPosition(final Lawn lawn);

	/**
	 * Gives the current movable position
	 * 
	 * @return Position position
	 */
	Position getPosition();

	/**
	 * Gives the current movable direction
	 * 
	 * @return DirectionEnum direction
	 */
	DirectionEnum getDirection();
}
