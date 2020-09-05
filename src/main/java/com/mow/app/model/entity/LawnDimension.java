package com.mow.app.model.entity;

import com.mow.app.model.Vector;

/**
 * Class representing the lawn dimension as a vector
 * 
 * @author enrique.rolon
 *
 */
public class LawnDimension extends Vector {

	private LawnDimension(int x, int y) {
		super(x, y);
	}

	public static LawnDimension of(int x, int y) {
		return new LawnDimension(x, y);
	}
}
