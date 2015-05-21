package pl.edu.agh.agenty.project6;

import pl.edu.agh.agenty.project6.agents.Agent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class Platform implements Runnable {

    private Map<String,Agent> agents;
    private Intersection intersection;
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

    }
}
