package pl.edu.agh.agenty.project6.agents;

import pl.edu.agh.agenty.project6.traffic.RoadConstants;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class TrafficLight extends Thread implements Agent {

    private int greenTime; //in milliseconds
    private int yellowTime;
    private int redTime;
    private int phase;
    private LightColor currentLightColor;

    public TrafficLight(int redTime, int yellowTime, int greenTime, int phase) {
        this.redTime = redTime;
        this.yellowTime = yellowTime;
        this.greenTime = greenTime;
        this.phase = phase;
        currentLightColor = LightColor.RED;
    }

    public boolean isGreen() {
        return currentLightColor == LightColor.GREEN;
    }

    public boolean isYellow() {
        return currentLightColor == LightColor.YELLOW;
    }

    public boolean isRed() {
        return currentLightColor == LightColor.RED;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        this.yellowTime = yellowTime;
    }

    public int getRedTime() {
        return redTime;
    }

    public void setRedTime(int redTime) {
        this.redTime = redTime;
    }

    public synchronized LightColor getCurrentLightColor() {
        return currentLightColor;
    }

    public void setCurrentLightColor(LightColor currentLightColor) {
        this.currentLightColor = currentLightColor;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(phase);
            while(true) {
                switch (currentLightColor) {
                    case RED:
                        Thread.sleep(redTime);
                        setCurrentLightColor(LightColor.GREEN);
                        break;
                    case YELLOW:
                        Thread.sleep(yellowTime);
                        setCurrentLightColor(LightColor.RED);
                        break;
                    case GREEN:
                        Thread.sleep(greenTime);
                        setCurrentLightColor(LightColor.YELLOW);
                        break;
                    default:
                        break;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("TrafficLight agent's thread interrupted.");
        }
    }
}
