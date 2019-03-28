package components;

import android.os.AsyncTask;

import java.util.Date;

public class CountdownTimer extends AsyncTask {

    private boolean isActive;
    private long remainingTime;
    private long startTime;
    private long endTime;
    private Thread thread;
    private int timeRequested;


    public CountdownTimer(int seconds) {

        remainingTime = 0;
        startTime = 0;
        endTime = 0;
        isActive = false;
        thread = new Thread("Countdown Timer");
        timeRequested = seconds;


    }

    public int getTimeRemaining() {


        return (int) remainingTime / 1000;


    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }

    private void start(int seconds) {


        startTime = System.currentTimeMillis();
        endTime = (seconds * 1000) + startTime;
        isActive = true;

        int currentSecond;
        int lastSecond = 0;

        do {

            remainingTime = endTime - (new Date()).getTime();
            currentSecond = getTimeRemaining();

            if (currentSecond != lastSecond) {

                lastSecond = currentSecond;

            }
        } while (remainingTime >= 0);

        isActive = false;


    }


}