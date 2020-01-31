package com.mikezalik.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView numTime;
    SeekBar seekBar;

    public void buttonClicked(View view) {
       CountDownTimer countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
           @Override
           public void onTick(long l) {
                updateTimer((int) l / 1000);
           }

           @Override
           public void onFinish() {

           }
       }.start();
    }

    public void updateTimer (int secondsLeft) {

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (secondString.equals("0")) {
            secondString = "00";
        }

        numTime.setText(String.format("%s:%s", Integer.toString(minutes), secondString));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        numTime = findViewById(R.id.numTime);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
