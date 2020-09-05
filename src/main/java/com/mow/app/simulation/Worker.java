package com.mow.app.simulation;

import java.util.List;
import java.util.function.Consumer;

import com.mow.app.model.behaviour.Movable;

/**
 * Class implementing run method to consume the given movable consumer list with
 * the following actions: <br>
 * 
 * turnLeft <br>
 * turnRigth <br>
 * moveForward <br>
 * 
 * Each worker represents the calculation of a single movable object
 * 
 * @author enrique.rolon
 *
 */
public class Worker implements Runnable {

	private final Movable movable;
	private final List<Consumer<Movable>> executionConsumers;

	private Worker(final Movable movable, final List<Consumer<Movable>> executionConsumers) {
		this.movable = movable;
		this.executionConsumers = executionConsumers;
	}

	public static Worker of(final Movable movable, final List<Consumer<Movable>> executionConsumers) {
		return new Worker(movable, executionConsumers);
	}

	public Movable getMovable() {
		return this.movable;
	}

	/**
	 * The consumer list if fulfilled with actions, execute them calling the accept
	 * method
	 */
	@Override
	public void run() {
		for (Consumer<Movable> consumer : this.executionConsumers) {
			consumer.accept(this.movable);
		}
	}

}
