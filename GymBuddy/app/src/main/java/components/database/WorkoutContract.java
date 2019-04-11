package components.database;

import android.provider.BaseColumns;

import java.util.ArrayList;

public final class WorkoutContract {

    //Implement Schema here

    private WorkoutContract() {
    }

    public static String[] createSQLEntries() {

        ArrayList<String> list = new ArrayList<>();


        String currentQuery =
                "CREATE TABLE " + WorkoutTemplateEntry.TABLE_NAME + " (" +
                        WorkoutTemplateEntry._ID + " INTEGER PRIMARY KEY," +
                        WorkoutTemplateEntry.COLUMN_NAME_NAME + " TEXT," +
                        WorkoutTemplateEntry.COLUMN_NAME_DATE + " DATE)";
        list.add(currentQuery);

        currentQuery =
                "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " (" +
                        WorkoutEntry._ID + " INTEGER PRIMARY KEY," +
                        WorkoutEntry.COLUMN_NAME_WORKOUT_TEMPLATE_ID + " INTEGER," +
                        WorkoutEntry.COLUMN_NAME_NAME + " TEXT," +
                        "FOREIGN KEY (" + WorkoutEntry.COLUMN_NAME_WORKOUT_TEMPLATE_ID + ") REFERENCES " + WorkoutTemplateEntry.TABLE_NAME + "(" + WorkoutTemplateEntry._ID + ")" +
                        ")";

        list.add(currentQuery);

        currentQuery =
                "CREATE TABLE " + SetEntry.TABLE_NAME + " (" +
                        SetEntry._ID + " INTEGER PRIMARY KEY," +
                        SetEntry.COLUMN_NAME_SET_NUMBER + " INTEGER," +
                        SetEntry.COLUMN_NAME_SET_REPS + " INTEGER," +
                        SetEntry.COLUMN_NAME_WORKOUT_ID + " INTEGER," +
                        SetEntry.COLUMN_NAME_SET_WEIGHT + " INTEGER," +
                        "FOREIGN KEY (" + SetEntry.COLUMN_NAME_WORKOUT_ID + ") REFERENCES " + WorkoutEntry.TABLE_NAME + "(" + WorkoutEntry._ID + ")" +
                        ")";

        list.add(currentQuery);

        String[] returnList = new String[list.size()];
        returnList = list.toArray(returnList);
        return returnList;
    }

    public static class WorkoutTemplateEntry implements BaseColumns {

        private static final String TABLE_NAME = "workoutTemplate";
        private static final String COLUMN_NAME_NAME = "name";
        private static final String COLUMN_NAME_DATE = "date";


    }

    public static class WorkoutEntry implements BaseColumns {

        private static final String TABLE_NAME = "workout";
        private static final String COLUMN_NAME_NAME = "name";
        private static final String COLUMN_NAME_WORKOUT_TEMPLATE_ID = "workout_template_id";


    }

    public static class SetEntry implements BaseColumns {

        private static final String TABLE_NAME = "aset";
        private static final String COLUMN_NAME_SET_NUMBER = "number";
        private static final String COLUMN_NAME_SET_WEIGHT = "weight";
        private static final String COLUMN_NAME_SET_REPS = "reps";
        private static final String COLUMN_NAME_WORKOUT_ID = "workout_id";


    }


}
