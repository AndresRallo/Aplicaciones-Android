package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class SoSafe extends AppCompatActivity {

    ImageButton posponer, directorio, entretencion, vecinos, mensajes, panicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sosafe);
        getSupportActionBar().hide();

        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "sosafe");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), random*60*1000, pendingIntent);
        } catch (Exception e) {}

        posponer = (ImageButton) findViewById(R.id.posponer);
        directorio = (ImageButton) findViewById(R.id.directorio);
        entretencion = (ImageButton) findViewById(R.id.entretencion);
        vecinos = (ImageButton) findViewById(R.id.vecinos);
        mensajes = (ImageButton) findViewById(R.id.mensajes);
        panicButton = (ImageButton) findViewById(R.id.panic_button);
    }

    public void goDirectorio (View view){
        Intent directorioActivity = new Intent(this, MainActivity.class);
        startActivity(directorioActivity);
    }

    public void goPosponer (View view){
        Intent posponerActivity = new Intent(this, Posponer.class);
        startActivity(posponerActivity);
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

}
