package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tareas extends AppCompatActivity {

    ImageButton posponer, sosafe, entretencion, vecinos, directorio, panicButton;
    Button sub_mensaje;

    private static final String TAG = "Tareas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas);
        getSupportActionBar().hide();
        Log.d(TAG, "onCreate: Started.");

        final ListView listView = (ListView) findViewById(R.id.tareas_view);

        // Development
        // String API_BASE_URL = "http://10.0.2.2:3000/";

        // Production
        String API_BASE_URL = "https://awakeapp.herokuapp.com/";

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        AwakeClient client =  retrofit.create(AwakeClient.class);
        SharedPreferences mySharedPreferences;
        mySharedPreferences = getSharedPreferences("Current Email", Activity.MODE_PRIVATE);
        String email = mySharedPreferences.getString("currentEmail", "");
        String base = email + ": cualquiera";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        Call<ArrayList<Work>> call = client.works(authHeader);
        call.enqueue(new Callback<ArrayList<Work>>() {
            @Override
            public void onResponse(Call<ArrayList<Work>> call, Response<ArrayList<Work>> response) {
                final ArrayList<Work> works = new ArrayList<>();
                listView.setAdapter(new WorkListAdapter(Tareas.this, R.layout.tareas_item, works));
            }


            @Override
            public void onFailure(Call<ArrayList<Work>> call, Throwable t) {

            }
        });

        /*
        Work w1 = new Work("28-02-2018", "22:34", "Regar Plantas");
        Work w2 = new Work("27-02-2018", "12:45", "Revisar Electricidad");
        Work w3 = new Work("25-02-2018", "20:23", "Limpiar Estacionamiento");
        Work w4 = new Work("20-02-2018", "12:09", "Recibir Encomienda");
        Work w5 = new Work("17-02-2018", "07:54", "Cobrar Gastos Comunes");

        final ArrayList<Work> works = new ArrayList<>();
        works.add(w1);
        works.add(w2);
        works.add(w3);
        works.add(w4);

        WorkListAdapter adapter = new WorkListAdapter(this, R.layout.tareas_item, works);
        listView.setAdapter(adapter);
        */
/*
        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "tareas");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), random*60*1000, pendingIntent);
        } catch (Exception e) {}
*/
        posponer = (ImageButton) findViewById(R.id.posponer);
        sosafe = (ImageButton) findViewById(R.id.sosafe);
        entretencion = (ImageButton) findViewById(R.id.entretencion);
        vecinos = (ImageButton) findViewById(R.id.vecinos);
        directorio = (ImageButton) findViewById(R.id.directorio);
        panicButton = (ImageButton) findViewById(R.id.panic_button);
        sub_mensaje = (Button) findViewById(R.id.sub_mensajes);
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

    public void goPanic (View view){
        Intent panicActivity = new Intent(this, PanicButton.class);
        startActivity(panicActivity);
    }

    public void goSosafe (View view){
        Intent sosafeActivity = new Intent(this, SoSafe.class);
        startActivity(sosafeActivity);
    }

    public void goMensajes (View view){
        Intent mensajesActivity = new Intent(this, Mensajes.class);
        startActivity(mensajesActivity);
    }
}
