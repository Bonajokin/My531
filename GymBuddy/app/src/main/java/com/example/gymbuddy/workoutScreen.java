package com.example.gymbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import components.WorkoutTemplate;

public class workoutScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {

                Snackbar.make(view, "New Section Added", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                // Insert undo section section logic here.


                            }


                        }).show();

                //Build the workout header, content, footer from templates

                WorkoutTemplate template = new WorkoutTemplate("Testing", 0, 0, (LinearLayout) findViewById(R.id.ws_LinearLayout), getLayoutInflater(), getApplicationContext());



            }
        });




    }


}


