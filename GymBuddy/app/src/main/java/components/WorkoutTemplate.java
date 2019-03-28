package components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gymbuddy.R;

import java.io.Serializable;

public class WorkoutTemplate implements Serializable {

    private String name;

    private static int id = 0;

    private int setNum;
    private int weight;
    private int reps;
    private int restTimerPreferenceCode;

    private View view;
    private LinearLayout rootSetLayout;
    private LinearLayout layoutToInflate;
    private LayoutInflater inflater;

    private TextView workoutWeight;
    private TextView workoutReps;
    private TextView workoutSet;
    private CheckBox workoutCheckbox;
    private EditText workoutName;
    private ImageButton workoutAddButton;
    private ImageButton workoutDeleteButton;

    private Context currentContext;


    //Need a stack of views for sets here

    public WorkoutTemplate(String name, int weight, int reps, LinearLayout layout, LayoutInflater inflater, Context context) {

        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.setNum = 1;
        this.restTimerPreferenceCode = 2;
        this.layoutToInflate = layout;
        this.inflater = inflater;
        layoutToInflate = layout;
        currentContext = context;


        //Create Header
        view = inflater.inflate(R.layout.workout_header_template, null);
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

        workoutSet.setText(String.valueOf(setNum));
        workoutWeight.setText(String.valueOf(this.weight));
        workoutReps.setText(String.valueOf(this.reps));



        //Create footer

        view = inflater.inflate(R.layout.workout_footer_template, null);
        layoutToInflate.addView(view);

        workoutAddButton = view.findViewById(R.id.workout_plusSetButton);
        workoutAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addSet();

            }


        });
        workoutDeleteButton = view.findViewById(R.id.workout_minusSetButton);


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


        setNum--;


    }

    public void editWeight(int weight) {

        this.weight = weight;

    }

    public void editReps(int reps) {

        this.reps = reps;

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

}

