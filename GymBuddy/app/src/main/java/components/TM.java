package components;

import java.io.Serializable;

public class TM implements Serializable {

    private final double constant = 0.0333;
    private int trainingMax;
    private int reps;
    private int weight;
    private double maxPercentage;


    public TM(int reps, int weight, double maxPercent) {

        this.reps = reps;
        this.weight = weight;
        this.maxPercentage = maxPercent;
        this.trainingMax = (int) ((maxPercentage / 100) * calculate());

    }


    private int calculate() {


        trainingMax = (int) (weight * reps * constant + weight);
        return trainingMax;
    }

    public int getTrainingMax() {

        return trainingMax;

    }

    public void trainingMaxPercentage(int percent) {

        maxPercentage = percent / 100;

    }


}
