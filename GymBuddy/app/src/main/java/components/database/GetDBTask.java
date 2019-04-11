package components.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.gymbuddy.homeScreen;

public class GetDBTask extends AsyncTask<Context, Void, SQLiteDatabase> {

    homeScreen parent;

    public GetDBTask(homeScreen activity) {
        parent = activity;
    }

    @Override
    protected SQLiteDatabase doInBackground(Context... contexts) {
        DatabaseHelper dbHelper = new DatabaseHelper(contexts[0]);
        return dbHelper.getWritableDatabase();
    }


    @Override
    protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {

        homeScreen.db = sqLiteDatabase;
        parent.updatePR();
    }
}
