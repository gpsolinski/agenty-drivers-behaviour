package pl.edu.agh.agenty.project6.agents;


import pl.edu.agh.agenty.project6.traffic.ProbabilityGenerator;
import pl.edu.agh.agenty.project6.traffic.Road;
import pl.edu.agh.agenty.project6.traffic.RoadConstants;

public class Car extends Thread implements Agent  {
    private int velocity;
    private int position;
    private Road road;
    boolean notOutOfRoad;

    private double driversAggression;
    private double driversOpacity;
    private double length;

    public Car(int position, int velocity, Road road) {
        this.velocity = velocity;
        this.position = position;
        this.road = road;
        notOutOfRoad = true;
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
