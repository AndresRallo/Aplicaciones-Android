package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.Tag;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class Vecinos extends AppCompatActivity {

    ImageButton posponer, sosafe, entretencion, directorio, mensajes, panicButton;

    private static final String TAG = "Vecinos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vecinos);
        getSupportActionBar().hide();
        Log.d(TAG, "onCreate: Started.");
        ListView listView = (ListView) findViewById(R.id.vecinos_view);

        Vecino alcantara = new Vecino("Alcantara", "Alcantara 453", "Emerson Godoy", "+56990340958");
        Vecino colon = new Vecino("Colon", "Colon 465", "Patricio Leiva", "+56994580348");
        Vecino escuela = new Vecino("Escuela Militar", "Los Militares 43635", "Daniela Campos", "+56990824324");
        Vecino copihues = new Vecino("Los Copihues", "Los Copihues 4435", "Cristobal Cespedes", "+56998403224");

        ArrayList<Vecino> arrayList = new ArrayList<>();
        arrayList.add(alcantara);
        arrayList.add(colon);
        arrayList.add(escuela);
        arrayList.add(copihues);

        VecinoListAdapter vecinoListAdapter = new VecinoListAdapter(this, R.layout.vecinos_item, arrayList);
        listView.setAdapter(vecinoListAdapter);


        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "vecinos");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), random*60*1000, pendingIntent);
        } catch (Exception e) {}

        posponer = (ImageButton) findViewById(R.id.posponer);
        sosafe = (ImageButton) findViewById(R.id.sosafe);
        entretencion = (ImageButton) findViewById(R.id.entretencion);
        directorio = (ImageButton) findViewById(R.id.directorio);
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
}
