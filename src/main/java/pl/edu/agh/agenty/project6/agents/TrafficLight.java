package pl.edu.agh.agenty.project6.agents;

/**
 * Created by grzegorz on 2015-05-20.
 */
public class TrafficLight extends Thread {

    private int greenTime; //in milliseconds
    private int yellowTime;
    private int redTime;
    private LightColor currentLightColorX;
    private LightColor currentLightColorY;

    public TrafficLight(int greenTime, int yellowTime) {
        this.greenTime = greenTime;
        this.yellowTime = yellowTime;
        this.redTime = greenTime + yellowTime;
        currentLightColorX = LightColor.RED;
        currentLightColorY = LightColor.YELLOW;
    }

    public boolean isGreenX() {
        return currentLightColorX == LightColor.GREEN;
    }

    public boolean isGreenY() {
        return currentLightColorY == LightColor.GREEN;
    }

    public boolean isYellowX() {
        return currentLightColorX == LightColor.YELLOW;
    }

    public boolean isYellowY() {
        return currentLightColorY == LightColor.YELLOW;
    }

    public boolean isRedX() {
        return currentLightColorX == LightColor.RED;
    }

    public boolean isRedY() {
        return currentLightColorY == LightColor.RED;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        this.yellowTime = yellowTime;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public int getRedTime() {
        return redTime;
    }

    public synchronized LightColor getCurrentLightColorX() {
        return currentLightColorX;
    }

    public void setCurrentLightColorX(LightColor currentLightColorX) {
        this.currentLightColorX = currentLightColorX;
    }

    public void setCurrentLightColorY(LightColor currentLightColorY) {
        this.currentLightColorY = currentLightColorY;
    }
    @Override
    public void run() {
        try {
            while(true) {
                switch (currentLightColorX) {
                    case RED:
                        Thread.sleep(yellowTime);
                        setCurrentLightColorY(LightColor.GREEN);
                        Thread.sleep(greenTime);
                        setCurrentLightColorY(LightColor.RED);
                        setCurrentLightColorX(LightColor.YELLOW);
                        break;
                    case YELLOW:
                        Thread.sleep(yellowTime);
                        setCurrentLightColorX(LightColor.GREEN);
                        break;
                    case GREEN:
                        Thread.sleep(greenTime);
                        setCurrentLightColorX(LightColor.RED);
                        setCurrentLightColorY(LightColor.YELLOW);
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

    public String toStringX() {
        switch (currentLightColorX) {
            case RED:
                return "R";
            case YELLOW:
                return "Y";
            case GREEN:
                return "G";
            default:
                return "R";
        }
    }

    public String toStringY() {
        switch (currentLightColorY) {
            case RED:
                return "R";
            case YELLOW:
                return "Y";
            case GREEN:
                return "G";
            default:
                return "R";
        }
    }
}
