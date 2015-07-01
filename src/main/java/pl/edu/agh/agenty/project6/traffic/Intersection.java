package pl.edu.agh.agenty.project6.traffic;

import pl.edu.agh.agenty.project6.agents.CarDirection;
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

    public TrafficLight trafficLight;

    public Intersection() {
        roadBeforeIntersectionX = new Road();
        roadAfterIntersectionX = new Road();
        roadBeforeIntersectionY = new Road();
        roadAfterIntersectionY = new Road();
        carsBeforeIntersectionX = new LinkedList<>();
        carsBeforeIntersectionY = new LinkedList<>();
        carsAfterIntersectionY = new LinkedList<>();
        carsAfterIntersectionX = new LinkedList<>();
        trafficLight = new TrafficLight(RoadConstants.GREEN_TIME, RoadConstants.YELLOW_TIME);
    }

    public Car addCarX(int position, int velocity, CarDirection carDirection) {
        Car carToAdd = new Car(position, velocity, carDirection, roadBeforeIntersectionX);
        carsBeforeIntersectionX.add(carToAdd);
        return carToAdd;
    }

    public void crossIntersectionX(Car car) {
        carsBeforeIntersectionX.remove(car);
        roadBeforeIntersectionX.road.set(roadBeforeIntersectionX.road.size()-1, false);
        car.setRoad(roadAfterIntersectionX);
        car.setPosition(0);
        car.setNotOutOfRoad(true);
        carsAfterIntersectionX.add(car);
        roadAfterIntersectionX.road.set(0, true);
        new Thread(car).start();
    }

    public Car addCarY(int position, int velocity, CarDirection carDirection) {
        Car carToAdd = new Car(position, velocity, carDirection, roadBeforeIntersectionY);
        carsBeforeIntersectionY.add(carToAdd);
        return carToAdd;
    }

    public void crossIntersectionY(Car car) {
        carsBeforeIntersectionY.remove(car);
        roadBeforeIntersectionY.road.set(roadBeforeIntersectionY.road.size()-1, false);
        car.setRoad(roadAfterIntersectionY);
        car.setPosition(0);
        car.setNotOutOfRoad(true);
        carsAfterIntersectionY.add(car);
        roadAfterIntersectionY.road.set(0, true);
        new Thread(car).start();
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

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLightX(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String toString() {
        StringBuilder intersectionRepr = new StringBuilder();
        intersectionRepr.append(roadBeforeIntersectionX);
        intersectionRepr.append(trafficLight.toStringX());
        intersectionRepr.append(roadAfterIntersectionX + "\n");
        intersectionRepr.append(roadBeforeIntersectionY);
        intersectionRepr.append(trafficLight.toStringY());
        intersectionRepr.append(roadAfterIntersectionY + "\n");
        return intersectionRepr.toString();
    }
}
