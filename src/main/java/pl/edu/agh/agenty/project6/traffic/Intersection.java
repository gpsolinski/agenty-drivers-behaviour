package pl.edu.agh.agenty.project6.traffic;

import pl.edu.agh.agenty.project6.agents.TrafficLight;
import pl.edu.agh.agenty.project6.agents.Car;

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
        trafficLightX = new TrafficLight(RoadConstants.RED_TIME, RoadConstants.YELLOW_TIME, RoadConstants.GREEN_TIME, 0);
        trafficLightY = new TrafficLight(RoadConstants.RED_TIME, RoadConstants.YELLOW_TIME, RoadConstants.GREEN_TIME, RoadConstants.RED_TIME);
    }

    public Car addCarX(int position, int velocity) {
        Car carToAdd = new Car(position, velocity, roadAfterIntersectionX);
        carsBeforeIntersectionX.add(new Car(position, velocity, roadAfterIntersectionX));
        return carToAdd;
    }

    public void crossIntersectionX(Car car) {
        carsBeforeIntersectionX.remove(car);
        car.setPosition(0);
        carsAfterIntersectionX.add(car);
        car.start();
    }

    public Car addCarY(int position, int velocity) {
        Car carToAdd = new Car(position, velocity, roadAfterIntersectionY);
        carsBeforeIntersectionX.add(carToAdd);
        return carToAdd;
    }

    public void crossIntersectionY(Car car) {
        carsBeforeIntersectionY.remove(car);
        car.setPosition(0);
        carsAfterIntersectionY.add(car);
        car.start();
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
