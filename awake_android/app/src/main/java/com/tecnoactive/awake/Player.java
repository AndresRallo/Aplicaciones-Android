package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class Player extends AppCompatActivity {

    ImageButton posponer, sosafe, entretencion, vecinos, mensajes, panicButton, directorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();

        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "player");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), random*60*1000, pendingIntent);
        } catch (Exception e) {}

        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        posponer = (ImageButton) findViewById(R.id.posponer);
        sosafe = (ImageButton) findViewById(R.id.sosafe);
        entretencion = (ImageButton) findViewById(R.id.entretencion);
        vecinos = (ImageButton) findViewById(R.id.vecinos);
        mensajes = (ImageButton) findViewById(R.id.mensajes);
        panicButton = (ImageButton) findViewById(R.id.panic_button);
        directorio = (ImageButton) findViewById(R.id.directorio);

        Intent intent = getIntent();
        String moviePath = intent.getStringExtra("MOVIE_PATH");
        String filePlace = moviePath + ".mp4";
        Toast.makeText(this, filePlace, Toast.LENGTH_SHORT).show();
        videoView.setVideoURI(Uri.parse(filePlace));
        videoView.setMediaController(new MediaController(this));

        videoView.start();
    }

    public void goPanic (View view){
        Intent panicActivity = new Intent(this, PanicButton.class);
        startActivity(panicActivity);
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

    public void goSosafe (View view){
        Intent sosafeActivity = new Intent(this, SoSafe.class);
        startActivity(sosafeActivity);
    }

    public void goDirectorio (View view){
        Intent directorioActivity = new Intent(this, MainActivity.class);
        startActivity(directorioActivity);
    }
}
