package es.keensoft.main.engine;

import es.keensoft.bean.Input;
import es.keensoft.bean.Output;

public class BasicStepEngine {

	public static Output start(Input in) {

		for (int step = 0; step < in.getInputLines().size(); step++) {

			nextStep();

		}

		return new Output();

	}

	private static void nextStep() {

	}

}
