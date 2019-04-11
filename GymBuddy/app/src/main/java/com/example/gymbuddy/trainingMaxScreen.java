package com.example.gymbuddy;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import components.TrainingMax;
import components.database.Schema.TrainingMaxesContract;

public class trainingMaxScreen extends AppCompatActivity implements Serializable {

    private TrainingMax[] trainingMaxes = null;

    TextView benchWeight;
    TextView benchReps;
    TextView deadliftWeight;
    TextView deadliftReps;
    TextView pressWeight;
    TextView pressReps;
    TextView squatWeight;
    TextView squatReps;
    TextView trainingMax;
    TextView debugBox;

    Button calculate;


    public TrainingMax[] getTrainingMaxes() {

        return trainingMaxes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_maxes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        trainingMaxes = new TrainingMax[5];

        calculate = findViewById(R.id.tm_submitButton);

        //If saving them change button text to save else change it to edit if editing.
        if (true) {

            calculate.setText(getString(R.string.trainingMaxes_Save));

        } else {

            calculate.setText(getString(R.string.trainingMaxes_Edit));
        }

        benchWeight = findViewById(R.id.tm_benchWeight);
        benchReps = findViewById(R.id.tm_benchReps);
        deadliftWeight = findViewById(R.id.tm_deadliftWeight);
        deadliftReps = findViewById(R.id.tm_deadLiftReps);
        pressWeight = findViewById(R.id.tm_pressWeight);
        pressReps = findViewById(R.id.tm_pressReps);
        squatWeight = findViewById(R.id.tm_squatWeight);
        squatReps = findViewById(R.id.tm_squatReps);
        trainingMax = findViewById(R.id.tm_percentage);
        //debugBox = findViewById(R.id.debugTextBox);


        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                trainingMaxes[0] = new TrainingMax(Integer.parseInt(benchReps.getText().toString()), Integer.parseInt(benchWeight.getText().toString()), Double.parseDouble(trainingMax.getText().toString()));
                trainingMaxes[1] = new TrainingMax(Integer.parseInt(deadliftReps.getText().toString()), Integer.parseInt(deadliftWeight.getText().toString()), Double.parseDouble(trainingMax.getText().toString()));
                trainingMaxes[2] = new TrainingMax(Integer.parseInt(pressReps.getText().toString()), Integer.parseInt(pressWeight.getText().toString()), Double.parseDouble(trainingMax.getText().toString()));
                trainingMaxes[3] = new TrainingMax(Integer.parseInt(squatReps.getText().toString()), Integer.parseInt(squatWeight.getText().toString()), Double.parseDouble(trainingMax.getText().toString()));

                ContentValues values = new ContentValues();
                values.put(TrainingMaxesContract.TrainingMaxesEntry.getColumnNameBench(), trainingMaxes[0].getTrainingMax());
                values.put(TrainingMaxesContract.TrainingMaxesEntry.getColumnNameDeadlift(), trainingMaxes[1].getTrainingMax());
                values.put(TrainingMaxesContract.TrainingMaxesEntry.getColumnNamePress(), trainingMaxes[2].getTrainingMax());
                values.put(TrainingMaxesContract.TrainingMaxesEntry.getColumnNameSquat(), trainingMaxes[3].getTrainingMax());

                long newRowId = homeScreen.db.insert(TrainingMaxesContract.TrainingMaxesEntry.getTableName(), null, values);

                Snackbar.make(v, "Saved!", Snackbar.LENGTH_SHORT).show();

                /*
                Intent result = new Intent();
                result.putExtra("Training Maxes", trainingMaxes);
                setResult(Activity.RESULT_OK, result);
                trainingMaxScreen.this.finish();
                */

                //debugBox.setText("Bench = " + trainingMaxes[0].getTrainingMax() + ", Deadlift = " + trainingMaxes[1].getTrainingMax() + ", Press = " + trainingMaxes[2].getTrainingMax() + ", Squat = " + trainingMaxes[3].getTrainingMax());


            }


        });


    }


}
