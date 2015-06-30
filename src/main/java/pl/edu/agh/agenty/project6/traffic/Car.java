package pl.edu.agh.agenty.project6.traffic;

import pl.edu.agh.agenty.project6.traffic.entities.Tiredness;
import pl.edu.agh.agenty.project6.traffic.entities.Turn;
import pl.edu.agh.agenty.project6.traffic.random.ProbabilityGenerator;


public class Car implements Runnable {
	private int velocity;
	private int position;
	private final int longitude;
	private final Tiredness tiredness;
	private final Turn turnToTake;
	private Road road;
	boolean notOutOfRoad;
	
	public Car(int position, int velocity, int longitude, Road road,
				Tiredness tiredness) {
		this.velocity = velocity;
		this.position = position;
		this.road = road;
		this.longitude = longitude;
		this.tiredness = tiredness;
		this.turnToTake = ProbabilityGenerator.generateTurn();
		notOutOfRoad = true;
	}
	
	@Override
	public void run() {
		while (notOutOfRoad && road.isDirectedToCrossing()) {
			if (!notOutOfRoad) /* is about to do a turn on a crossing */
				doManeuver();
			while (notOutOfRoad) {			
				acceleration();
				braking();
				randomize();
				move();
				
				if (turnToTake == Turn.LEFT 
					&& (RoadConstants.ROAD_LENGTH - RoadConstants.LEFT_LANE_LENGTH
						< position)) {
					setVelocity(0);
					while (!road.joinLeftTurnLane(Thread.currentThread().getId(),
													position, longitude));
					notOutOfRoad = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Car reached the end of the road!");
	}

	private void doManeuver() {
		while(road.isRedLight());
		notOutOfRoad = true;
		road = road.changeRoad(turnToTake);
	}

	private void acceleration() {
		if (velocity < RoadConstants.MAX_SPEED) {
			setVelocity(velocity+1);
		}
	}
	
	private void braking() {
		setVelocity(Math.min(velocity, road.getDistanceAhead(position)));
	}
	
	public void randomize() {
		setVelocity(ProbabilityGenerator.handleChangeProbability(velocity, 
																tiredness));
	}
	
	public void move() {
		notOutOfRoad = road.move(position, velocity, longitude);
		position += velocity;
	}
	
	public void setVelocity(int velocity) {
		if (velocity > 0)
			this.velocity = velocity;
		else 
			this.velocity = 0;
	}

}
