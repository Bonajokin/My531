package com.example.gymbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import components.TrainingMax;

public class homeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView benchPR;
    TextView deadliftPR;
    TextView pressPR;
    TextView squatPR;

    private static final int SET_TRAININGMAXES_REQUESTCODE = 1;
    private static final int EDIT_TRAININGMAXES_REQUESTCODE = 2;

    private TrainingMax[] trainingMaxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        benchPR = findViewById(R.id.hs_benchPR);
        deadliftPR = findViewById(R.id.hs_deadliftPR);
        pressPR = findViewById(R.id.hs_pressPR);
        squatPR = findViewById(R.id.hs_squatPR);

        if (trainingMaxes == null) {

            benchPR.setText("0");
            deadliftPR.setText("0");
            pressPR.setText("0");
            squatPR.setText("0");

        } else {

            updatePR();

        }
    }


    public void updatePR() {

        benchPR.setText(String.valueOf(trainingMaxes[0].getTrainingMax()));
        deadliftPR.setText(String.valueOf(trainingMaxes[1].getTrainingMax()));
        pressPR.setText(String.valueOf(trainingMaxes[2].getTrainingMax()));
        squatPR.setText(String.valueOf(trainingMaxes[3].getTrainingMax()));


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setTM) {
            //Set training Maxes, change icon later


            //If training maxes were already loaded allow them to be edited. Edit this later when saving and loading is implemented.

            if (false) {

                Intent trainingMaxIntent = new Intent(homeScreen.this, trainingMaxScreen.class);
                homeScreen.this.startActivityForResult(trainingMaxIntent, EDIT_TRAININGMAXES_REQUESTCODE);

            } else {

                //If no training maxes are active allow setting them
                Intent trainingMaxIntent = new Intent(homeScreen.this, trainingMaxScreen.class);
                homeScreen.this.startActivityForResult(trainingMaxIntent, SET_TRAININGMAXES_REQUESTCODE);

            }
        } else if (id == R.id.nav_workout) {

            //Goto the current  if one is active here.

            Intent workoutIntent = new Intent(homeScreen.this, workoutScreen.class);
            homeScreen.this.startActivity(workoutIntent);


        } else if (id == R.id.nav_progress_chart) {

            Snackbar.make(getCurrentFocus(), "Feature not implemented yet.", Snackbar.LENGTH_SHORT).show();

        } else if (id == R.id.nav_settings) {

            Snackbar.make(getCurrentFocus(), "Feature not implemented yet.", Snackbar.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {

            Snackbar.make(getCurrentFocus(), "Feature not implemented yet.", Snackbar.LENGTH_SHORT).show();


        } else if (id == R.id.nav_export) {

            Snackbar.make(getCurrentFocus(), "Feature not implemented yet.", Snackbar.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case (SET_TRAININGMAXES_REQUESTCODE): {

                if (resultCode == Activity.RESULT_OK) {
                    try {

                        trainingMaxes = (TrainingMax[]) data.getSerializableExtra("Training Maxes");

                    } catch (NullPointerException e) {

                        trainingMaxes = null;

                    }
                    updatePR();
                }

            }

            case (EDIT_TRAININGMAXES_REQUESTCODE): {

                if (resultCode == Activity.RESULT_OK) {

                    try {

                        trainingMaxes = (TrainingMax[]) data.getSerializableExtra("Training Maxes");

                    } catch (NullPointerException e) {

                        trainingMaxes = null;

                    }
                    updatePR();
                }


            }


        }
    }
}
