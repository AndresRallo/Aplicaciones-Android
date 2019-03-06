package com.tecnoactive.awake;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Alarm extends AppCompatActivity {

    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%02d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        getSupportActionBar().hide();
        timerTextView = (TextView) findViewById(R.id.timer);

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);


        ImageButton stopAlarm = (ImageButton) findViewById(R.id.stopAlarm);

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String clase = intent.getStringExtra("CLASS");
                if (clase.equals("entretencion")){
                    Intent intentback = new Intent(Alarm.this, Entretencion.class);
                    startActivity(intentback);
                }
                else if (clase.equals("directorio")){
                    Intent intentback = new Intent(Alarm.this, MainActivity.class);
                    startActivity(intentback);
                }
                else if (clase.equals("mensajes")){
                    Intent intentback = new Intent(Alarm.this, Mensajes.class);
                    startActivity(intentback);
                }
                else if (clase.equals("posponer")){
                    Intent intentback = new Intent(Alarm.this, Posponer.class);
                    startActivity(intentback);
                }
                else if (clase.equals("series")){
                    Intent intentback = new Intent(Alarm.this, Series.class);
                    startActivity(intentback);
                }
                else if (clase.equals("sosafe")){
                    Intent intentback = new Intent(Alarm.this, SoSafe.class);
                    startActivity(intentback);
                }
                else if (clase.equals("tareas")){
                    Intent intentback = new Intent(Alarm.this, Tareas.class);
                    startActivity(intentback);
                }
                else if (clase.equals("television")){
                    Intent intentback = new Intent(Alarm.this, Television.class);
                    startActivity(intentback);
                }
                else if (clase.equals("vecinos")){
                    Intent intentback = new Intent(Alarm.this, MainActivity.class);
                    startActivity(intentback);
                }
                else if (clase.equals("player")){
                    Intent intentback = new Intent(Alarm.this, Player.class);
                    startActivity(intentback);
                }
            }
        });


    }
}
