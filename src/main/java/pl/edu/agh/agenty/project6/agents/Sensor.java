package pl.edu.agh.agenty.project6.agents;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class Sensor {

    private double trafficDensity;
    private boolean deadlock;
    private boolean weather;

    public double getTrafficDensity() {
        return trafficDensity;
    }

    public void setTrafficDensity(double trafficDensity) {
        this.trafficDensity = trafficDensity;
    }

    public boolean isDeadlock() {
        return deadlock;
    }

    public void setDeadlock(boolean deadlock) {
        this.deadlock = deadlock;
    }

    public boolean isWeather() {
        return weather;
    }

    public void setWeather(boolean weather) {
        this.weather = weather;
    }
}
