package pl.edu.agh.agenty.project6.traffic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pl.edu.agh.agenty.project6.traffic.entities.Turn;

public class Road {
	private final List<Long> road;
	private final List<Long> leftTurnLane;
	private final boolean directedToCrossing;
	private final Highway parentHighway;
	private boolean isRedLight;
	
	public Road(boolean directedToCrossing, Highway parentHighway) {
		road = new CopyOnWriteArrayList<Long>();
		leftTurnLane = new CopyOnWriteArrayList<Long>();
		this.directedToCrossing = directedToCrossing;
		this.parentHighway = parentHighway;
		for (int i = 0; i < RoadConstants.ROAD_LENGTH; i++) {
			road.add(RoadConstants.EMPTY_ROAD);
		}
		isRedLight = true;
	}
	
	/* we are interested only in cars, not road end's */
	public int getDistanceAhead(int position) {
		int i = position + 1, returnValue;
		for (; i < road.size() && (road.get(i) == RoadConstants.EMPTY_ROAD); i++);
		if (i >= road.size())
			returnValue = RoadConstants.ROAD_LENGTH;
		else 
			returnValue = i - position - 1;
		
		return returnValue;
	}
	
	public boolean move(int position, int distance, int longitude) {
		if (position + distance >= road.size()) {
			unmarkPreviousPosition(position, longitude);
			if (directedToCrossing) 
				markActualPosition(RoadConstants.ROAD_LENGTH, longitude);
			return false;
		}
			
		unmarkPreviousPosition(position, longitude);
		int i = position;
		for (; (i != (position+distance)) && (road.get(i) == 0); i++);
		if (road.get(i) != 0) {
			throw new RuntimeException("Collision at position " + position);
		}
		markActualPosition(i, longitude);		
		
		return true;
	}
	
	private void markActualPosition(int position, int longitude) {
		while ((longitude-- > 0) && (position > 0)) {
			road.set(position--, Thread.currentThread().getId());
		}
	}

	private void unmarkPreviousPosition(int position, int longitude) {
		while ((longitude-- > 0) && (position > 0)) {
			road.set(position--, RoadConstants.EMPTY_ROAD);
		}
	}
	
	public boolean joinLeftTurnLane(long id, int position, int longitude) {
		if (!directedToCrossing)
			throw new RuntimeException("Road not directed to crossroad!");
		if (leftTurnLane.size() > RoadConstants.LEFT_LANE_LENGTH)
			return false;
		
		unmarkPreviousPosition(position, longitude);
		leftTurnLane.add(id);
		
		return true;
	}
	
	public boolean isDirectedToCrossing() {
		return directedToCrossing;
	}
	
	public Road changeRoad(Turn turn) {
		return parentHighway.switchRoad(turn);
	}
	
	@Override
	public String toString() {
		StringBuilder roadRepresentation = new StringBuilder();
		long vechicleNum;
		int i = 0;
		
		if (directedToCrossing) {	/* i.e. has left turn lane */
			for (i = 0; i < (RoadConstants.ROAD_LENGTH 
						- RoadConstants.LEFT_LANE_LENGTH - 1);
						i++) {
				roadRepresentation.append(" ");
			}
			roadRepresentation.append("/");
			for (; i < RoadConstants.ROAD_LENGTH; i++) {
				if (leftTurnLane.size() > (RoadConstants.ROAD_LENGTH - i)) {
					vechicleNum = leftTurnLane.get(RoadConstants.ROAD_LENGTH - i);
					if (vechicleNum != RoadConstants.EMPTY_ROAD) {
						roadRepresentation.append("[ ");
						i++;
						while ((i < leftTurnLane.size()) 
								&& (leftTurnLane.get(i) == vechicleNum)) {
							roadRepresentation.append(" ");
							i++;
						}
						roadRepresentation.append("]");
					}
					else
						roadRepresentation.append("-");
				}
				else
					roadRepresentation.append("-");
			}
			roadRepresentation.append("\n");
		}
		/* main road */
		for (i = 0; i < road.size(); i++) {
			vechicleNum = road.get(i);
			if (vechicleNum != RoadConstants.EMPTY_ROAD) {
				roadRepresentation.append("[ ");
				i++;
				while ((i < road.size()) && (road.get(i) == vechicleNum)) {
					roadRepresentation.append(" ");
					i++;
				}
				roadRepresentation.append("]");
			}
			else
				roadRepresentation.append("-");
		}
		
		return roadRepresentation.toString();
	}

	public boolean isRedLight() {
		return isRedLight;
	}
	
	public void setIsRedLight(boolean isRedLight) {
		this.isRedLight = isRedLight;
	}

}
