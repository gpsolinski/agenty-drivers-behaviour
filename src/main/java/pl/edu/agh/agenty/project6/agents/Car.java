package pl.edu.agh.agenty.project6.agents;


import pl.edu.agh.agenty.project6.traffic.ProbabilityGenerator;
import pl.edu.agh.agenty.project6.traffic.Road;
import pl.edu.agh.agenty.project6.traffic.RoadConstants;

public class Car implements Agent, Runnable  {
    private int velocity;
    private int position;
    private Road road;
    private boolean notOutOfRoad;
    private final CarDirection carDirection;

    private double driversAggression;
    private double driversOpacity;
    private double length;

    private long reachingIntersectionTime;

    public Car(int position, int velocity, CarDirection carDirection, Road road) {
        this.velocity = velocity;
        this.position = position;
        this.carDirection = carDirection;
        this.road = road;
        notOutOfRoad = true;
        reachingIntersectionTime = 0;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getDriversAggression() {
        return driversAggression;
    }

    public double getDriversOpacity() {
        return driversOpacity;
    }

    public double getLength() {
        return length;
    }

    public void setNotOutOfRoad(boolean notOutOfRoad) {
        this.notOutOfRoad = notOutOfRoad;
    }

    public boolean isOutOfRoad() {
        return !notOutOfRoad;
    }

    @Override
    public void run() {
        while (notOutOfRoad) {
            acceleration();
            braking();
            randomize();
            move();

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        reachingIntersectionTime = System.currentTimeMillis();
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
        if (notOutOfRoad)
            position += velocity;
        else
            position = road.road.size()-1;
    }

    public void setVelocity(int velocity) {
        if (velocity > 0)
            this.velocity = velocity;
        else
            this.velocity = 0;
    }

    public int getPosition() {
        return position;
    }

    public long getReachingIntersectionTime() {
        return reachingIntersectionTime;
    }

    public CarDirection getCarDirection() {
        return carDirection;
    }

    public String toString() {
        switch (carDirection) {
            case RIGHT:
                return "R";
            case STRAIGHT:
                return "S";
            case LEFT:
                return "L";
            default:
                return "S";
        }
    }
}
