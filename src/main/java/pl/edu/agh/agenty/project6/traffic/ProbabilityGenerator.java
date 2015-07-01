package pl.edu.agh.agenty.project6.traffic;

import pl.edu.agh.agenty.project6.agents.CarDirection;

import java.util.Random;

public class ProbabilityGenerator {
	private static final int RANDOM_TRIGERRING = 1;
	private static final int RANDOM_MAX = 3;
	private static Random random;
	
	public static void setUp() {
		random = new Random();
	}

    public static double randomDriversBehaviourIndex() {
        return random.nextDouble();
    }

    public static int randomCarLength() {
        return random.nextInt(3) + 1;
    }

    public static boolean addAnotherCar(double triggerValue) {
        return random.nextDouble() < triggerValue;
    }

    public static CarDirection getRandomCarDirection(double inclination) {
        double randVal = random.nextDouble();
        if (randVal < inclination)
            return CarDirection.LEFT;
        else if (randVal > 1 - inclination)
            return CarDirection.RIGHT;
        else
            return CarDirection.STRAIGHT;
    }

	public static int handleChangeProbability(int value) {
		try {
			if (RANDOM_TRIGERRING == random.nextInt(RANDOM_MAX))
				value--;
		} catch (NullPointerException e) {
			System.err.println("Generator not set up!");
		}
		
		return value;
	}

}
