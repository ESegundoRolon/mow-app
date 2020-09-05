package com.mow.app.model.entity;

import org.apache.log4j.Logger;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.model.Position;
import com.mow.app.model.behaviour.Movable;

/**
 * Class representing mower object with movable behaviour
 * 
 * The mower has a given position and direction
 * 
 * @author enrique.rolon
 *
 */
public class Mower implements Movable {

	private final static Logger LOGGER = Logger.getLogger(Mower.class);

	private final Position position;
	private DirectionEnum direction;
	private static final String POSITION_SEPARATOR = " ";

	private Mower(final Position position, final DirectionEnum direction) {
		LOGGER.info("Creating mower in initial position: " + position + " direction: " + direction);
		this.position = position;
		this.direction = direction;
	}

	public static Mower of(final int x, final int y, final DirectionEnum direction) {
		if (x < 0 || y < 0) {
			LOGGER.error("Error creating mower with negatives position values X: " + x + " Y: " + y);
			throw new IllegalArgumentException("Given coordinates should not be negative integers");
		}
		return new Mower(Position.of(x, y), direction);
	}

	@Override
	public DirectionEnum getDirection() {
		return this.direction;
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void turnLeft() {
		LOGGER.info("Turning left before direction: " + this.direction);
		// abuse the order declaration of enumerator to avoid ugly if else conditions
		int i = this.direction.ordinal() + 1;
		this.direction = DirectionEnum.values()[(i > 3 ? 0 : i)];
		LOGGER.info("Turning left after direction: " + this.direction);
	}

	@Override
	public void turnRight() {
		LOGGER.info("Turning right before direction: " + this.direction);
		// abuse the order declaration of enumerator to avoid ugly if else conditions
		int i = this.direction.ordinal() - 1;
		this.direction = DirectionEnum.values()[(i < 0 ? 3 : i)];
		LOGGER.info("Turning right after direction: " + this.direction);
	}

	@Override
	public void moveForward(final Lawn lawn) {
		LOGGER.info("Moving forward before position: " + this.position);
		// Ask to the given lawn if the next position is free to go given this current
		// position and next calculated position
		if (lawn.allowsNextPosition(this.position, this.getNextPosition(lawn))) {
			// move trough current direction
			this.position.move(this.direction, lawn.getLawnDimension());

		}
		LOGGER.info("Moving forward after position: " + this.position);
	}

	@Override
	public Position getNextPosition(final Lawn lawn) {
		return this.position.getNextPosition(this.direction, lawn.getLawnDimension());
	}

	/**
	 * Use this to match expected output
	 */
	@Override
	public String toString() {
		return position.getX() + POSITION_SEPARATOR + position.getY() + POSITION_SEPARATOR + direction;
	}

}
