package pl.edu.agh.agenty.project6.agents;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class Car implements Agent {
    private double driversAggression;
    private double driversOpacity;
    private double length;

    public double getDriversAggression() {
        return driversAggression;
    }

    public void setDriversAggression(double driversAggression) {
        this.driversAggression = driversAggression;
    }

    public double getDriversOpacity() {
        return driversOpacity;
    }

    public void setDriversOpacity(double driversOpacity) {
        this.driversOpacity = driversOpacity;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
