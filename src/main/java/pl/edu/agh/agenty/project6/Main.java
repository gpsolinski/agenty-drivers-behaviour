package pl.edu.agh.agenty.project6;


import pl.edu.agh.agenty.project6.traffic.ProbabilityGenerator;


public class Main {
	
    public static void main(String[] args) {
        ProbabilityGenerator.setUp();

        Platform platform = Platform.getInstance();

        platform.start();
    }
    
}
