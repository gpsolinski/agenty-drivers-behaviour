package pl.edu.agh.agenty.project6;

import pl.edu.agh.agenty.project6.traffic.Highway;
import pl.edu.agh.agenty.project6.traffic.HighwaysManager;
import pl.edu.agh.agenty.project6.traffic.entities.Tiredness;
import pl.edu.agh.agenty.project6.traffic.random.ProbabilityGenerator;


public class Main {
	
    public static void main(String[] args) {
        ProbabilityGenerator.setUp();
        
        Highway highwayNorth = new Highway();
        highwayNorth.addCar(3, 3, Tiredness.REFRESHED);
        highwayNorth.addCar(5, 1, Tiredness.TIRED);
        highwayNorth.addCar(7, 1, Tiredness.REFRESHED);
        Highway highwayEast = new Highway();
        highwayEast.addCar(3, 3, Tiredness.REFRESHED);
        highwayEast.addCar(5, 1, Tiredness.TIRED);
        highwayEast.addCar(7, 1, Tiredness.REFRESHED);
        Highway highwaySouth = new Highway();
        highwaySouth.addCar(3, 3, Tiredness.REFRESHED);
        highwaySouth.addCar(5, 1, Tiredness.TIRED);
        highwaySouth.addCar(7, 1, Tiredness.REFRESHED);
        Highway highwayWest = new Highway();
        highwayWest.addCar(3, 3, Tiredness.REFRESHED);
        highwayWest.addCar(5, 1, Tiredness.TIRED);
        highwayWest.addCar(7, 1, Tiredness.REFRESHED);
        
        HighwaysManager.setUp(highwayNorth, highwayEast, highwaySouth, highwayWest);
        
        HighwaysManager.debug();
    }
    
}
