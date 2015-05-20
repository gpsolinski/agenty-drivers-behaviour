package pl.agh.edu.agenty.project6.traffic;


public class Car implements Runnable {
	private int velocity;
	private int position;
	private Road road;
	boolean notOutOfRoad;
	
	public Car(int position, int velocity, Road road) {
		this.velocity = velocity;
		this.position = position;
		this.road = road;
		notOutOfRoad = true;
	}
	
	@Override
	public void run() {
		while (notOutOfRoad) {			
			acceleration();
			braking();
			randomize();
			move();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Car reached the end of the road!");
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
		setVelocity(ProbabilityGenerator.handleChangeProbability(velocity));
	}
	
	public void move() {
		notOutOfRoad = road.move(position, velocity);
		position += velocity;
	}
	
	public void setVelocity(int velocity) {
		if (velocity > 0)
			this.velocity = velocity;
		else 
			this.velocity = 0;
	}

}
