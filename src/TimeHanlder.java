
import java.io.*;

public class TimeHanlder {

    private long lastStartTime;



    private boolean running = false;


    private long elapsedTime = 0;


    public void start() {
        if (running) return;
        running = true;
        lastStartTime = System.nanoTime();
    }


    public double stop() {
        if (running) {
            running = false;
            elapsedTime = elapsedTime + System.nanoTime() - lastStartTime;
        }
        return getElapsedTime();
    }


    public double getElapsedTime() {
        return  (double) elapsedTime;
    }





    public void reset() {
        elapsedTime = 0;
        running = false;
    }

}