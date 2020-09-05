package com.mow.app.model;

/**
 * Class representing two coordinates X and Y starting from bottom left corner
 * as origin
 * 
 * @author enrique.rolon
 *
 */
public class Vector {

	private int x;
	private int y;

	protected Vector(final int x, final int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Members of the class Vector must be positive");
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		return x * 31 + y;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		return other.getX() == this.getX() && other.getY() == this.getY();
	}

}
