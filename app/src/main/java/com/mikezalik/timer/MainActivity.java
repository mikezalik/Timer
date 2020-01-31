package com.mikezalik.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
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
    Boolean counterIsActive = false;
    Button timerStart;

    public void buttonClicked(View view) {

        counterIsActive = true;
        seekBar.setEnabled(false);
        timerStart.setText("Stop Timer!");

       CountDownTimer countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
           @Override
           public void onTick(long l) {
                updateTimer((int) l / 1000);
           }

           @Override
           public void onFinish() {
               MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
               mplayer.start();
           }
       }.start();
    }

    public void updateTimer (int secondsLeft) {

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        numTime.setText(String.format("%s:%s", Integer.toString(minutes), secondString));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        numTime = findViewById(R.id.numTime);
        timerStart = findViewById(R.id.timerStart);

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
