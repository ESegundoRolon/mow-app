package com.mow.app;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.exception.InvalidLawnException;
import com.mow.app.simulation.Simulator;
import com.mow.app.simulation.SimulatorBuilder;

public class App {

	private final static Logger LOGGER = Logger.getLogger(App.class);

	private final SimulatorBuilder builder;
	private static final String DEFAULT_FILE = "lawn.txt";

	App(final SimulatorBuilder builder) {
		this.builder = builder;
	}

	public void run(final String path) throws InvalidFileFormatException {

		LOGGER.info("Starting run with path: " + path);

		// Create initially the URL with the default file
		URL systemResource = ClassLoader.getSystemResource(DEFAULT_FILE);
		URI uri = URI.create(systemResource.toString());
		LOGGER.info("Using input: " + systemResource.toString());

		// if there is filePath as input, then override current URL
		if (path != null && !path.isBlank()) {
			try {

				uri = URI.create(path);

			} catch (InvalidPathException ex) {
				throw new InvalidLawnException("Input path could not be found or has invalid format");
			}
		}

		FileSystem fs = null;
		try {
			fs = FileSystems.newFileSystem(uri, Collections.emptyMap());

		} catch (IOException ex) {
			LOGGER.error("Error opening file system");
		} catch (IllegalArgumentException ex) {
			// return default fs
			fs = FileSystems.getDefault();
		}

		Path systemPath = Paths.get(uri);

		try (Stream<String> lines = Files.lines(systemPath)) {

			Simulator simulation = this.builder.buildSimulation(lines.collect(Collectors.toList()));
			simulation.startSimulation();
			fs.close();
		} catch (IOException ex) {
			LOGGER.error("Error getting lines from file resource", ex);

			throw new InvalidLawnException("Could not parse lines of given file");
		} catch (UnsupportedOperationException ex) {
			LOGGER.error("Error closing file system");
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (IOException | UnsupportedOperationException ex) {
				LOGGER.error("Error closing file system");
			}
		}
	}

	/**
	 * Main program
	 * 
	 * @param args with optional path
	 * @throws InvalidFileFormatException
	 */
	public static void main(final String[] args) throws InvalidFileFormatException {

		String filePath = null;
		if (args.length > 0) {
			filePath = args[0];
		}

		new App(new SimulatorBuilder()).run(filePath);
	}

}
