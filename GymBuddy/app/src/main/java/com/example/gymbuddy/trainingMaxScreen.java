package com.example.gymbuddy;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
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

        boolean loadSuccessful;
        loadSuccessful = loadTrainingMaxes();

        if (loadSuccessful) {

            calculate.setText(getString(R.string.trainingMaxes_Edit));

        } else {

            calculate.setText(getString(R.string.trainingMaxes_Save));

        }


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

                values = new ContentValues();
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameBenchReps(), benchReps.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameBenchWeight(), benchWeight.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameDeadliftReps(), deadliftReps.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameDeadliftWeight(), deadliftWeight.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNamePressReps(), pressReps.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNamePressWeight(), pressWeight.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameSquatReps(), squatReps.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameSquatWeight(), squatWeight.getText().toString());
                values.put(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameTrainingMaxPercentage(), trainingMax.getText().toString());

                newRowId = homeScreen.db.insert(TrainingMaxesContract.TrainingCalculationsEntry.getTableName(), null, values);

                Snackbar.make(benchWeight, "Saved!", Snackbar.LENGTH_SHORT).show();
            }


        });


    }

    private boolean loadTrainingMaxes() {


        trainingMaxes = new TrainingMax[5];

        calculate = findViewById(R.id.tm_submitButton);

        benchWeight = findViewById(R.id.tm_benchWeight);
        benchReps = findViewById(R.id.tm_benchReps);
        deadliftWeight = findViewById(R.id.tm_deadliftWeight);
        deadliftReps = findViewById(R.id.tm_deadLiftReps);
        pressWeight = findViewById(R.id.tm_pressWeight);
        pressReps = findViewById(R.id.tm_pressReps);
        squatWeight = findViewById(R.id.tm_squatWeight);
        squatReps = findViewById(R.id.tm_squatReps);
        trainingMax = findViewById(R.id.tm_percentage);

        String benchRepsQueryResult;
        String benchWeightQueryResult;
        String deadliftRepsQueryResult;
        String deadliftWeightQueryResult;
        String pressRepsQueryResult;
        String pressWeightQueryResult;
        String squatRepsQueryResult;
        String squatWeightQueryResult;
        String trainingMaxPercentageQueryResult;

        String[] projection = {
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameBenchReps(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameBenchWeight(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameDeadliftReps(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameDeadliftWeight(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNamePressReps(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNamePressWeight(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameSquatReps(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameSquatWeight(),
                TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameTrainingMaxPercentage()

        };

        String selection = TrainingMaxesContract.TrainingCalculationsEntry._ID + " = ?";
        String[] selectionArgs = {"1"};


        Cursor cursor = homeScreen.db.query(
                TrainingMaxesContract.TrainingCalculationsEntry.getTableName(),   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToNext();
        try {

            benchRepsQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameBenchReps()));
            benchWeightQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameBenchWeight()));
            deadliftRepsQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameDeadliftReps()));
            deadliftWeightQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameDeadliftWeight()));
            pressRepsQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNamePressReps()));
            pressWeightQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNamePressWeight()));
            squatRepsQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameSquatReps()));
            squatWeightQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameSquatWeight()));
            trainingMaxPercentageQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingCalculationsEntry.getColumnNameTrainingMaxPercentage()));

            benchReps.setText(benchRepsQueryResult);
            benchWeight.setText(benchWeightQueryResult);
            deadliftReps.setText(deadliftRepsQueryResult);
            deadliftWeight.setText(deadliftWeightQueryResult);
            pressReps.setText(pressRepsQueryResult);
            pressWeight.setText(pressWeightQueryResult);
            squatReps.setText(squatRepsQueryResult);
            squatWeight.setText(squatWeightQueryResult);
            trainingMax.setText(trainingMaxPercentageQueryResult);

            cursor.close();
            return true;

        } catch (CursorIndexOutOfBoundsException e) {

            benchReps.setHint(String.valueOf(0));
            benchWeight.setHint(String.valueOf(0));
            deadliftReps.setHint(String.valueOf(0));
            deadliftWeight.setHint(String.valueOf(0));
            pressReps.setHint(String.valueOf(0));
            pressWeight.setHint(String.valueOf(0));
            squatReps.setHint(String.valueOf(0));
            squatWeight.setHint(String.valueOf(0));
            trainingMax.setHint(String.valueOf(0));

            cursor.close();
            return false;

        }


    }


}
