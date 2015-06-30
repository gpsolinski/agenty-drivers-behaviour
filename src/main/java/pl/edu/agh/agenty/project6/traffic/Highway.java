package pl.edu.agh.agenty.project6.traffic;

import pl.edu.agh.agenty.project6.traffic.entities.Tiredness;
import pl.edu.agh.agenty.project6.traffic.entities.Turn;

public class Highway {
	private Road roadToCrossing;
	private Road oppositeRoad;
	
	public Highway() {
		roadToCrossing = new Road(true, this);
		oppositeRoad = new Road(false, this);
	}
	
	public Road switchRoad(Turn turn) {
		return HighwaysManager.getHighwayAfterTurn(this, turn).getOppositeRoad();
	}
	
	public void addCar(int position, int longitude, Tiredness tiredness) {
		Thread thread = new Thread(
							new Car(position, 0, longitude, roadToCrossing, tiredness)
							);
		thread.start();
	}
	
	public Road getOppositeRoad() {
		return oppositeRoad;
	}
	
	@Override
	public String toString() {
		String roadRepresentation = roadToCrossing.toString();
		String opossiteRoadRepresentation = oppositeRoad.toString();
		
		return roadRepresentation + "\n" + opossiteRoadRepresentation;
	}
}
