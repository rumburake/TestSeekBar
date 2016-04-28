package com.rumburake.testseekbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    int delay1 = 1000;
    int delay2 = 200;
    int primaryProgress;
    int secondaryProgress;

    boolean mStarted;
    Button mButton;
    SeekBar mSeekBar;
    Handler h1 = new Handler();
    Handler h2 = new Handler();

    Runnable primary = new Runnable() {
        @Override
        public void run() {
            primaryProgress = (primaryProgress + 1) % 100;
            mSeekBar.setProgress(primaryProgress);
            h1.postDelayed(primary, delay1);
        }
    };

    Runnable secondary = new Runnable() {
        @Override
        public void run() {
            secondaryProgress = (secondaryProgress + 1) % 100;
            mSeekBar.setSecondaryProgress(secondaryProgress);
            h2.postDelayed(secondary, delay2);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });
        mSeekBar = (SeekBar)findViewById(R.id.seekBar);
    }

    private void buttonClick() {
        mStarted = !mStarted;
        if (mStarted) {
            mButton.setText("Started");
            h1.postDelayed(primary, delay1);
            h2.postDelayed(secondary, delay2);
        } else {
            mButton.setText("Stopped");
            h1.removeCallbacks(primary);
            h2.removeCallbacks(secondary);
        }

    }

}
