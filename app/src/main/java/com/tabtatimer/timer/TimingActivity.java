package com.tabtatimer.timer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class TimingActivity extends AppCompatActivity {
    private TextView mFullTime, mCurrentTime, mPart, mRounds;
    private long longPrepInMilli, longWorkInMilli, longRestInMilli, constWork, constRest;
    public long longTimeInMilli;
    private String time, prep, work, rest, round;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private Button mPlay;
    private int currentRound = 1, intRound;
    private RelativeLayout rl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);

//        startService();

        rl = (RelativeLayout)findViewById(R.id.relative_layout);
        Intent intent = getIntent();
        time = intent.getStringExtra(MainActivity.TIME);
        prep = intent.getStringExtra(MainActivity.PREP);
        work = intent.getStringExtra(MainActivity.WORK);
        rest = intent.getStringExtra(MainActivity.REST);
        round = intent.getStringExtra(MainActivity.ROUND);
        intRound = Integer.parseInt(round);
        mFullTime = findViewById(R.id.full_time);
        mFullTime.setText(time);
        mCurrentTime = findViewById(R.id.current_time);
        mCurrentTime.setText(prep);
        mPlay = findViewById(R.id.play);
        mPart = findViewById(R.id.part);
        mRounds = findViewById(R.id.rounds);
        longTimeInMilli = Long.parseLong(time) * 1000;
        longPrepInMilli = Long.parseLong(prep) * 1000;
        longWorkInMilli = Long.parseLong(work) * 1000;
        constWork = longWorkInMilli;
        longRestInMilli = Long.parseLong(rest) * 1000;
        constRest = longRestInMilli;
        timerRunning = false;
        startStop();
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning == false)
                    longTimeInMilli = longTimeInMilli - 1000;
                startStop();
            }
        });
        startService();
    }

    private void startService() {
        Intent serviceIntent = new Intent(this, TimerService.class);
        startService(serviceIntent);
    }

    private void startStop() {
        if (timerRunning) {
            stopTime();
        } else {
            startTime();
        }
    }

    private void startTime() {
        timerRunning = true;
        mPlay.setText("PAUSE");
        countDownTimer = new CountDownTimer(longTimeInMilli, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                longTimeInMilli = millisUntilFinished;
                updateTime();
                if (longPrepInMilli-1000 > 0) {
                    longPrepInMilli = longPrepInMilli - 1000;
                    updateCurrentTime(1);
                    mPart.setText("PREPARE");
                    rl.setBackgroundColor(Color.BLUE);
                } else if ((currentRound <= intRound) && (longWorkInMilli > 0) ) {
                    updateCurrentTime(2);
                    longWorkInMilli = longWorkInMilli - 1000;
                    mPart.setText("WORK");
                    rl.setBackgroundColor(Color.RED);
                } else if ((currentRound <= intRound) && (longRestInMilli > 0) ){
                    updateCurrentTime(3);
                    longRestInMilli = longRestInMilli - 1000;
                    mPart.setText("REST");
                    rl.setBackgroundColor(Color.GREEN);
                } else if ((currentRound <= intRound) && (longWorkInMilli == 0) && (longRestInMilli == 0)) {
                    currentRound++;
                    longWorkInMilli = constWork;
                    longRestInMilli = constRest;
                    mPart.setText("WORK");
                    updateCurrentTime(2);
                    longWorkInMilli = longWorkInMilli - 1000;
                    rl.setBackgroundColor(Color.RED);
                }
                mRounds.setText(Integer.toString(currentRound) + " of " + Integer.toString(intRound) + " rounds");
            }

            @Override
            public void onFinish() {
                longTimeInMilli = 0;
                updateTime();
                mPart.setText("END");
                mCurrentTime.setText("0");
                mPlay.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void stopTime() {
        countDownTimer.cancel();
        timerRunning = false;
        mPlay.setText("PLAY");
    }

    private void updateTime() {
        int hours, min, sec, intTime;
        intTime = (int) longTimeInMilli / 1000;
        sec = intTime % 60;
        min = (intTime / 60) % 60;
        hours = intTime / 3600;
        time = Integer.toString(intTime);
        mFullTime.setText(String.format("%02d:%02d:%02d", hours, min, sec));
    }

    private void updateCurrentTime(int which) {
        ///1 - prep, 2 - work, 3 - rest
        int intSec = 0;
        String strSec;
        if (which == 1) {
            intSec = (int) longPrepInMilli / 1000;
        } else if (which == 2) {
            intSec = (int) longWorkInMilli / 1000;
        } else if (which == 3){
            intSec = (int) longRestInMilli / 1000;
        }
        strSec = Integer.toString(intSec);
        mCurrentTime.setText(strSec);
    }
}
