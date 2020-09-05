package com.mow.app.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * Class to compute start and wait completion of given workers
 * 
 * @author enrique.rolon
 *
 */
public class Simulator {

	private final static Logger LOGGER = Logger.getLogger(Simulator.class);
	private static final int MAX_THREADS = 30;
	private final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);;

	private final List<Worker> workers;

	private Simulator(final List<Worker> workers) {
		this.workers = new ArrayList<>(workers);

	}

	public static Simulator of(final List<Worker> workers) {
		return new Simulator(workers);
	}

	/**
	 * Start and wait workers
	 */
	public void startSimulation() {

		LOGGER.info("Starting simulation....");

		for (final Worker worker : workers) {
			// start workers
			this.executor.execute(worker);
		}

		// shutdown in order to refuse any other worker to arrive
		this.executor.shutdown();

		// Wait until all workers are finish
		while (!this.executor.isTerminated()) {

		}
		LOGGER.info("Simulation finished...");

		for (final Worker worker : workers) {
			final String position = worker.getMovable().toString();
			LOGGER.info("Worker id: " + worker.hashCode() + " final computed position: " + position);
		}
	}

}
