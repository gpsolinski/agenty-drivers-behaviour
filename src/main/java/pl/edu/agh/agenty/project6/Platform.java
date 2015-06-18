package pl.edu.agh.agenty.project6;

import pl.edu.agh.agenty.project6.agents.Agent;
import pl.edu.agh.agenty.project6.agents.Car;
import pl.edu.agh.agenty.project6.agents.LightColor;
import pl.edu.agh.agenty.project6.traffic.Road;
import pl.edu.agh.agenty.project6.traffic.RoadConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class Platform implements Runnable {

    private Map<String,Agent> agents;
    private Intersection intersection;
    private int countBeforeIntersectionX = 0;
    private int countBeforeIntersectionY = 0;
    private Platform _instance = new Platform();

    private Platform() {
        agents = new HashMap<>();
        intersection = new Intersection();
    }

    public Platform getInstance() {
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

        Car car1X = new Car(0, 0);
        Car car2X = new Car(4, 0);
        Car car3X = new Car(5, 0);

        Car car1Y = new Car(0, 0);
        Car car2Y = new Car(2, 0);
        Car car3Y = new Car(4, 0);

        intersection.addCarX(car1X);
        intersection.addCarX(car2X);
        intersection.addCarX(car3X);
        intersection.addCarX(car1Y);
        intersection.addCarX(car2Y);
        intersection.addCarX(car3Y);

        try {

            for (int time = 0; time < 200; time++) {
                //ilość samochodów w danej odległości od świateł
                int countBeforeIntersectionX = 0;
                int countBeforeIntersectionY = 0;
                for (int i = RoadConstants.LENGTH - 1; i >= RoadConstants.SAFE_DISTANCE ; i--) {
                    if (intersection.getRoadBeforeIntersectionX().road.get(i))
                        countBeforeIntersectionX++;

                    if (intersection.getRoadBeforeIntersectionY().road.get(i))
                        countBeforeIntersectionY++;

                }
//
//                for (int i = RoadConstants.LENGTH - 1; i >= 0 ; i--) {
//                    int diff = 0;
//                    if (intersection.getRoadBeforeIntersectionY().road.get(i))
//                        diff = 1;
//
//                    if (i == RoadConstants.LENGTH - 1 && intersection.getRoadBeforeIntersectionY().road.get(i))
//                        countOfCarsInDistanceY.set(i, 1);
//                    else {
//                        countOfCarsInDistanceY.set(i, countOfCarsInDistanceY.get(i+1) + diff);
//                    }
//                }
                boolean stoppedX = false;
                boolean stoppedY = false;

                for (Car car : intersection.getCarsAfterIntersectionX()) {
                    if (car.getVelocity() == 0) {
                        stoppedX = true;
                        break;
                    }
                }
                if (stoppedX)
                    intersection.trafficLightX.setCurrentLightColor(LightColor.RED);

                for (Car car : intersection.getCarsAfterIntersectionY()) {
                    if (car.getVelocity() == 0) {
                        stoppedY = true;
                        break;
                    }
                }
                if (stoppedY)
                    intersection.trafficLightY.setCurrentLightColor(LightColor.RED);

                if (intersection.getRoadAfterIntersectionX().road.get(0)
                        && intersection.getCarsAfterIntersectionX().get(0).getVelocity() == 0) {
                    intersection.trafficLightX.setCurrentLightColor(LightColor.RED);
                }

                if (intersection.getRoadAfterIntersectionY().road.get(0)
                        && intersection.getCarsAfterIntersectionY().get(0).getVelocity() == 0) {
                    intersection.trafficLightY.setCurrentLightColor(LightColor.RED);
                }


                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
