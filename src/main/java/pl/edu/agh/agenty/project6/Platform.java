package pl.edu.agh.agenty.project6;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pl.edu.agh.agenty.project6.agents.Agent;
import pl.edu.agh.agenty.project6.agents.Car;
import pl.edu.agh.agenty.project6.agents.CarDirection;
import pl.edu.agh.agenty.project6.traffic.CollisionException;
import pl.edu.agh.agenty.project6.traffic.Intersection;
import pl.edu.agh.agenty.project6.traffic.ProbabilityGenerator;
import pl.edu.agh.agenty.project6.traffic.RoadConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class Platform extends Thread {

    private Map<String,Agent> agents;
    private Intersection intersection;
    private int countBeforeIntersectionX = 0;
    private int countBeforeIntersectionY = 0;
    private static Platform _instance = new Platform();

    private Platform() {
        agents = new HashMap<>();
        intersection = new Intersection();
    }

    public static Platform getInstance() {
        return _instance;
    }

    public void registerAgent(String id, Agent agent) {
        agents.put(id, agent);
    }

    public void deregisterAgent(String id) {
        agents.remove(id);
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public void run() {
        try {
            intersection.trafficLight.start();

            while(true) {
                debug();
                if (ProbabilityGenerator.addAnotherCar(RoadConstants.CAR_TRAFFIC)
                        && !intersection.getRoadBeforeIntersectionX().road.get(0)) {
                    Car carX = intersection.addCarX(0, 0, ProbabilityGenerator.getRandomCarDirection(RoadConstants.TURN_INCLINATION));
                    new Thread(carX).start();
                }
                Thread.sleep(RoadConstants.CAR_PERIOD);
                if (ProbabilityGenerator.addAnotherCar(RoadConstants.CAR_TRAFFIC)
                        && !intersection.getOppositeBeforeIntersectionX().road.get(0)) {
                    Car carX = intersection.addOppositeCarX(0, 0, ProbabilityGenerator.getRandomCarDirection(RoadConstants.TURN_INCLINATION));
                    new Thread(carX).start();
                }
                Thread.sleep(RoadConstants.CAR_PERIOD);
                if (ProbabilityGenerator.addAnotherCar(RoadConstants.CAR_TRAFFIC)
                        && !intersection.getRoadBeforeIntersectionY().road.get(0)) {
                    Car carY = intersection.addCarY(0, 0, ProbabilityGenerator.getRandomCarDirection(RoadConstants.TURN_INCLINATION));
                    new Thread(carY).start();
                }
                Thread.sleep(RoadConstants.CAR_PERIOD);
                if (ProbabilityGenerator.addAnotherCar(RoadConstants.CAR_TRAFFIC)
                        && !intersection.getOppositeBeforeIntersectionY().road.get(0)) {
                    Car carY = intersection.addOppositeCarY(0, 0, ProbabilityGenerator.getRandomCarDirection(RoadConstants.TURN_INCLINATION));
                    new Thread(carY).start();
                }

                List<Car> carsBeforeIntersectionX = intersection.getCarsBeforeIntersectionX();
                if (!carsBeforeIntersectionX.isEmpty()) {
                    Car lastCarX = carsBeforeIntersectionX.get(0);
                    if (lastCarX.isOutOfRoad() && intersection.canCrossX(lastCarX)) {
                        intersection.crossIntersectionX(lastCarX);
                    }
                }

                List<Car> carsOppositeBeforeIntersectionX = intersection.getCarsOppositeBeforeIntersectionX();
                if (!carsOppositeBeforeIntersectionX.isEmpty()) {
                    Car lastCarX = carsOppositeBeforeIntersectionX.get(0);
                    if (lastCarX.isOutOfRoad() && intersection.canCrossOppositeX(lastCarX)) {
                        intersection.crossIntersectionOppositeX(lastCarX);
                    }
                }

                List<Car> carsBeforeIntersectionY = intersection.getCarsBeforeIntersectionY();
                if (!carsBeforeIntersectionY.isEmpty()) {
                    Car lastCarY = carsBeforeIntersectionY.get(0);
                    if (lastCarY.isOutOfRoad() && intersection.canCrossY(lastCarY)) {
                        intersection.crossIntersectionY(lastCarY);
                    }
                }

                List<Car> carsOppositeBeforeIntersectionY = intersection.getCarsOppositeBeforeIntersectionY();
                if (!carsOppositeBeforeIntersectionY.isEmpty()) {
                    Car lastCarY = carsOppositeBeforeIntersectionY.get(0);
                    if (lastCarY.isOutOfRoad() && intersection.canCrossOppositeY(lastCarY)) {
                        intersection.crossIntersectionY(lastCarY);
                    }
                }

                List<Car> carsAfterIntersectionX = intersection.getCarsAfterIntersectionX();
                if (!carsAfterIntersectionX.isEmpty()) {
                    Car lastCarX = carsAfterIntersectionX.get(0);
                    if (lastCarX.isOutOfRoad()) {
                        intersection.getRoadAfterIntersectionX().road.set(lastCarX.getPosition(), false);
                        carsAfterIntersectionX.remove(lastCarX);
                    }
                }

                List<Car> carsOppositeAfterIntersectionX = intersection.getCarsOppositeAfterIntersectionX();
                if (!carsOppositeAfterIntersectionX.isEmpty()) {
                    Car lastCarX = carsOppositeAfterIntersectionX.get(0);
                    if (lastCarX.isOutOfRoad()) {
                        intersection.getOppositeAfterIntersectionX().road.set(lastCarX.getPosition(), false);
                        carsOppositeAfterIntersectionX.remove(lastCarX);
                    }
                }

                List<Car> carsAfterIntersectionY = intersection.getCarsAfterIntersectionY();
                if (!carsAfterIntersectionY.isEmpty()) {
                    Car lastCarY = carsAfterIntersectionY.get(0);
                    if (lastCarY.isOutOfRoad()) {
                        intersection.getRoadAfterIntersectionY().road.set(lastCarY.getPosition(), false);
                        carsAfterIntersectionY.remove(lastCarY);
                    }
                }

                List<Car> carsOppositeAfterIntersectionY = intersection.getCarsOppositeAfterIntersectionY();
                if (!carsOppositeAfterIntersectionY.isEmpty()) {
                    Car lastCarY = carsOppositeAfterIntersectionY.get(0);
                    if (lastCarY.isOutOfRoad()) {
                        intersection.getOppositeAfterIntersectionY().road.set(lastCarY.getPosition(), false);
                        carsOppositeAfterIntersectionY.remove(lastCarY);
                    }
                }

                System.out.println("Number of collisions: " + intersection.collisions);
                System.out.println("Number of cars turning right: " + intersection.getNumberOfCarsTurningRight());
                System.out.println("Number of cars going straight: " +intersection.getNumberOfCarsGoingStraight());
                System.out.println("Number of cars turning left: " + intersection.getNumberOfCarsTurningLeft());
                System.out.println("Average waiting time to turn right: " + (double)intersection.getTotalWaitingTimeRight()/intersection.getNumberOfCarsTurningRight());
                System.out.println("Average waiting time to go straight: " + (double)intersection.getTotalWaitingTimeStraight()/intersection.getNumberOfCarsGoingStraight());
                System.out.println("Average waiting time to turn left: " + (double)intersection.getTotalWaitingTimeLeft()/intersection.getNumberOfCarsTurningLeft());


                Thread.sleep(RoadConstants.TURN_PERIOD);
            }
        } catch (CollisionException e) {
            intersection.collisions++;
        } catch (InterruptedException e1) {
            System.out.println("Finish!");
        }
    }


    public void debug() {
        System.out.println(intersection);
        System.out.println("=============================================================================================");
    }
}
