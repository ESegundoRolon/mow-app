package com.mow.app.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.mow.app.enums.DirectionEnum;
import com.mow.app.enums.MovementEnum;
import com.mow.app.model.behaviour.Movable;
import com.mow.app.simulation.Worker;

public class WorkerTest {

	@Test
	public void givenRun_withEmptyConsumerList_thenMovableStaysFix() throws Exception {
		List<Consumer<Movable>> executionConsumers = new ArrayList<>();
		Mower mower = Mower.of(1, 1, DirectionEnum.E);

		Worker worker = Worker.of(mower, executionConsumers);
		worker.run();

		// No consumer so it should not move
		Movable result = worker.getMovable();

		assertEquals(1, result.getPosition().getX());
		assertEquals(1, result.getPosition().getY());
		assertEquals(DirectionEnum.E, result.getDirection());
	}

	@Test
	public void givenRun_withConsumerList_thenVerifyMovable() throws Exception {
		List<MovementEnum> movements = Arrays.asList(MovementEnum.F, MovementEnum.L, MovementEnum.R);
		Mower mower = Mower.of(1, 1, DirectionEnum.E);
		Lawn lawn = Lawn.of(LawnDimension.of(5, 5), Collections.singletonList(mower));
		List<Consumer<Movable>> executionConsumers = movements.stream().<Consumer<Movable>>map(order -> {
			switch (order) {
			case L:
				return Movable::turnLeft;
			case R:
				return Movable::turnRight;
			case F:
				return (m -> m.moveForward(lawn));
			}
			return null;
		}).collect(Collectors.toList());

		Worker worker = Worker.of(mower, executionConsumers);
		worker.run();

		Movable result = worker.getMovable();

		assertEquals(2, result.getPosition().getX());
		assertEquals(1, result.getPosition().getY());
		assertEquals(DirectionEnum.E, result.getDirection());
	}

}
