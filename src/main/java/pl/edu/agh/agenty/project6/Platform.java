package pl.edu.agh.agenty.project6;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pl.edu.agh.agenty.project6.agents.Agent;
import pl.edu.agh.agenty.project6.agents.Car;
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
                        && !intersection.getRoadBeforeIntersectionY().road.get(0)) {
                    Car carY = intersection.addCarY(0, 0, ProbabilityGenerator.getRandomCarDirection(RoadConstants.TURN_INCLINATION));
                    new Thread(carY).start();
                }
//                intersection.getRoadBeforeIntersectionX().setLoggingThread(carX.getId());
//                intersection.getRoadAfterIntersectionY().setLoggingThread(carX.getId());
                List<Car> carsBeforeIntersectionX = intersection.getCarsBeforeIntersectionX();
                if (!carsBeforeIntersectionX.isEmpty()) {
                    Car lastCarX = carsBeforeIntersectionX.get(0);
                    if (lastCarX.isOutOfRoad() &&
                            (intersection.trafficLight.isGreenX() ||
                                    (intersection.trafficLight.isYellowX() && lastCarX.getDriversAggression() >= RoadConstants.AGRESSION_THRESHOLD))) {
                        intersection.crossIntersectionX(lastCarX);
                    }
                }

                List<Car> carsBeforeIntersectionY = intersection.getCarsBeforeIntersectionY();
                if (!carsBeforeIntersectionY.isEmpty()) {
                    Car lastCarY = carsBeforeIntersectionY.get(0);
                    if (lastCarY.isOutOfRoad() &&
                            (intersection.trafficLight.isGreenY() ||
                                    (intersection.trafficLight.isYellowY() && lastCarY.getDriversAggression() >= RoadConstants.AGRESSION_THRESHOLD))) {
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

                List<Car> carsAfterIntersectionY = intersection.getCarsAfterIntersectionY();
                if (!carsAfterIntersectionY.isEmpty()) {
                    Car lastCarY = carsAfterIntersectionY.get(0);
                    if (lastCarY.isOutOfRoad()) {
                        intersection.getRoadAfterIntersectionY().road.set(lastCarY.getPosition(), false);
                        carsAfterIntersectionY.remove(lastCarY);
                    }
                }

                Thread.sleep(RoadConstants.TURN_PERIOD);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void debug() {
        System.out.println(intersection);
        System.out.println("==========================================================");
    }
}
