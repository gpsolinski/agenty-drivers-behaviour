package pl.edu.agh.agenty.project6.traffic;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

public class Road {
	public List<Boolean> road;
	/* tmp printing solution - waits for other threads */
	private CyclicBarrier barrier;
	private long loggingThread;
	
	public Road() {
		road = new CopyOnWriteArrayList<Boolean>();
		barrier = new CyclicBarrier(3);
		for (int i = 0; i < RoadConstants.LENGTH; i++) {
			road.add(false);
		}
	}
	
	public void setLoggingThread(long id) {
		loggingThread = id;
	}
	
	/* we are interested only in cars, not road end's */
	public int getDistanceAhead(int position) {
		int i = position + 1, returnValue;
		for (; i < road.size() && !road.get(i); i++);
		if (i >= road.size())
			returnValue = RoadConstants.LENGTH;
		else 
			returnValue = i - position - 1;
		
		return returnValue;
	}
	
	public boolean move(int position, int distance) {
		if (position + distance >= road.size()) {
			road.set(position, false);
            road.set(road.size() - 1, true);
			return false;
		}
			
		road.set(position, false);
		int i = position;
		for (; (i != (position+distance)) && !road.get(i); i++);
		if (road.get(i)) {
			throw new RuntimeException("Collision at position " + position);
		}
		
		road.set(i, true);

		return true;
	}
	
	public void debug() {
		System.out.println(this);
	}

    public String toString() {

        StringBuilder roadRepresentation = new StringBuilder();
        for (Boolean element : road) {
            if (element)
                roadRepresentation.append("[ ]");
            else
                roadRepresentation.append("-");
        }
        return roadRepresentation.toString();
    }
}
