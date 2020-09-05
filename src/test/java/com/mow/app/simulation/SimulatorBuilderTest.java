package com.mow.app.simulation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mow.app.exception.InvalidFileFormatException;

public class SimulatorBuilderTest {

	@Test
	public void givenBuildSimulation_withNullLines_thenReturnNull() throws InvalidFileFormatException {
		SimulatorBuilder builder = new SimulatorBuilder();

		Simulator result = builder.buildSimulation(null);

		assertNull(result);

	}

	@Test
	public void givenBuildSimulation_withEmptyLineList_thenReturnNull() throws InvalidFileFormatException {
		SimulatorBuilder builder = new SimulatorBuilder();

		Simulator result = builder.buildSimulation(new ArrayList<>());

		assertNull(result);

	}

	@Test
	public void givenBuildSimulation_withLineList_thenReturnSimulator() throws InvalidFileFormatException {
		SimulatorBuilder builder = new SimulatorBuilder();
		List<String> lines = Arrays.asList("5 5", "1 2 N", "LFLFLFLFF", "3 3 E", "FFRFFRFRRF");

		Simulator result = builder.buildSimulation(lines);

		assertNotNull(result);
	}

	@Test
	public void givenBuildSimulation_withInvalidInitialPositions_thenThrowInvalidLawnException()
			throws InvalidFileFormatException {
		try {
			SimulatorBuilder builder = new SimulatorBuilder();
			List<String> lines = Arrays.asList("5 5", "1 1 N", "LFLFLFLFF", "1 1 E", "FFRFFRFRRF");

			builder.buildSimulation(lines);
			fail("Should throw InvalidFileFormatException when the initial position of the mowers overlap");
		} catch (InvalidFileFormatException e) {
			// success
		}
	}

}
