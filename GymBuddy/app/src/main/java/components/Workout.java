package components;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gymbuddy.R;

import java.io.Serializable;

public class Workout implements Serializable {

    private String name;

    private static int id = 0;

    private int setNum;
    private int weight;
    private int reps;
    private int restTimerPreferenceCode;

    private View view;
    private View headerView;
    private View footerView;
    private LinearLayout rootSetLayout;
    private LinearLayout layoutToInflate;
    private LayoutInflater inflater;

    private TextView workoutWeight;
    private TextView workoutReps;
    private TextView workoutSet;
    private TextView workoutTimerText;
    private CheckBox workoutCheckbox;
    private EditText workoutName;
    private ImageButton workoutAddButton;
    private ImageButton workoutDeleteButton;

    private Context currentContext;


    public Workout(String name, int weight, int reps, LinearLayout layout, LayoutInflater inflater, Context context) {

        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.setNum = 1;
        this.restTimerPreferenceCode = -1;
        this.layoutToInflate = layout;
        this.inflater = inflater;
        layoutToInflate = layout;
        currentContext = context;


        //Link to countdown timer text
        workoutTimerText = layoutToInflate.findViewById(R.id.ws_setTimerText);

        //Create Header
        view = inflater.inflate(R.layout.workout_header_template, null);
        headerView = view;
        layoutToInflate.addView(view);

        workoutName = view.findViewById(R.id.workout_nameTitle);

        workoutName.setText(getName());


        //Create first set
        LinearLayout setContainer = new LinearLayout(currentContext);
        setContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setContainer.setOrientation(LinearLayout.VERTICAL);
        setContainer.setId(++id);
        layoutToInflate.addView(setContainer);

        view = inflater.inflate(R.layout.workout_content_template, null);
        rootSetLayout = layoutToInflate.findViewById(id);
        setContainer.addView(view);

        workoutWeight = view.findViewById(R.id.workout_weight);
        workoutSet = view.findViewById(R.id.workout_set);
        workoutReps = view.findViewById(R.id.workout_reps);


        workoutCheckbox = view.findViewById(R.id.workout_setCheckbox);
        workoutCheckbox.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                CountdownTimer timer = new CountdownTimer(getTimerPreference());

                if (((CheckBox) v).isChecked()) {


                    timer.run();
                    workoutTimerText.setText(String.valueOf(timer.getTimeRemaining()));

                } else {

                }


            }


        });

        workoutSet.setText(String.valueOf(setNum));
        workoutWeight.setText(String.valueOf(this.weight));
        workoutReps.setText(String.valueOf(this.reps));



        //Create footer

        view = inflater.inflate(R.layout.workout_footer_template, null);
        footerView = view;
        layoutToInflate.addView(view);

        workoutAddButton = view.findViewById(R.id.workout_plusSetButton);
        workoutAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addSet();

            }


        });
        workoutDeleteButton = view.findViewById(R.id.workout_minusSetButton);
        workoutDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeSet();

            }
        });


    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;
        workoutName.setText(getName());

    }

    public void addSet() {

        setNum++;
        view = inflater.inflate(R.layout.workout_content_template, null);
        rootSetLayout.addView(view);
        TextView temp = view.findViewById(R.id.workout_set);
        temp.setText(String.valueOf(setNum));


    }

    public void removeSet() {

        if (setNum >= 1) {

            rootSetLayout.removeViewAt(setNum - 1);
            setNum--;

        } else {

            Snackbar.make(rootSetLayout, "Remove Section?", Snackbar.LENGTH_LONG)
                    .setAction("CONFIRM", new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            layoutToInflate.removeViewAt(layoutToInflate.indexOfChild(headerView));
                            layoutToInflate.removeViewAt(layoutToInflate.indexOfChild(footerView));

                        }


                    }).show();


        }

    }



    public void editWeight(int weight) {

        this.weight = weight;
        workoutWeight.setText(String.valueOf(this.weight));

    }

    public void editReps(int reps) {

        this.reps = reps;
        workoutReps.setText(String.valueOf(this.reps));

    }

    public int getWeight() {

        return weight;

    }

    public int getReps() {

        return reps;

    }

    public void setRestTimerPreferenceCode(int restTimerPreferenceCode) {

        this.restTimerPreferenceCode = restTimerPreferenceCode;

    }


    private void runOnClickListeners() {


    }

    private void runOnFocusListeners() {


        //Listener to change the name of the title on focus change.
        workoutName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if (!hasFocus) {


                    workoutName.setText(workoutName.getText());
                    workoutName.setEnabled(false);

                }


            }
        });


    }

    private int getTimerPreference() {


        switch (restTimerPreferenceCode) {

            case 1: {
                return 30;
            }

            case 2: {
                return 60;
            }

            case 3: {
                return 90;
            }

            case -1: {
                return 10;
            }

        }

        return 60;

    }

}

