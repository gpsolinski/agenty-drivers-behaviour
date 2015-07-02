package pl.edu.agh.agenty.project6;


import pl.edu.agh.agenty.project6.traffic.ProbabilityGenerator;


public class Main {
	
    public static void main(String[] args) {
        ProbabilityGenerator.setUp();

        Platform platform = Platform.getInstance();

        Thread shutdownHook = new Thread() {

            @Override
            public void run()
            {
                System.out.println("Number of collisions: " + platform.getIntersection().collisions);
                System.out.println("Number of cars turning right: " + platform.getIntersection().getNumberOfCarsTurningRight());
                System.out.println("Number of cars going straight: " + platform.getIntersection().getNumberOfCarsGoingStraight());
                System.out.println("Number of cars turning left: " + platform.getIntersection().getNumberOfCarsTurningLeft());
                System.out.println("Average waiting time to turn right: " + (double)platform.getIntersection().getTotalWaitingTimeRight()/platform.getIntersection().getNumberOfCarsTurningRight());
                System.out.println("Average waiting time to go straight: " + (double)platform.getIntersection().getTotalWaitingTimeStraight()/platform.getIntersection().getNumberOfCarsGoingStraight());
                System.out.println("Average waiting time to turn left: " + (double)platform.getIntersection().getTotalWaitingTimeLeft()/platform.getIntersection().getNumberOfCarsTurningLeft());
            }
        };

        Runtime.getRuntime().addShutdownHook(shutdownHook);

        platform.start();

    }
    
}
