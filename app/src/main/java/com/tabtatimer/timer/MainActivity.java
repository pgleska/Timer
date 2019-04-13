package com.tabtatimer.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TIME = "com.tabtatimer.timer.TIME", PREP = "com.tabtatimer.timer.PREP", WORK = "com.tabtatimer.timer.WORK",
                                REST = "com.tabtatimer.timer.REST", ROUND = "com.tabtatimer.timer.ROUND";
    private int time, prep, work, rest, round;
    private Button mBtnPlusPrep, mBtnMinusPrep, mBtnPlusWork, mBtnMinusWork, mBtnPlusRest, mBtnMinusRest, mBtnPlusRound, mBtnMinusRound, mMainStart;
    private EditText mEditPrep, mEditWork, mEditRest, mEditRound;
    private TextView mTime;
    private String timeTxt, prepTxt, workTxt, restTxt, roundTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        work = 10;
        prep = 10;
        rest = 10;
        round = 1;

        setContentView(R.layout.activity_main);
        mBtnPlusPrep = findViewById(R.id.plus_prep);
        mBtnMinusPrep = findViewById(R.id.minus_prep);
        mBtnPlusWork= findViewById(R.id.plus_work);
        mBtnMinusWork = findViewById(R.id.minus_work);
        mBtnPlusRest = findViewById(R.id.plus_rest);
        mBtnMinusRest = findViewById(R.id.minus_rest);
        mBtnPlusRound = findViewById(R.id.plus_round);
        mBtnMinusRound = findViewById(R.id.minus_round);
        mEditPrep = findViewById(R.id.edit_prep);
        mEditWork = findViewById(R.id.edit_work);
        mEditRest = findViewById(R.id.edit_rest);
        mEditRound = findViewById(R.id.edit_round);
        mTime = findViewById(R.id.text_time);
        mMainStart = findViewById(R.id.main_start);

        updateEditPrep();
        updateEditWork();
        updateEditRest();
        updateEditRound();
        updateTime();

        //TODO integer over ranged
        mEditPrep.setSelectAllOnFocus(true);

        mBtnPlusPrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prep < 3600) { //TODO in every button Click
                    prep += 1;
                } else {
                    Toast.makeText(MainActivity.this, "Max 3600 seconds", Toast.LENGTH_SHORT).show();
                }
                updateEditPrep();
                updateTime();

            }
        });
        mBtnMinusPrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prep > 0) {
                    prep -= 1;
                    updateEditPrep();
                    updateTime();
                }
            }
        });

        mEditPrep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textPrep;
                textPrep = mEditPrep.getText().toString();
                if (textPrep.equals("") || Integer.parseInt(textPrep) == 0){
                    textPrep = "1";
                    mEditPrep.setText("1");
                    mEditPrep.selectAll();
                    Toast.makeText(MainActivity.this, "At least 1 second is required", Toast.LENGTH_SHORT).show();
                }
                if (Integer.parseInt(textPrep) > 3600) {
                    textPrep = "3600";
                    mEditPrep.setText("3600");
                    Toast.makeText(MainActivity.this, "Max 3600 seconds", Toast.LENGTH_SHORT).show();
                }
                prep = Integer.parseInt(textPrep);
                updateTime();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditWork.setSelectAllOnFocus(true);

        mBtnPlusWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                work += 1;
                updateEditWork();
                updateTime();

            }
        });
        mBtnMinusWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(work > 1) {
                    work -= 1;
                    updateEditWork();
                    updateTime();
                }
            }
        });

        mEditWork.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textWork;
                textWork = mEditWork.getText().toString();
                if (textWork.equals("") || Integer.parseInt(textWork) == 0){
                    textWork = "1";
                    mEditWork.setText("1");
                    mEditWork.selectAll();
                    Toast.makeText(MainActivity.this, "At least 1 second required", Toast.LENGTH_SHORT).show();
                }
                if (Integer.parseInt(textWork) > 3600) {
                    textWork = "3600";
                    mEditWork.setText("3600");
                    Toast.makeText(MainActivity.this, "Max 3600 seconds", Toast.LENGTH_SHORT).show();
                }
                work = Integer.parseInt(textWork);
                updateTime();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditRest.setSelectAllOnFocus(true);

        mBtnPlusRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rest += 1;
                updateEditRest();
                updateTime();

            }
        });
        mBtnMinusRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rest > 1) {
                    rest -= 1;
                    updateEditRest();
                    updateTime();
                }
            }
        });

        mEditRest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textRest;
                textRest = mEditRest.getText().toString();
                if (textRest.equals("") || Integer.parseInt(textRest) == 0){
                    textRest = "1";
                    mEditRest.setText("1");
                    mEditRest.selectAll();
                    Toast.makeText(MainActivity.this, "At least 1 second required", Toast.LENGTH_SHORT).show();
                }
                if (Integer.parseInt(textRest) > 3600) {
                    textRest = "3600";
                    mEditRest.setText("3600");
                    Toast.makeText(MainActivity.this, "Max 3600 seconds", Toast.LENGTH_SHORT).show();
                }
                rest = Integer.parseInt(textRest);
                updateTime();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditRound.setSelectAllOnFocus(true);

        mBtnPlusRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                round += 1;
                updateEditRound();
                updateTime();

            }
        });
        mBtnMinusRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(round > 1) {
                    round -= 1;
                    updateEditRound();
                    updateTime();
                }
            }
        });

        mEditRound.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textRound;
                textRound = mEditRound.getText().toString();
                if (textRound.equals("") || Integer.parseInt(textRound) == 0){
                    textRound = "1";
                    mEditRound.setText("1");
                    mEditRound.selectAll();
                    Toast.makeText(MainActivity.this, "At least 1 second is required", Toast.LENGTH_SHORT).show();
                }
                if (Integer.parseInt(textRound) > 100) {
                    textRound = "100";
                    mEditRound.setText("100");
                    Toast.makeText(MainActivity.this, "Max 100 rounds", Toast.LENGTH_SHORT).show();
                }
                round = Integer.parseInt(textRound);
                updateTime();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMainStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCountDown();
            }
        });
    }
    public void goToCountDown(){
        timeTxt = Integer.toString(time);
        prepTxt = Integer.toString(prep);
        workTxt = Integer.toString(work);
        restTxt = Integer.toString(rest);
        roundTxt = Integer.toString(round);
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra(TIME, timeTxt);
        intent.putExtra(PREP, prepTxt);
        intent.putExtra(WORK, workTxt);
        intent.putExtra(REST, restTxt);
        intent.putExtra(ROUND, roundTxt);
        startActivity(intent);
    }
    private void updateTime(){
        int hours, min, sec;
        time = prep + (work + rest) * round;
        sec = time % 60;
        min = (time / 60) % 60;
        hours = time / 3600;
        mTime.setText(String.format("%02d:%02d:%02d", hours, min, sec));
    }
    public void updateEditPrep(){
        mEditPrep.setText(Integer.toString(prep));
    }
    public void updateEditWork(){
        mEditWork.setText(Integer.toString(work));
    }
    public void updateEditRest(){
        mEditRest.setText(Integer.toString(rest));
    }
    public void updateEditRound(){
        mEditRound.setText(Integer.toString(round));
    }
}
