package com.mow.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.simulation.SimulatorBuilder;

public class AppIntegrationTest {

	@BeforeEach
	void beforeEachTest() {
		System.out.println("Before Each Test");
	}

	@AfterEach
	void afterEachTest() {
		System.out.println("After Each Test");
		System.out.println("=====================");
	}

	@RepeatedTest(500)
	public void givenMain_withValidFile_thenVerifyOutput()
			throws InvalidFileFormatException, URISyntaxException, MalformedURLException {

		final TestAppender appender = new TestAppender();

		final Logger logger = Logger.getRootLogger();

		logger.addAppender(appender);

		try {

			App app = new App(new SimulatorBuilder());

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("lawn.txt").getFile());

			app.run(file.toURI().toString());
		}

		finally {
			logger.removeAppender(appender);
		}

		boolean isFirstConditionSuccess = false;
		boolean isSecondConditionSuccess = false;

		final List<LoggingEvent> log = appender.getLog();

		// Expected conditions
		final String mower1Expected = "1 3 N";
		final String mower2Expected = "5 1 E";

		for (LoggingEvent loggingEvent : log) {
			final String message = (String) loggingEvent.getMessage();

			if (message.contains(mower1Expected)) {
				isFirstConditionSuccess = true;
			} else if (message.contains(mower2Expected)) {
				isSecondConditionSuccess = true;
			}
		}

		assertTrue(isFirstConditionSuccess && isSecondConditionSuccess);
	}

	@Test
	public void givenMain_withOverlappingInitialPosition_thenThrowInvalidFileException()
			throws InvalidFileFormatException {

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("lawn-invalid.txt").getFile());

			App app = new App(new SimulatorBuilder());
			app.run(file.toURI().toString());

			fail("Should throw InvalidFileFormatException when running app with invalid path");
		} catch (InvalidFileFormatException e) {
			// success
		}

	}

}

/**
 * Utility class to intercept test case logger and verify output
 * 
 * @author enrique.rolon
 *
 */
class TestAppender extends AppenderSkeleton {

	private final List<LoggingEvent> log = new ArrayList<LoggingEvent>();

	@Override

	public boolean requiresLayout() {
		return false;

	}

	@Override
	protected void append(final LoggingEvent loggingEvent) {

		this.log.add(loggingEvent);

	}

	@Override
	public void close() {

	}

	public List<LoggingEvent> getLog() {

		return new ArrayList<LoggingEvent>(this.log);

	}

}