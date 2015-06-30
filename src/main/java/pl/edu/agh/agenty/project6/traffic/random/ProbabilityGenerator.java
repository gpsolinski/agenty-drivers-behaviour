package pl.edu.agh.agenty.project6.traffic.random;

import java.util.Random;

import pl.edu.agh.agenty.project6.traffic.entities.Tiredness;
import pl.edu.agh.agenty.project6.traffic.entities.Turn;

public class ProbabilityGenerator {
	private static final int RANDOM_TRIGERRING_BRAKE = 1;
	private static final int RANDOM_MAX_BRAKE = 4;
	private static final int RANDOM_TRIGERRING_LEFT_TURN = 1;
	private static final int RANDOM_TRIGERRING_RIGHT_TURN = 5;
	private static final int RANDOM_MAX_TURN = 20;
	private static Random random;
	
	public static void setUp() {
		random = new Random();
	}
	
	public static int handleChangeProbability(int value, Tiredness tiredness) {
		try {
			if (RANDOM_TRIGERRING_BRAKE == random.nextInt(RANDOM_MAX_BRAKE))
				value--;
		} catch (NullPointerException e) {
			System.err.println("Generator not set up!");
		}
		
		if (value > 0) {
			int chanceToOverbrake;
			switch (tiredness) {
			case REFRESHED:
				chanceToOverbrake = 0;
				break;
			case TIRED:
				chanceToOverbrake = 1;
				break;
			case VERY_TIRED:
				chanceToOverbrake = 2;
				break;
			case EXHAUSTED:
				chanceToOverbrake = 3;
				break;
			default:
				chanceToOverbrake = 0;
				System.err.println("Invalid tiredness value");
				break;
			}
			if (random.nextInt(RANDOM_MAX_BRAKE) < chanceToOverbrake)
				value--;
		}
		
		return value;
	}
	
	public static Turn generateTurn() {
		if (RANDOM_TRIGERRING_LEFT_TURN != random.nextInt(RANDOM_MAX_TURN))
			return Turn.LEFT;
		if (RANDOM_TRIGERRING_RIGHT_TURN != random.nextInt(RANDOM_MAX_TURN))
			return Turn.RIGHT;
		return Turn.FORWARD;
	}

}
