package com.tecnoactive.awake;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import android.os.SystemClock;
import android.app.AlarmManager;
import android.app.PendingIntent;

public class Entretencion extends AppCompatActivity {








    ImageButton posponer, sosafe, directorio, vecinos, mensajes, panicButton;
    Button series, television;

    private static final int MY_PERMISSION_REQUEST = 1;

    ArrayList<String> arrayList;

    ListView listView;

    ArrayAdapter<String> adapter;

    ImageView cover;

    Button play_button;

    TextView description;

    String currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entretencion);
        getSupportActionBar().hide();

        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "entretencion");
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
        series = (Button) findViewById(R.id.series);
        television = (Button) findViewById(R.id.television);

        if (ContextCompat.checkSelfPermission(Entretencion.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Entretencion.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(Entretencion.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(Entretencion.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        } else {
            doStuff();
        }
    }

    public void doStuff(){
        listView = (ListView) findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        getVideo();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        final File f = new File("/storage/sdcard1/");
        if (f.exists()) {
            Toast.makeText(this, f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String movieTitle = arrayList.get(position);
                String root = f.getAbsolutePath() + "/Movies/" + movieTitle;
                cover = (ImageView) findViewById(R.id.cover);
                play_button = (Button) findViewById(R.id.play_button);
                description = (TextView) findViewById(R.id.description);
                String name = movieTitle.toLowerCase();
                String path =  root + "/" + name;
                currentMovie = path;
                String filePlace = path + ".png";
                Bitmap bmp = BitmapFactory.decodeFile(filePlace);
                cover.setImageBitmap(bmp);
                play_button.setVisibility(View.VISIBLE);
                File file = new File(path + ".txt");
                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();

                }
                catch (IOException e){
                    text = new StringBuilder();
                    text.append("Descripcion no encontrada");
                }

                description.setText(text);


                // String movieTitle2 = arrayList.get(position);
                // Intent playerActivity = new Intent(Entretencion.this, Player.class);
                // playerActivity.putExtra("MOVIE_NAME", movieTitle);
                // startActivity(playerActivity);


            }
        });
    }

    public void playMovie(View view){
        Intent playerActivity = new Intent(Entretencion.this, Player.class);
        playerActivity.putExtra("MOVIE_PATH", currentMovie);
        startActivity(playerActivity);
    }

    public void getVideo(){

        String path = "/storage/sdcard1/Movies";

        // Field[] fields=Environment.getExternalStorageDirectory().getClass().getFields();
        // for (int count=0; count < fields.length; count++){
        //     arrayList.add(fields[count].getName());
        // }

        // String path = "android.resource://" + getPackageName() + "/raw/movies";
        File root = new File(path);
        File[] files = root.listFiles();
        

        for (File file : files){
            arrayList.add(file.getName());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(Entretencion.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permiso otorgado!", Toast.LENGTH_SHORT).show();

                        doStuff();
                    } else {
                        Toast.makeText(this, "Permiso denegado!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    return;
                }
            }
        }

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

    public void goTelevision (View view){
        Intent televisionActivity = new Intent(this, Television.class);
        startActivity(televisionActivity);
    }
}
