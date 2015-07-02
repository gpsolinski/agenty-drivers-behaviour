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
    private Road roadBeforeIntersectionX;
    private Road oppositeBeforeIntersectionX;
    private Road roadAfterIntersectionX;
    private Road oppositeAfterIntersectionX;
    private Road roadBeforeIntersectionY;
    private Road oppositeBeforeIntersectionY;
    private Road roadAfterIntersectionY;
    private Road oppositeAfterIntersectionY;

    private List<Car> carsBeforeIntersectionX;
    private List<Car> carsOppositeBeforeIntersectionX;
    private List<Car> carsAfterIntersectionX;
    private List<Car> carsOppositeAfterIntersectionX;
    private List<Car> carsBeforeIntersectionY;
    private List<Car> carsOppositeBeforeIntersectionY;
    private List<Car> carsAfterIntersectionY;
    private List<Car> carsOppositeAfterIntersectionY;

    private int numberOfCarsTurningRight;
    private int numberOfCarsGoingStraight;
    private int numberOfCarsTurningLeft;

    public int collisions;

    private long totalWaitingTimeLeft;
    private long totalWaitingTimeStraight;
    private long totalWaitingTimeRight;

    public TrafficLight trafficLight;

    public Intersection() {
        roadBeforeIntersectionX = new Road();
        oppositeBeforeIntersectionX = new Road();
        roadAfterIntersectionX = new Road();
        oppositeAfterIntersectionX = new Road();
        roadBeforeIntersectionY = new Road();
        oppositeBeforeIntersectionY = new Road();
        roadAfterIntersectionY = new Road();
        oppositeAfterIntersectionY = new Road();

        carsBeforeIntersectionX = new LinkedList<>();
        carsOppositeBeforeIntersectionX = new LinkedList<>();
        carsBeforeIntersectionY = new LinkedList<>();
        carsOppositeAfterIntersectionX = new LinkedList<>();
        carsAfterIntersectionY = new LinkedList<>();
        carsOppositeBeforeIntersectionY = new LinkedList<>();
        carsAfterIntersectionX = new LinkedList<>();
        carsOppositeAfterIntersectionY = new LinkedList<>();

        numberOfCarsTurningRight = 0;
        numberOfCarsGoingStraight = 0;
        numberOfCarsTurningLeft = 0;

        collisions = 0;

        totalWaitingTimeLeft = 0;
        totalWaitingTimeStraight = 0;
        totalWaitingTimeRight = 0;

        trafficLight = new TrafficLight(RoadConstants.GREEN_TIME, RoadConstants.YELLOW_TIME);
    }

    public Car addCarX(int position, int velocity, CarDirection carDirection) {
        Car carToAdd = new Car(position, velocity, carDirection, roadBeforeIntersectionX);
        carsBeforeIntersectionX.add(carToAdd);
        return carToAdd;
    }

    public Car addOppositeCarX(int position, int velocity, CarDirection carDirection) {
        Car carToAdd = new Car(position, velocity, carDirection, oppositeBeforeIntersectionX);
        carsOppositeBeforeIntersectionX.add(carToAdd);
        return carToAdd;
    }

    public void crossIntersectionX(Car car) {
        carsBeforeIntersectionX.remove(car);
        roadBeforeIntersectionX.road.set(roadBeforeIntersectionX.road.size()-1, false);

        switch (car.getCarDirection()) {
            case LEFT:
                car.setRoad(roadAfterIntersectionY);
                carsAfterIntersectionY.add(car);
                roadAfterIntersectionY.road.set(0, true);
                numberOfCarsTurningLeft++;
                totalWaitingTimeLeft += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case STRAIGHT:
                car.setRoad(roadAfterIntersectionX);
                carsAfterIntersectionX.add(car);
                roadAfterIntersectionX.road.set(0, true);
                numberOfCarsGoingStraight++;
                totalWaitingTimeStraight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case RIGHT:
                car.setRoad(oppositeAfterIntersectionY);
                carsOppositeAfterIntersectionY.add(car);
                oppositeAfterIntersectionY.road.set(0, true);
                numberOfCarsTurningRight++;
                totalWaitingTimeRight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
        }

        car.setPosition(0);
        car.setNotOutOfRoad(true);

        new Thread(car).start();
    }

    public void crossIntersectionOppositeX(Car car) {
        carsOppositeBeforeIntersectionX.remove(car);
        oppositeBeforeIntersectionX.road.set(oppositeBeforeIntersectionX.road.size()-1, false);
        switch (car.getCarDirection()) {
            case LEFT:
                car.setRoad(oppositeAfterIntersectionY);
                carsOppositeAfterIntersectionY.add(car);
                oppositeAfterIntersectionY.road.set(0, true);
                numberOfCarsTurningLeft++;
                totalWaitingTimeLeft += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case STRAIGHT:
                car.setRoad(oppositeAfterIntersectionX);
                carsOppositeAfterIntersectionX.add(car);
                oppositeAfterIntersectionX.road.set(0, true);
                numberOfCarsGoingStraight++;
                totalWaitingTimeStraight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case RIGHT:
                car.setRoad(roadAfterIntersectionY);
                carsAfterIntersectionY.add(car);
                roadAfterIntersectionY.road.set(0, true);
                numberOfCarsTurningRight++;
                totalWaitingTimeRight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
        }

        car.setPosition(0);
        car.setNotOutOfRoad(true);

        new Thread(car).start();
    }

    public Car addCarY(int position, int velocity, CarDirection carDirection) {
        Car carToAdd = new Car(position, velocity, carDirection, roadBeforeIntersectionY);
        carsBeforeIntersectionY.add(carToAdd);
        return carToAdd;
    }

    public Car addOppositeCarY(int position, int velocity, CarDirection carDirection) {
        Car carToAdd = new Car(position, velocity, carDirection, oppositeBeforeIntersectionY);
        carsOppositeBeforeIntersectionY.add(carToAdd);
        return carToAdd;
    }

    public void crossIntersectionY(Car car) {
        carsBeforeIntersectionY.remove(car);
        roadBeforeIntersectionY.road.set(roadBeforeIntersectionY.road.size()-1, false);

        switch (car.getCarDirection()) {
            case LEFT:
                car.setRoad(oppositeAfterIntersectionX);
                carsOppositeAfterIntersectionX.add(car);
                oppositeAfterIntersectionX.road.set(0, true);
                numberOfCarsTurningLeft++;
                totalWaitingTimeLeft += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case STRAIGHT:
                car.setRoad(roadAfterIntersectionY);
                carsAfterIntersectionY.add(car);
                roadAfterIntersectionY.road.set(0, true);
                numberOfCarsGoingStraight++;
                totalWaitingTimeStraight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case RIGHT:
                car.setRoad(roadAfterIntersectionX);
                carsAfterIntersectionX.add(car);
                roadAfterIntersectionX.road.set(0, true);
                numberOfCarsTurningRight++;
                totalWaitingTimeRight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
        }

        car.setPosition(0);
        car.setNotOutOfRoad(true);

        new Thread(car).start();
    }

    public void crossIntersectionOppositeY(Car car) {
        carsOppositeBeforeIntersectionY.remove(car);
        oppositeBeforeIntersectionY.road.set(oppositeBeforeIntersectionY.road.size()-1, false);

        switch (car.getCarDirection()) {
            case LEFT:
                car.setRoad(roadAfterIntersectionX);
                carsAfterIntersectionX.add(car);
                roadAfterIntersectionX.road.set(0, true);
                numberOfCarsTurningLeft++;
                totalWaitingTimeLeft += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case STRAIGHT:
                car.setRoad(oppositeAfterIntersectionY);
                carsOppositeAfterIntersectionY.add(car);
                oppositeAfterIntersectionY.road.set(0, true);
                numberOfCarsGoingStraight++;
                totalWaitingTimeStraight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
            case RIGHT:
                car.setRoad(oppositeAfterIntersectionX);
                carsOppositeAfterIntersectionX.add(car);
                oppositeAfterIntersectionX.road.set(0, true);
                numberOfCarsTurningRight++;
                totalWaitingTimeRight += System.currentTimeMillis() - car.getReachingIntersectionTime();
                break;
        }

        car.setPosition(0);
        car.setNotOutOfRoad(true);

        new Thread(car).start();
    }

    public boolean canCrossX(Car car) {
        if (trafficLight.isRedX())
            return false;
        if (trafficLight.isYellowX() && car.getDriversAggression() < RoadConstants.AGRESSION_THRESHOLD)
            return false;
        switch (car.getCarDirection()) {
            case RIGHT:
                return !oppositeAfterIntersectionY.road.get(oppositeAfterIntersectionY.road.size()-1);
            case STRAIGHT:
                return !roadAfterIntersectionX.road.get(roadAfterIntersectionX.road.size()-1);
            case LEFT:
                return !roadAfterIntersectionY.road.get(roadAfterIntersectionY.road.size()-1) && !oppositeBeforeIntersectionX.road.get(0);
        }
        return true;
    }

    public boolean canCrossOppositeX(Car car) {
        if (trafficLight.isRedX())
            return false;
        if (trafficLight.isYellowX() && car.getDriversAggression() < RoadConstants.AGRESSION_THRESHOLD)
            return false;
        switch (car.getCarDirection()) {
            case RIGHT:
                return !roadAfterIntersectionY.road.get(roadAfterIntersectionY.road.size()-1);
            case STRAIGHT:
                return !oppositeAfterIntersectionX.road.get(oppositeAfterIntersectionX.road.size()-1);
            case LEFT:
                return !oppositeAfterIntersectionY.road.get(oppositeAfterIntersectionY.road.size()-1) && !roadBeforeIntersectionX.road.get(0);
        }
        return true;
    }

    public boolean canCrossY(Car car) {
        if (trafficLight.isRedY())
            return false;
        if (trafficLight.isYellowY() && car.getDriversAggression() < RoadConstants.AGRESSION_THRESHOLD)
            return false;
        switch (car.getCarDirection()) {
            case RIGHT:
                return !roadAfterIntersectionX.road.get(roadAfterIntersectionX.road.size()-1);
            case STRAIGHT:
                return !roadAfterIntersectionY.road.get(roadAfterIntersectionY.road.size()-1);
            case LEFT:
                return !oppositeAfterIntersectionX.road.get(oppositeAfterIntersectionX.road.size()-1) && !oppositeBeforeIntersectionY.road.get(0);
        }
        return true;
    }

    public boolean canCrossOppositeY(Car car) {
        if (trafficLight.isRedY())
            return false;
        if (trafficLight.isYellowY() && car.getDriversAggression() < RoadConstants.AGRESSION_THRESHOLD)
            return false;
        switch (car.getCarDirection()) {
            case RIGHT:
                return !oppositeAfterIntersectionX.road.get(oppositeAfterIntersectionX.road.size()-1);
            case STRAIGHT:
                return !oppositeAfterIntersectionY.road.get(oppositeAfterIntersectionY.road.size()-1);
            case LEFT:
                return !roadAfterIntersectionX.road.get(roadAfterIntersectionX.road.size()-1) && !roadBeforeIntersectionY.road.get(0);
        }
        return true;
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

    public Road getOppositeBeforeIntersectionX() {
        return oppositeBeforeIntersectionX;
    }

    public Road getOppositeAfterIntersectionX() {
        return oppositeAfterIntersectionX;
    }

    public Road getOppositeBeforeIntersectionY() {
        return oppositeBeforeIntersectionY;
    }

    public Road getOppositeAfterIntersectionY() {
        return oppositeAfterIntersectionY;
    }

    public List<Car> getCarsOppositeBeforeIntersectionX() {
        return carsOppositeBeforeIntersectionX;
    }

    public void setCarsOppositeBeforeIntersectionX(List<Car> carsOppositeBeforeIntersectionX) {
        this.carsOppositeBeforeIntersectionX = carsOppositeBeforeIntersectionX;
    }

    public List<Car> getCarsOppositeAfterIntersectionX() {
        return carsOppositeAfterIntersectionX;
    }

    public void setCarsOppositeAfterIntersectionX(List<Car> carsOppositeAfterIntersectionX) {
        this.carsOppositeAfterIntersectionX = carsOppositeAfterIntersectionX;
    }

    public List<Car> getCarsOppositeBeforeIntersectionY() {
        return carsOppositeBeforeIntersectionY;
    }

    public void setCarsOppositeBeforeIntersectionY(List<Car> carsOppositeBeforeIntersectionY) {
        this.carsOppositeBeforeIntersectionY = carsOppositeBeforeIntersectionY;
    }

    public List<Car> getCarsOppositeAfterIntersectionY() {
        return carsOppositeAfterIntersectionY;
    }

    public void setCarsOppositeAfterIntersectionY(List<Car> carsOppositeAfterIntersectionY) {
        this.carsOppositeAfterIntersectionY = carsOppositeAfterIntersectionY;
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

    public int getNumberOfCarsTurningRight() {
        return numberOfCarsTurningRight;
    }

    public int getNumberOfCarsGoingStraight() {
        return numberOfCarsGoingStraight;
    }

    public int getNumberOfCarsTurningLeft() {
        return numberOfCarsTurningLeft;
    }

    public long getTotalWaitingTimeLeft() {
        return totalWaitingTimeLeft;
    }

    public long getTotalWaitingTimeStraight() {
        return totalWaitingTimeStraight;
    }

    public long getTotalWaitingTimeRight() {
        return totalWaitingTimeRight;
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

        StringBuilder oppositeRoad = new StringBuilder();
        oppositeRoad.append(oppositeAfterIntersectionX);
        intersectionRepr.append(oppositeRoad.reverse().toString());
        intersectionRepr.append(trafficLight.toStringX());

        oppositeRoad = new StringBuilder();
        oppositeRoad.append(oppositeBeforeIntersectionX);
        intersectionRepr.append(oppositeRoad.reverse().toString());

        intersectionRepr.append("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        intersectionRepr.append(roadBeforeIntersectionY);
        intersectionRepr.append(trafficLight.toStringY());
        intersectionRepr.append(roadAfterIntersectionY + "\n");

        oppositeRoad = new StringBuilder();
        oppositeRoad.append(oppositeAfterIntersectionY);
        intersectionRepr.append(oppositeRoad.reverse().toString());
        intersectionRepr.append(trafficLight.toStringY());

        oppositeRoad = new StringBuilder();
        oppositeRoad.append(oppositeBeforeIntersectionY);
        intersectionRepr.append(oppositeRoad.reverse().toString());

        intersectionRepr.append("\n");
        return intersectionRepr.toString();
    }
}
