package components;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gymbuddy.R;

import java.io.Serializable;

public class Workout implements Serializable {

    private String name;

    private static int id = 0;

    private int setNum;
    private int restTimerPreferenceCode;
    private static boolean isActiveTimer;
    private static CountdownTimer activeTimer;

    private View view;
    private View headerView;
    private View setView;
    private View footerView;
    private RelativeLayout greenHeaderLayout;
    private LinearLayout rootSetLayout;
    private LinearLayout layoutToInflate;
    private LayoutInflater inflater;

    private TextView workoutWeight;
    private TextView workoutReps;
    private TextView workoutTimerText;
    private EditText workoutName;


    private Context currentContext;


    public Workout(String name, LinearLayout lowerlayout, RelativeLayout headerLayout, LayoutInflater inflater, Context context) {

        this.name = name;
        this.setNum = 1;
        this.restTimerPreferenceCode = -1;
        this.inflater = inflater;
        greenHeaderLayout = headerLayout;
        layoutToInflate = lowerlayout;
        currentContext = context;

        bindCountdownTimerToUI();
        createHeader();
        createSet();
        createFooter();


    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;
        workoutName.setText(getName());

    }

    public void addSet() {

        //Bug happens when you create multiple sets at one time the add and minus buttons are getting mismapped.

        setNum++;
        view = inflater.inflate(R.layout.workout_content_template, null);
        rootSetLayout.addView(view);
        ImageButton tempImgButton = view.findViewById(R.id.workout_repsPlusButton);
        tempImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOne((TextView) view.findViewById(R.id.workout_reps));
            }
        });

        tempImgButton = view.findViewById(R.id.workout_weightPlusButton);
        tempImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOne((TextView) view.findViewById(R.id.workout_weight));
            }
        });

        tempImgButton = view.findViewById(R.id.workout_weightMinusButton);
        tempImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOne((TextView) view.findViewById(R.id.workout_weight));
            }
        });

        tempImgButton = view.findViewById(R.id.workout_repsMinusButton);
        tempImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOne((TextView) view.findViewById(R.id.workout_reps));
            }
        });




        TextView temp = view.findViewById(R.id.workout_set);
        CheckBox tempCBox = view.findViewById(R.id.workout_setCheckbox);
        tempCBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    if (!isActiveTimer) {
                        activeTimer = new CountdownTimer(getTimerPreference(), workoutTimerText);
                        isActiveTimer = true;
                        activeTimer.execute();

                    } else {
                        isActiveTimer = activeTimer.isActive();
                        activeTimer.resetTimer(getTimerPreference());

                    }

                } else {

                    isActiveTimer = activeTimer.isActive();

                }

            }
        });
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


    public View getHeaderView() {
        return headerView;
    }

    public View getSetView() {
        return setView;
    }

    public View getFooterView() {
        return footerView;
    }

    private void incrementOne(TextView view) {

        int amount;
        amount = Integer.parseInt(view.getText().toString());
        if (amount < 999)
            amount++;
        view.setText(String.valueOf(amount));

    }

    private void decrementOne(TextView view) {

        int amount;
        amount = Integer.parseInt(view.getText().toString());
        if (amount > 0)
            amount--;
        view.setText(String.valueOf(amount));

    }

    private void editNumber(TextView view, int increaseAmount) {

        view.setText(String.valueOf(increaseAmount));

    }


    public void setRestTimerPreferenceCode(int restTimerPreferenceCode) {

        this.restTimerPreferenceCode = restTimerPreferenceCode;

    }


    private void bindCountdownTimerToUI() {

        //Link to countdown timer text
        workoutTimerText = greenHeaderLayout.findViewById(R.id.ws_setTimerText);
        workoutTimerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Reset the active timer check so that the next timer can activate
                if (s.toString().equals("0:01")) {

                    isActiveTimer = false;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void createHeader() {

        //Create Header
        view = inflater.inflate(R.layout.workout_header_template, null);
        headerView = view;
        layoutToInflate.addView(view);

        workoutName = view.findViewById(R.id.workout_nameTitle);

        workoutName.setText(getName());

    }

    private void createSet() {

        //Create first set
        ImageButton workoutWeightAddButton;
        ImageButton workoutWeightMinusButton;
        ImageButton workoutRepsAddButton;
        ImageButton workoutRepsMinusButton;
        TextView workoutSet;
        CheckBox workoutCheckbox;

        LinearLayout setContainer = new LinearLayout(currentContext);
        setContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setContainer.setOrientation(LinearLayout.VERTICAL);
        setContainer.setId(++id);
        setView = setContainer;
        layoutToInflate.addView(setContainer);

        view = inflater.inflate(R.layout.workout_content_template, null);
        rootSetLayout = layoutToInflate.findViewById(id);
        setContainer.addView(view);

        workoutWeight = view.findViewById(R.id.workout_weight);
        workoutSet = view.findViewById(R.id.workout_set);
        workoutReps = view.findViewById(R.id.workout_reps);

        workoutWeightAddButton = view.findViewById(R.id.workout_weightPlusButton);
        workoutWeightMinusButton = view.findViewById(R.id.workout_weightMinusButton);

        workoutRepsAddButton = view.findViewById(R.id.workout_repsPlusButton);
        workoutRepsMinusButton = view.findViewById(R.id.workout_repsMinusButton);

        workoutWeightAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOne(workoutWeight);
            }
        });

        workoutWeightMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOne(workoutWeight);
            }
        });

        workoutRepsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOne(workoutReps);
            }
        });

        workoutRepsMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOne(workoutReps);
            }
        });


        workoutCheckbox = view.findViewById(R.id.workout_setCheckbox);
        workoutCheckbox.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    if (!isActiveTimer) {
                        activeTimer = new CountdownTimer(getTimerPreference(), workoutTimerText);
                        isActiveTimer = true;
                        activeTimer.execute();

                    } else {
                        isActiveTimer = activeTimer.isActive();
                        activeTimer.resetTimer(getTimerPreference());

                    }

                } else {

                    isActiveTimer = activeTimer.isActive();

                }


            }


        });

        workoutSet.setText(String.valueOf(setNum));
        workoutWeight.setText(String.valueOf(0));
        workoutReps.setText(String.valueOf(0));


    }

    private void createFooter() {

        //Create footer
        ImageButton workoutAddButton;
        ImageButton workoutDeleteButton;

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


    private int getTimerPreference() {


        switch (restTimerPreferenceCode) {

            case 1: {
                //30 seconds
                return 30;
            }

            case 2: {
                // 1 minute
                return 60;
            }

            case 3: {
                // 1 minute 30 seconds
                return 90;
            }

            case -1: {
                // 10 seconds debugging and testing case
                return 10;
            }

            default:
                return 60;

        }


    }

}

