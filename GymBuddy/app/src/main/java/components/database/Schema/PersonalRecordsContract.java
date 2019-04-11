package components.database.Schema;

import android.provider.BaseColumns;

import java.util.ArrayList;

public class PersonalRecordsContract {

    private PersonalRecordsContract() {
    }

    public static String[] createSQLEntries() {

        ArrayList<String> list = new ArrayList<>();


        String currentQuery =
                "CREATE TABLE IF NOT EXISTS " + PersonalRecordsEntry.TABLE_NAME + " (" +
                        PersonalRecordsEntry._ID + " INTEGER PRIMARY KEY," +
                        PersonalRecordsEntry.COLUMN_NAME_BENCHPR + " INTEGER," +
                        PersonalRecordsEntry.COLUMN_NAME_DEADLIFTPR + " INTEGER," +
                        PersonalRecordsEntry.COLUMN_NAME_PRESSPR + " INTEGER," +
                        PersonalRecordsEntry.COLUMN_NAME_SQUATPR + " INTEGER," +
                        PersonalRecordsEntry.COLUMN_NAME_DATE + " DATE)";
        list.add(currentQuery);


        String[] returnList = new String[list.size()];
        returnList = list.toArray(returnList);
        return returnList;
    }

    public static class PersonalRecordsEntry implements BaseColumns {

        private static final String TABLE_NAME = "homeScreen_personalRecords";
        private static final String COLUMN_NAME_BENCHPR = "bench";
        private static final String COLUMN_NAME_DEADLIFTPR = "deadlift";
        private static final String COLUMN_NAME_PRESSPR = "press";
        private static final String COLUMN_NAME_SQUATPR = "squat";
        private static final String COLUMN_NAME_DATE = "date";

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getColumnNameBenchpr() {
            return COLUMN_NAME_BENCHPR;
        }

        public static String getColumnNameDeadliftpr() {
            return COLUMN_NAME_DEADLIFTPR;
        }

        public static String getColumnNamePresspr() {
            return COLUMN_NAME_PRESSPR;
        }

        public static String getColumnNameSquatpr() {
            return COLUMN_NAME_SQUATPR;
        }

        public static String getColumnNameDate() {
            return COLUMN_NAME_DATE;
        }


    }


}
