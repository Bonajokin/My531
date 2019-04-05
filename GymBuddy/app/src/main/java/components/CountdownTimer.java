package components;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.util.Date;


public class CountdownTimer extends AsyncTask<Void, Void, Boolean> {

    private long remainingTime;
    private long startTime;
    private long endTime;
    private int timeRequested;
    private TextView timerText;
    private boolean isActive;


    public CountdownTimer(int seconds, TextView textView) {

        remainingTime = 0;
        startTime = 0;
        endTime = 0;
        timeRequested = seconds;
        this.timerText = textView;

    }

    private int getCurrentTime() {

        return (int) (remainingTime / 1000);
    }

    public String getTimeRemaining() {
        String currentTime = "";


        if (getCurrentTime() % 60 >= 10) {
            currentTime = (getCurrentTime() / 60) + ":" + getCurrentTime() % 60;
        } else {
            currentTime = (getCurrentTime() / 60) + ":0" + getCurrentTime() % 60;
        }


        return currentTime;


    }

    public boolean isActive() {

        return isActive;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        start(timeRequested);
        return false;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        timerText.setVisibility(View.VISIBLE);
        timerText.setText(getTimeRemaining());
    }

    @Override
    protected void onCancelled() {
        timerText.setVisibility(View.INVISIBLE);
        timerText.setText("0:00");
    }

    @Override
    protected void onPostExecute(Boolean result) {

        timerText.setVisibility(View.INVISIBLE);
        isActive = false;

        //send a vibration notification here. ?


    }

    public void resetTimer(int seconds) {

        startTime = System.currentTimeMillis();
        endTime = (seconds * 1000) + startTime;

    }

    private void start(int seconds) {


        startTime = System.currentTimeMillis();
        endTime = (seconds * 1000) + startTime;

        isActive = true;
        do {

            remainingTime = endTime - (new Date()).getTime();
            publishProgress();

        } while (remainingTime >= 0);

        isActive = false;

    }


}