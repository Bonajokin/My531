package com.example.gymbuddy;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import components.Workout;
import components.database.Schema.WorkoutContract;

public class workoutScreen extends AppCompatActivity {

    public static boolean isNewTemplate;
    private ArrayList<Workout> workouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_screen);

        if (loadWorkoutTemplate()) {
            for (Workout workout : workouts) {

            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {

                // Make a new workout


                final Workout template = new Workout("New Template", (LinearLayout) findViewById(R.id.ws_LinearLayout), (RelativeLayout) findViewById(R.id.toolbar), getLayoutInflater(), getApplicationContext());
                workouts.add(template);

                Snackbar.make(view, "New Section Added", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                // Delete the most recently added workout template
                                LinearLayout layout = findViewById(R.id.ws_LinearLayout);
                                layout.removeViewAt(layout.indexOfChild(template.getHeaderView()));
                                layout.removeViewAt(layout.indexOfChild(template.getSetView()));
                                layout.removeViewAt(layout.indexOfChild(template.getFooterView()));


                            }


                        }).show();


            }
        });




    }


    private boolean loadWorkoutTemplate() {

        try {
            String[] projection = {
                    WorkoutContract.WorkoutTemplateEntry.getColumnNameName(),
                    WorkoutContract.WorkoutTemplateEntry.getColumnNameWorkout()
            };
            String selection = WorkoutContract.WorkoutTemplateEntry._ID + " = ?";
            String[] selectionArgs = {"1"};


            Cursor cursor = homeScreen.db.query(
                    WorkoutContract.WorkoutTemplateEntry.getTableName(),   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            byte[] workoutTemplate = cursor.getBlob(cursor.getColumnIndex(WorkoutContract.WorkoutTemplateEntry.getColumnNameWorkout()));
            String json = new String(workoutTemplate);
            Gson gson = new Gson();
            workouts = gson.fromJson(json, new TypeToken<ArrayList<Workout>>() {
            }.getType());

            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public void saveWorkoutTemplate() {

        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(WorkoutContract.WorkoutTemplateEntry.getColumnNameWorkout(), gson.toJson(workouts).getBytes());

    }

}


