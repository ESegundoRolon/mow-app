package com.mow.app.simulation;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.model.behaviour.Movable;
import com.mow.app.model.entity.Mower;

public class SimulatorTest {

	@Test
	public void givenStartSimulation_withWorkers_thenSimulate() {
		List<Consumer<Movable>> program = new ArrayList<>();
		Mower mower = Mower.of(1, 1, DirectionEnum.E);
		Worker worker = Worker.of(mower, program);

		Simulator simulator = Simulator.of(Arrays.asList(worker));

		try {
			simulator.startSimulation();
		} catch (Exception e) {
			fail("Should not fail when simulating with workers");
		}
		// success

	}

}
