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

import com.mow.app.exception.InvalidFileException;
import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.simulation.Simulator;
import com.mow.app.simulation.SimulatorBuilder;

public class App {

	private final static Logger LOGGER = Logger.getLogger(App.class);

	private final SimulatorBuilder builder;
	private static final String DEFAULT_FILE = "lawn.txt";
	private static final String ALLOWED_EXTENSION = "txt";

	App(final SimulatorBuilder builder) {
		this.builder = builder;
	}

	public void run(final String path) throws InvalidFileException {

		LOGGER.info("Starting app with path: " + path);

		// Create initially the URL with the default file
		URL systemResource = ClassLoader.getSystemResource(DEFAULT_FILE);
		URI uri = URI.create(systemResource.toString());
		LOGGER.info("Using path input: " + systemResource.toString());

		// if there is filePath as input, then override current URL
		if (path != null && !path.isBlank()) {

			// verify file extension
			if (path.length() > 2) {

				String extension = path.substring(path.lastIndexOf(".") + 1);
				if (!ALLOWED_EXTENSION.equals(extension)) {
					throw new InvalidFileException("File extension should be txt");
				}

			}
			try {

				uri = URI.create(path);

			} catch (InvalidPathException ex) {

				LOGGER.error("Invalid input path of file: " + path);
				throw new InvalidFileException("Input path could not be found or has invalid format", ex);
			}
		}

		// open file system to access file
		FileSystem fs = null;
		try {

			fs = FileSystems.newFileSystem(uri, Collections.emptyMap());

		} catch (IOException ex) {

			LOGGER.error("Error opening file system");

		} catch (IllegalArgumentException ex) {
			// it could fail on some OS, then return default fs
			fs = FileSystems.getDefault();
		}

		// create path from normalised URI
		Path systemPath = Paths.get(uri);

		// iterate each file lines
		try (Stream<String> lines = Files.lines(systemPath)) {

			Simulator simulation = this.builder.buildSimulation(lines.collect(Collectors.toList()));
			simulation.startSimulation();

		} catch (IOException ex) {

			LOGGER.error("Error getting lines from file resource", ex);
			throw new InvalidFileException("Could not parse lines of given file", ex);

		} catch (UnsupportedOperationException ex) {
			LOGGER.warn("Error closing file system");
		} finally {
			try {
				// once the simulation finish or crashed, close fs
				if (fs != null) {
					fs.close();
				}
			} catch (IOException | UnsupportedOperationException ex) {
				// the fs close could fail on test environments
				LOGGER.warn("Error closing file system");
			}
		}
	}

	/**
	 * Main program
	 * 
	 * @param args with optional path
	 * @throws InvalidFileFormatException
	 * @throws InvalidFileException
	 */
	public static void main(final String[] args) throws InvalidFileException {

		String filePath = null;
		if (args.length > 0) {
			filePath = args[0];
		}

		new App(new SimulatorBuilder()).run(filePath);
	}

}
