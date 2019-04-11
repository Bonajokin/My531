package components.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "GymBuddy.db";
    public static final int DB_VERSION = 1;


    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Current set to only create the tables in the workout page go back and change this to create the entire database later.

        for (String query : WorkoutContract.createSQLEntries()) {
            db.execSQL(query);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
