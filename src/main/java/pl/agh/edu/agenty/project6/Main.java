package pl.agh.edu.agenty.project6;

import pl.agh.edu.agenty.project6.traffic.Car;
import pl.agh.edu.agenty.project6.traffic.ProbabilityGenerator;
import pl.agh.edu.agenty.project6.traffic.Road;


public class Main {
	
    public static void main(String[] args) {
        Road road = new Road();
        
        ProbabilityGenerator.setUp();
        
        Car car1 = new Car(0, 0, road);
        Car car2 = new Car(4, 0, road);
        Car car3 = new Car(5, 0, road);
        
        Thread thread1 = (new Thread(car1));
        Thread thread2 = (new Thread(car2));
        Thread thread3 = (new Thread(car3));
        
        road.setLoggingThread(thread3.getId());
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
}
