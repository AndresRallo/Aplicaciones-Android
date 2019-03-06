package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class Television extends AppCompatActivity {

    ImageButton posponer, sosafe, directorio, vecinos, mensajes, panicButton;
    Button series, peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.television);
        getSupportActionBar().hide();

        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "television");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), random*60*1000, pendingIntent);
        } catch (Exception e) {}

        posponer = (ImageButton) findViewById(R.id.posponer);
        sosafe = (ImageButton) findViewById(R.id.sosafe);
        directorio = (ImageButton) findViewById(R.id.directorio);
        vecinos = (ImageButton) findViewById(R.id.vecinos);
        mensajes = (ImageButton) findViewById(R.id.mensajes);
        panicButton = (ImageButton) findViewById(R.id.panic_button);
        peliculas = (Button) findViewById(R.id.peliculas);
        series = (Button) findViewById(R.id.series);
    }

    public void goDirectorio (View view){
        Intent directorioActivity = new Intent(this, MainActivity.class);
        startActivity(directorioActivity);
    }

    public void goPosponer (View view){
        Intent posponerActivity = new Intent(this, Posponer.class);
        startActivity(posponerActivity);
    }

    public void goVecinos (View view){
        Intent vecinosActivity = new Intent(this, Vecinos.class);
        startActivity(vecinosActivity);
    }

    public void goSosafe (View view){
        Intent sosafeActivity = new Intent(this, SoSafe.class);
        startActivity(sosafeActivity);
    }

    public void goMensajes (View view){
        Intent mensajesActivity = new Intent(this, Mensajes.class);
        startActivity(mensajesActivity);
    }

    public void goPanic (View view){
        Intent panicActivity = new Intent(this, PanicButton.class);
        startActivity(panicActivity);
    }

    public void goSeries (View view){
        Intent seriesActivity = new Intent(this, Series.class);
        startActivity(seriesActivity);
    }
    public void goEntretencion (View view){
        Intent entretencionActivity = new Intent(this, Entretencion.class);
        startActivity(entretencionActivity);
    }
}
