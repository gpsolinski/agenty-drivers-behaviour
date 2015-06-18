package pl.edu.agh.agenty.project6;

import pl.edu.agh.agenty.project6.agents.LightColor;
import pl.edu.agh.agenty.project6.agents.TrafficLight;
import pl.edu.agh.agenty.project6.agents.Car;
import pl.edu.agh.agenty.project6.traffic.Road;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by grzegorz on 2015-05-21.
 */
public class Intersection {
    private int numberOfLanes;
    private Road roadBeforeIntersectionX;
    private Road roadAfterIntersectionX;
    private Road roadBeforeIntersectionY;
    private Road roadAfterIntersectionY;

    private List<Car> carsBeforeIntersectionX;
    private List<Car> carsAfterIntersectionX;
    private List<Car> carsBeforeIntersectionY;
    private List<Car> carsAfterIntersectionY;

    public TrafficLight trafficLightX;
    public TrafficLight trafficLightY;

    public Intersection() {
        roadBeforeIntersectionX = new Road();
        roadAfterIntersectionX = new Road();
        roadBeforeIntersectionY = new Road();
        roadAfterIntersectionY = new Road();
        carsBeforeIntersectionX = new LinkedList<>();
        carsBeforeIntersectionY = new LinkedList<>();
        carsAfterIntersectionY = new LinkedList<>();
        carsAfterIntersectionX = new LinkedList<>();
        trafficLightX = new TrafficLight();
        trafficLightX.setCurrentLightColor(LightColor.RED);
        trafficLightY = new TrafficLight();
        trafficLightY.setCurrentLightColor(LightColor.RED);
    }

    public void addCarX(Car car) {
        carsBeforeIntersectionX.add(car);
    }

    public void crossIntersectionX(Car car) {
        carsBeforeIntersectionX.remove(car);
        carsAfterIntersectionX.add(car);
    }

    public void addCarY(Car car) {
        carsBeforeIntersectionX.add(car);
    }

    public void crossIntersectionY(Car car) {
        carsBeforeIntersectionX.remove(car);
        carsAfterIntersectionX.add(car);
    }

    public Road getRoadBeforeIntersectionX() {
        return roadBeforeIntersectionX;
    }

    public Road getRoadAfterIntersectionX() {
        return roadAfterIntersectionX;
    }

    public Road getRoadBeforeIntersectionY() {
        return roadBeforeIntersectionY;
    }

    public Road getRoadAfterIntersectionY() {
        return roadAfterIntersectionY;
    }

    public List<Car> getCarsBeforeIntersectionX() {
        return carsBeforeIntersectionX;
    }

    public void setCarsBeforeIntersectionX(List<Car> carsBeforeIntersectionX) {
        this.carsBeforeIntersectionX = carsBeforeIntersectionX;
    }

    public List<Car> getCarsAfterIntersectionX() {
        return carsAfterIntersectionX;
    }

    public void setCarsAfterIntersectionX(List<Car> carsAfterIntersectionX) {
        this.carsAfterIntersectionX = carsAfterIntersectionX;
    }

    public List<Car> getCarsBeforeIntersectionY() {
        return carsBeforeIntersectionY;
    }

    public void setCarsBeforeIntersectionY(List<Car> carsBeforeIntersectionY) {
        this.carsBeforeIntersectionY = carsBeforeIntersectionY;
    }

    public List<Car> getCarsAfterIntersectionY() {
        return carsAfterIntersectionY;
    }

    public void setCarsAfterIntersectionY(List<Car> carsAfterIntersectionY) {
        this.carsAfterIntersectionY = carsAfterIntersectionY;
    }

    public int getNumberOfLanes() {
        return numberOfLanes;
    }

    public void setNumberOfLanes(int numberOfLanes) {
        this.numberOfLanes = numberOfLanes;
    }

    public TrafficLight getTrafficLightX() {
        return trafficLightX;
    }

    public void setTrafficLightX(TrafficLight trafficLightX) {
        this.trafficLightX = trafficLightX;
    }

    public TrafficLight getTrafficLightY() {
        return trafficLightY;
    }

    public void setTrafficLightY(TrafficLight trafficLightY) {
        this.trafficLightY = trafficLightY;
    }
}
