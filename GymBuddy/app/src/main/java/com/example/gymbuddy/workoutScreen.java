package com.example.gymbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import components.Workout;

public class workoutScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_screen);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {

                // Make a new workout template
                final Workout template = new Workout("Testing", 0, 0, (LinearLayout) findViewById(R.id.ws_LinearLayout), (RelativeLayout) findViewById(R.id.toolbar), getLayoutInflater(), getApplicationContext());

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


}


