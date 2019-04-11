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
                        TrainingMaxesEntry.COLUMN_NAME_BENCH + " INTEGER," +
                        TrainingMaxesEntry.COLUMN_NAME_DEADLIFT + " INTEGER," +
                        TrainingMaxesEntry.COLUMN_NAME_PRESS + " INTEGER," +
                        TrainingMaxesEntry.COLUMN_NAME_SQUAT + " INTEGER)";

        list.add(currentQuery);

        currentQuery = "CREATE TABLE IF NOT EXISTS " + TrainingCalculationsEntry.TABLE_NAME + " (" +
                TrainingCalculationsEntry._ID + " INTEGER PRIMARY KEY," +
                TrainingCalculationsEntry.COLUMN_NAME_BENCH_REPS + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_BENCH_WEIGHT + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_DEADLIFT_REPS + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_DEADLIFT_WEIGHT + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_PRESS_REPS + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_PRESS_WEIGHT + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_SQUAT_REPS + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_SQUAT_WEIGHT + " INTEGER," +
                TrainingCalculationsEntry.COLUMN_NAME_TRAINING_MAX_PERCENTAGE + " REAL)";

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

    public static class TrainingCalculationsEntry implements BaseColumns {

        private static final String TABLE_NAME = "trainingMaxScreen_TrainingCalculations";
        private static final String COLUMN_NAME_BENCH_REPS = "bench_reps";
        private static final String COLUMN_NAME_BENCH_WEIGHT = "bench_weight";
        private static final String COLUMN_NAME_DEADLIFT_REPS = "deadlift_reps";
        private static final String COLUMN_NAME_DEADLIFT_WEIGHT = "deadlift_weight";
        private static final String COLUMN_NAME_PRESS_REPS = "press_reps";
        private static final String COLUMN_NAME_PRESS_WEIGHT = "press_weight";
        private static final String COLUMN_NAME_SQUAT_REPS = "squat_reps";
        private static final String COLUMN_NAME_SQUAT_WEIGHT = "squat_weight";
        private static final String COLUMN_NAME_TRAINING_MAX_PERCENTAGE = "training_max_percentage";

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getColumnNameBenchReps() {
            return COLUMN_NAME_BENCH_REPS;
        }

        public static String getColumnNameBenchWeight() {
            return COLUMN_NAME_BENCH_WEIGHT;
        }

        public static String getColumnNameDeadliftReps() {
            return COLUMN_NAME_DEADLIFT_REPS;
        }

        public static String getColumnNameDeadliftWeight() {
            return COLUMN_NAME_DEADLIFT_WEIGHT;
        }

        public static String getColumnNamePressReps() {
            return COLUMN_NAME_PRESS_REPS;
        }

        public static String getColumnNamePressWeight() {
            return COLUMN_NAME_PRESS_WEIGHT;
        }

        public static String getColumnNameSquatReps() {
            return COLUMN_NAME_SQUAT_REPS;
        }

        public static String getColumnNameSquatWeight() {
            return COLUMN_NAME_SQUAT_WEIGHT;
        }

        public static String getColumnNameTrainingMaxPercentage() {
            return COLUMN_NAME_TRAINING_MAX_PERCENTAGE;
        }

    }


}
