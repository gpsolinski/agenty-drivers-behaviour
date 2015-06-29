package pl.edu.agh.agenty.project6;

import pl.edu.agh.agenty.project6.agents.Agent;
import pl.edu.agh.agenty.project6.agents.Car;
import pl.edu.agh.agenty.project6.traffic.Intersection;
import pl.edu.agh.agenty.project6.traffic.RoadConstants;

import java.util.HashMap;
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
            intersection.trafficLightX.start();
            System.out.print("Traffic lights -- added;");
            intersection.trafficLightY.start();
            System.out.print("Traffic lights | added;");

            while(true) {
                Car carX = intersection.addCarX(0,0);
                carX.start();
                Thread.sleep(RoadConstants.CAR_PERIOD);
                Car carY = intersection.addCarY(0,0);
                carY.start();
                intersection.getRoadBeforeIntersectionX().setLoggingThread(carX.getId());
                intersection.getRoadAfterIntersectionY().setLoggingThread(carX.getId());
                for (Car car : intersection.getCarsBeforeIntersectionX()) {
                    if (car.isOutOfRoad() &&
                            (intersection.trafficLightX.isGreen() ||
                                    (intersection.trafficLightX.isYellow() && car.getDriversAggression() >= 0.3))) {
                        intersection.crossIntersectionX(car);
                    }
                }

                for (Car car : intersection.getCarsBeforeIntersectionY()) {
                    if (car.isOutOfRoad() &&
                            (intersection.trafficLightX.isGreen() ||
                                    (intersection.trafficLightX.isYellow() && car.getDriversAggression() >= 0.3))) {
                        intersection.crossIntersectionY(car);
                    }
                }

                Thread.sleep(RoadConstants.TURN_PERIOD);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
