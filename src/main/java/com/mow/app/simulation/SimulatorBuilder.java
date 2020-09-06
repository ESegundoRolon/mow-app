package com.mow.app.simulation;

import static com.mow.app.parser.LawnFileParser.parseDimension;
import static com.mow.app.parser.LawnFileParser.parseMovements;
import static com.mow.app.parser.LawnFileParser.parseMower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.mow.app.enums.MovementEnum;
import com.mow.app.exception.BusinessException;
import com.mow.app.exception.InvalidFileFormatException;
import com.mow.app.model.behaviour.Movable;
import com.mow.app.model.entity.Lawn;
import com.mow.app.model.entity.LawnDimension;

public class SimulatorBuilder {

	/**
	 * Method to create simulator from parsed lines list.<br>
	 * 
	 * In order to work the lines from the array list should be at list as follows
	 * <br>
	 * 
	 * First element: "X Y" representing X dimension and Y dimension of the lawn<br>
	 * Second element: "X Y D" representing X position, Y position and D direction
	 * from a mower<br>
	 * Third element: "LRF" representing the movements of the given mower, such as
	 * Left, Right, Forward<br>
	 * 
	 * The second and third element could be repeated many times as desired.
	 * 
	 * @param lines
	 * @return Simulator object if all validations are passed and it allows run the
	 *         simulation
	 * @throws InvalidFileFormatException if there are overlaps between the mowers
	 */
	public Simulator buildSimulation(final List<String> lines) {

		// Do not create simulator if something went wrong parsing
		if (lines == null || lines.isEmpty()) {
			return null;
		}

		// First element represents the lawn dimensions X Y. Example 5 5
		// RuntimeException is raised to handle invalid integer values
		LawnDimension dimension = parseDimension(lines.get(0));

		// Initialise lists
		List<Movable> movables = new ArrayList<>();
		List<Worker> workers = new ArrayList<>();

		// Initialise lawn with mutable movable list
		Lawn lawn = Lawn.of(dimension, movables);

		// Loop through remaining array lines. Remember that the first line was already
		// parsed and it corresponds to lawn dimensions.
		// Each two lines there is a new mower
		for (int i = 1; i < lines.size(); i += 2) {

			// Create movable object and pass the dimension of the lawn to verify the
			// current input position
			Movable movable = parseMower(lines.get(i), dimension);

			// Check if any movable object has same position
			if (movables.stream().anyMatch(m -> m.getPosition().equals(movable.getPosition()))) {
				throw new BusinessException("Mowers should not be overlapping");
			}

			// Second line of the pair iteration corresponds to the movements
			List<MovementEnum> movements = parseMovements(lines.get(i + 1));

			// Create consumer referencing the given lawn object only when moving forward
			List<Consumer<Movable>> executionConsumers = movements.stream().<Consumer<Movable>>map(movement -> {
				switch (movement) {
				case L:
					return Movable::turnLeft;
				case R:
					return Movable::turnRight;
				case F:
					return (m -> m.moveForward(lawn));
				default:
					return null;
				}
			}).collect(Collectors.toList());

			// Add movable to the list, the movable list of the lawn will be updated by
			// reference
			movables.add(movable);

			// Create worker thread object
			workers.add(Worker.of(movable, executionConsumers));
		}

		return Simulator.of(workers);
	}

}
