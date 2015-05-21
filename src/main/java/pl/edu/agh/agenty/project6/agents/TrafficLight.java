package pl.edu.agh.agenty.project6.agents;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class TrafficLight implements Agent {

    private int greenTime; //in milliseconds
    private int redTime;

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public int getRedTime() {
        return redTime;
    }

    public void setRedTime(int redTime) {
        this.redTime = redTime;
    }
}
