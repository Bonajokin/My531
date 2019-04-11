package com.example.gymbuddy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
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
import components.database.GetDBTask;
import components.database.Schema.PersonalRecordsContract;
import components.database.Schema.TrainingMaxesContract;


public class homeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView benchPR;
    TextView deadliftPR;
    TextView pressPR;
    TextView squatPR;


    public static SQLiteDatabase db;
    private static final int SET_TRAININGMAXES_REQUESTCODE = 1;
    private static final int EDIT_TRAININGMAXES_REQUESTCODE = 2;

    private TrainingMax[] trainingMaxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GetDBTask dbTask = new GetDBTask(this);

        dbTask.execute(getApplicationContext());

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

    }

    public void updatePR() {

        benchPR = findViewById(R.id.hs_benchPR);
        deadliftPR = findViewById(R.id.hs_deadliftPR);
        pressPR = findViewById(R.id.hs_pressPR);
        squatPR = findViewById(R.id.hs_squatPR);


        //Fix the false here to check fro personal records
        //If Personal records haven't been set yet, try to set them to training maxes, if they aren't set yet just set them to 0;
        try {

            loadPRsFromDB();


        } catch (CursorIndexOutOfBoundsException e) {

            loadTMPRSFromDB();

        }


    }

    private void loadTMPRSFromDB() {

        String benchQueryResult;
        String deadliftQueryResult;
        String pressQueryResult;
        String squatQueryResult;

        String[] projection = {
                TrainingMaxesContract.TrainingMaxesEntry.getColumnNameBench(),
                TrainingMaxesContract.TrainingMaxesEntry.getColumnNameDeadlift(),
                TrainingMaxesContract.TrainingMaxesEntry.getColumnNamePress(),
                TrainingMaxesContract.TrainingMaxesEntry.getColumnNameSquat()
        };

        String selection = TrainingMaxesContract.TrainingMaxesEntry._ID + " = ?";
        String[] selectionArgs = {"1"};


        Cursor cursor = db.query(
                TrainingMaxesContract.TrainingMaxesEntry.getTableName(),   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToNext();
        try {
            benchQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingMaxesEntry.getColumnNameBench()));
            deadliftQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingMaxesEntry.getColumnNameDeadlift()));
            pressQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingMaxesEntry.getColumnNamePress()));
            squatQueryResult = cursor.getString(cursor.getColumnIndex(TrainingMaxesContract.TrainingMaxesEntry.getColumnNameSquat()));

            benchPR.setText(benchQueryResult);
            deadliftPR.setText(deadliftQueryResult);
            pressPR.setText(pressQueryResult);
            squatPR.setText(squatQueryResult);

            cursor.close();
        } catch (CursorIndexOutOfBoundsException e) {

            benchPR.setText(String.valueOf(0));
            deadliftPR.setText(String.valueOf(0));
            pressPR.setText(String.valueOf(0));
            squatPR.setText(String.valueOf(0));

            cursor.close();

        }


    }

    private void loadPRsFromDB() {

        String benchQueryResult;
        String deadliftQueryResult;
        String pressQueryResult;
        String squatQueryResult;

        String[] projection = {
                PersonalRecordsContract.PersonalRecordsEntry.getColumnNameBenchpr(),
                PersonalRecordsContract.PersonalRecordsEntry.getColumnNameDeadliftpr(),
                PersonalRecordsContract.PersonalRecordsEntry.getColumnNamePresspr(),
                PersonalRecordsContract.PersonalRecordsEntry.getColumnNameSquatpr()

        };

        String selection = TrainingMaxesContract.TrainingMaxesEntry._ID + " = ?";
        String[] selectionArgs = {"1"};


        Cursor cursor = db.query(
                TrainingMaxesContract.TrainingMaxesEntry.getTableName(),   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToNext();
        benchQueryResult = cursor.getString(cursor.getColumnIndex(PersonalRecordsContract.PersonalRecordsEntry.getColumnNameBenchpr()));
        deadliftQueryResult = cursor.getString(cursor.getColumnIndex(PersonalRecordsContract.PersonalRecordsEntry.getColumnNameDeadliftpr()));
        pressQueryResult = cursor.getString(cursor.getColumnIndex(PersonalRecordsContract.PersonalRecordsEntry.getColumnNamePresspr()));
        squatQueryResult = cursor.getString(cursor.getColumnIndex(PersonalRecordsContract.PersonalRecordsEntry.getColumnNameSquatpr()));

        benchPR.setText(benchQueryResult);
        deadliftPR.setText(deadliftQueryResult);
        pressPR.setText(pressQueryResult);
        squatPR.setText(squatQueryResult);

        cursor.close();


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


            Intent trainingMaxIntent = new Intent(homeScreen.this, trainingMaxScreen.class);
            homeScreen.this.startActivity(trainingMaxIntent);



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
