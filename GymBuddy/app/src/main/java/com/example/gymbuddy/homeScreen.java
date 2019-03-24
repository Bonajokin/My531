package com.example.gymbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import components.TM;

public class homeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView benchPR;
    TextView deadliftPR;
    TextView pressPR;
    TextView squatPR;

    private static final int UPDATE_PR_REQUESTCODE = 1;


    private TM[] trainingMaxes;

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

        if (id == R.id.nav_camera) {
            //Set training Maxes, change icon later

            Intent trainingMaxIntent = new Intent(homeScreen.this, TrainingMaxes.class);
            homeScreen.this.startActivityForResult(trainingMaxIntent, UPDATE_PR_REQUESTCODE);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case (UPDATE_PR_REQUESTCODE): {

                if (resultCode == Activity.RESULT_OK) {
                    trainingMaxes = (TM[]) data.getSerializableExtra("Training Maxes");
                    updatePR();
                }

            }


        }
    }
}
