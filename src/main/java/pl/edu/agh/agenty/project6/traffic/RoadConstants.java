package pl.edu.agh.agenty.project6.traffic;

public final class RoadConstants {
	public static final int MAX_SPEED = 3;
	public static final int LENGTH = 50;
    public static final int SAFE_DISTANCE = 10;

    public static final int RED_TIME = 2000;
    public static final int YELLOW_TIME = 800;
    public static final int GREEN_TIME = 2000;

    public static final int CAR_PERIOD = 100;
    public static final int TURN_PERIOD = 500;

    public static final double CAR_TRAFFIC = 0.20;
    public static final double TURN_INCLINATION = 0.20;
    public static final double AGRESSION_THRESHOLD = 0.30;

	private RoadConstants(){}
}
