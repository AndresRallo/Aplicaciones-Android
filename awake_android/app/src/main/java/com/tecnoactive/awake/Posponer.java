package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Posponer extends AppCompatActivity {

    ImageButton directorio, sosafe, entretencion, vecinos, mensajes, panicButton;
    ImageButton pos1, pos2, pos3, pos4, pos5, pos6;

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
    Intent intent;
    AlarmManager am;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posponer);
        getSupportActionBar().hide();

        // Development
        // String API_BASE_URL = "http://10.0.2.2:3000/";

        // Production
        String API_BASE_URL = "https://awakeapp.herokuapp.com/";

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        AwakeClient client =  retrofit.create(AwakeClient.class);

        Call<ArrayList<Posponedor>> call = client.posponers();
        call.enqueue(new Callback<ArrayList<Posponedor>>() {
            @Override
            public void onResponse(Call<ArrayList<Posponedor>> call, Response<ArrayList<Posponedor>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<Posponedor>> call, Throwable t) {

            }
        });
/*
        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "posponer");
            pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), random*60*1000, pendingIntent);
        } catch (Exception e) {}
*/
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        directorio = (ImageButton) findViewById(R.id.directorio);
        sosafe = (ImageButton) findViewById(R.id.sosafe);
        entretencion = (ImageButton) findViewById(R.id.entretencion);
        vecinos = (ImageButton) findViewById(R.id.vecinos);
        mensajes = (ImageButton) findViewById(R.id.mensajes);
        panicButton = (ImageButton) findViewById(R.id.panic_button);

        pos1 = (ImageButton) findViewById(R.id.pos1);
        pos2 = (ImageButton) findViewById(R.id.pos2);
        pos3 = (ImageButton) findViewById(R.id.pos3);
        pos4 = (ImageButton) findViewById(R.id.pos4);
        pos5 = (ImageButton) findViewById(R.id.pos5);
        pos6 = (ImageButton) findViewById(R.id.pos6);


    }
    public void goDirectorio (View view){
        Intent directorioActivity = new Intent(this, MainActivity.class);
        startActivity(directorioActivity);
    }

    public void goSosafe (View view){
        Intent sosafeActivity = new Intent(this, SoSafe.class);
        startActivity(sosafeActivity);
    }

    public void goEntretencion (View view){
        Intent entretencionActivity = new Intent(this, Entretencion.class);
        startActivity(entretencionActivity);
    }

    public void goVecinos (View view){
        Intent vecinosActivity = new Intent(this, Vecinos.class);
        startActivity(vecinosActivity);
    }

    public void goMensajes (View view){
        Intent mensajesActivity = new Intent(this, Mensajes.class);
        startActivity(mensajesActivity);
    }

    public void goPanic (View view){
        Intent panicActivity = new Intent(this, PanicButton.class);
        startActivity(panicActivity);
    }


    public void activateOnTouch (View v){
        TextView tv = (TextView) v;
        ViewGroup parent = (ViewGroup) tv.getParent();
        ImageButton b = (ImageButton) parent.getChildAt(0);
        b.setImageResource(R.mipmap.boton_posponer_active);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        am.cancel(pendingIntent);
    }


}
