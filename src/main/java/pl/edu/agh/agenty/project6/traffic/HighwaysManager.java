package pl.edu.agh.agenty.project6.traffic;

import pl.edu.agh.agenty.project6.traffic.entities.Turn;

public class HighwaysManager {
	public static Highway highwayNorth;
	public static Highway highwayEast;
	public static Highway highwaySouth;
	public static Highway highwayWest;

	public static void setUp(Highway highwayNorth,
							Highway highwayEast,
							Highway highwaySouth,
							Highway highwayWest) {
		HighwaysManager.highwayNorth = highwayNorth;
		HighwaysManager.highwayEast = highwayEast;
		HighwaysManager.highwaySouth = highwaySouth;
		HighwaysManager.highwayWest = highwayWest;
	}
	
	public static Highway getHighwayAfterTurn(Highway highway, Turn turn) {
		if (highway == highwayNorth) {
			if (turn == Turn.FORWARD)
				return highwaySouth;
			if (turn == Turn.LEFT)
				return highwayEast;
			return highwayWest;
		}
		if (highway == highwayEast) {
			if (turn == Turn.FORWARD)
				return highwayWest;
			if (turn == Turn.LEFT)
				return highwaySouth;
			return highwayNorth;
		}
		if (highway == highwaySouth) {
			if (turn == Turn.FORWARD)
				return highwayNorth;
			if (turn == Turn.LEFT)
				return highwayWest;
			return highwayEast;
		}
		else {
			if (turn == Turn.FORWARD)
				return highwayEast;
			if (turn == Turn.LEFT)
				return highwayNorth;
			return highwaySouth;
		}
	}
	
	public static void debug() {
		while (true) {
			System.out.println("0000000000000000000000000000000");
			System.out.println(highwayNorth);
			System.out.println("0000000000000000000000000000000");
			System.out.println(highwayEast);
			System.out.println("0000000000000000000000000000000");
			System.out.println(highwaySouth);
			System.out.println("0000000000000000000000000000000");
			System.out.println(highwayWest);
			System.out.println("0000000000000000000000000000000");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
