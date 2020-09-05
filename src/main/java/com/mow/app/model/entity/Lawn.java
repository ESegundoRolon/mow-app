package com.mow.app.model.entity;

import java.util.Collections;
import java.util.List;

import com.mow.app.model.Position;
import com.mow.app.model.behaviour.Movable;

/**
 * Class representing lawn object
 * 
 * The lawn has a given dimension and movable objects on it
 * 
 * @author enrique.rolon
 *
 */
public class Lawn {

	private final LawnDimension lawnDimension;
	private final List<Movable> movables;

	private Lawn(final int x, final int y, final List<Movable> movables) {
		this.lawnDimension = LawnDimension.of(x, y);
		// Returns a synchronized (thread-safe) list backed by the specified list. In
		// order to guarantee serial access, it is critical that all access to the
		// backing list is accomplished through the returned list.
		this.movables = Collections.synchronizedList(movables);
	}

	public static Lawn of(final LawnDimension size, final List<Movable> movables) {
		return new Lawn(size.getX(), size.getY(), movables);
	}

	public LawnDimension getLawnDimension() {
		return this.lawnDimension;
	}

	/**
	 * Allows (or not) a mower to move to its next position
	 * 
	 * @param mower Mower that wants to move on this Lawn
	 * @return true if the mower can move false otherwise
	 */
	public boolean allowsNextPosition(final Position currentPosition, final Position nextPosition) {

		// It is imperative to manually synchronize on the returned list when
		// iterating over it:
		synchronized (this.movables) {
			return this.movables //
					.stream() //
					.map(Movable::getPosition) //
					.filter(m -> !m.equals(currentPosition)) // filter current movable, there are not two movable into
																// the same position
					.noneMatch(m -> m.equals(nextPosition)); // if next position will collide with another movable
																// object, then return not allowed to move
		}
	}

}
