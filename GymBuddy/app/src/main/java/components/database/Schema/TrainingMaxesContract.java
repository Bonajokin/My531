package components.database.Schema;

import android.provider.BaseColumns;

import java.util.ArrayList;

public class TrainingMaxesContract {

    private TrainingMaxesContract() {
    }

    public static String[] createSQLEntries() {

        ArrayList<String> list = new ArrayList<>();


        String currentQuery =
                "CREATE TABLE IF NOT EXISTS " + TrainingMaxesEntry.TABLE_NAME + " (" +
                        TrainingMaxesEntry._ID + " INTEGER PRIMARY KEY," +
                        TrainingMaxesEntry.COLUMN_NAME_BENCH + " TEXT," +
                        TrainingMaxesEntry.COLUMN_NAME_DEADLIFT + " TEXT," +
                        TrainingMaxesEntry.COLUMN_NAME_PRESS + " TEXT," +
                        TrainingMaxesEntry.COLUMN_NAME_SQUAT + " TEXT)";

        list.add(currentQuery);


        String[] returnList = new String[list.size()];
        returnList = list.toArray(returnList);
        return returnList;
    }

    public static class TrainingMaxesEntry implements BaseColumns {

        private static final String TABLE_NAME = "trainingMaxScreen_trainingMaxes";
        private static final String COLUMN_NAME_BENCH = "bench";
        private static final String COLUMN_NAME_DEADLIFT = "deadlift";
        private static final String COLUMN_NAME_PRESS = "press";
        private static final String COLUMN_NAME_SQUAT = "squat";

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getColumnNameDeadlift() {
            return COLUMN_NAME_DEADLIFT;
        }

        public static String getColumnNamePress() {
            return COLUMN_NAME_PRESS;
        }

        public static String getColumnNameSquat() {
            return COLUMN_NAME_SQUAT;
        }

        public static String getColumnNameBench() {
            return COLUMN_NAME_BENCH;
        }


    }


}
