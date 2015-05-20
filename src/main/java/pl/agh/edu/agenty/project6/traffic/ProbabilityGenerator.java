package pl.agh.edu.agenty.project6.traffic;

import java.util.Random;

public class ProbabilityGenerator {
	private static final int RANDOM_TRIGERRING = 1;
	private static final int RANDOM_MAX = 3;
	private static Random random;
	
	public static void setUp() {
		random = new Random();
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
