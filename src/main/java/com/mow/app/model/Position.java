package com.mow.app.model;

import com.mow.app.enums.DirectionEnum;

/**
 * Class representing a position through X and Y coordinates
 * 
 * @author enrique.rolon
 *
 */
public class Position extends Vector {

	private Position(final int x, final int y) {
		super(x, y);
	}

	public static Position of(final int x, final int y) {
		return new Position(x, y);
	}

	/**
	 * Move the current position one spatial unity trough the given direction and
	 * vector
	 * 
	 * @param direction  to move the current position
	 * @param boundaries the X and Y coordinates representing the limits
	 */
	public void move(final DirectionEnum direction, final Vector boundaries) {
		if (direction != null) {
			switch (direction) {
			case E:
				setX(Math.min(getX() + 1, boundaries.getX()));
				break;
			case N:
				setY(Math.min(getY() + 1, boundaries.getY()));
				break;
			case W:
				setX(Math.max(getX() - 1, 0));
				break;
			case S:
				setY(Math.max(getY() - 1, 0));
				break;
			}
		}
	}

	/**
	 * Calculate the next position for the given direction and boundaries
	 * 
	 * @param direction  to move the current position
	 * @param boundaries the X and Y coordinates representing the limits
	 * @return the next calculated position
	 */
	public Position getNextPosition(final DirectionEnum direction, final Vector boundaries) {
		if (direction != null) {
			switch (direction) {
			case E:
				return Position.of(Math.min(getX() + 1, boundaries.getX()), getY());

			case N:
				return Position.of(getX(), Math.min(getY() + 1, boundaries.getY()));

			case W:
				return Position.of(Math.max(getX() - 1, 0), getY());

			case S:
				return Position.of(getX(), Math.max(getY() - 1, 0));
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return getX() + " " + getY();
	}

}
