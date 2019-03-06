package com.tecnoactive.awake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.Base64;
import android.util.EventLogTags;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mensajes extends AppCompatActivity {

    ImageButton posponer, sosafe, entretencion, vecinos, directorio, panicButton;
    Button tareas;

    private static final String TAG = "Mensajes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajes);
        getSupportActionBar().hide();
        Log.d(TAG, "onCreate: Started.");

        final ListView listView = (ListView) findViewById(R.id.message_view);

        // Development
        // String API_BASE_URL = "http://10.0.2.2:3000/";

        // Production
        String API_BASE_URL = "https://awakeapp.herokuapp.com/";

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        AwakeClient client =  retrofit.create(AwakeClient.class);
        SharedPreferences  mySharedPreferences;
        mySharedPreferences = getSharedPreferences("Current Email", Activity.MODE_PRIVATE);
        String email = mySharedPreferences.getString("currentEmail", "");
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        String base = email + ": cualquiera";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        Call<ArrayList<Message>> call = client.messages(authHeader);
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                final ArrayList<Message> messages = response.body();
                listView.setAdapter(new MessageListAdapter(Mensajes.this, R.layout.messages_item, messages));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView datetime = (TextView) findViewById(R.id.datetime);
                        TextView title = (TextView) findViewById(R.id.titulo);
                        TextView content = (TextView) findViewById(R.id.content);
                        Message m = messages.get(position);
                        Boolean read = m.getRead();
                        m.setRead(!read);
                        String dt = m.getDate();
                        datetime.setText(dt);
                        title.setText(m.getAsunto());
                        content.setText(m.getContent());
                        LinearLayout all_message = (LinearLayout) findViewById(R.id.all_message);
                        all_message.setVisibility(View.VISIBLE);
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

            }
        });

        /*

        Message m1 = new Message("28-02-2018", "22:34", "Ascensor malo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus mollis eleifend feugiat. Suspendisse ultricies sapien id quam suscipit pretium. Vivamus sodales erat fringilla mi finibus hendrerit fringilla hendrerit dolor. Etiam rhoncus et erat ut mollis. Suspendisse potenti. Cras aliquet lacinia odio a gravida. Etiam ut aliquam elit. Aenean ultrices congue justo, sed laoreet odio lobortis vel. Donec ut tempus sem.");
        Message m2 = new Message("27-02-2018", "12:15", "Nueva Administración", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus mollis eleifend feugiat. Suspendisse ultricies sapien id quam suscipit pretium. Vivamus sodales erat fringilla mi finibus hendrerit fringilla hendrerit dolor. Etiam rhoncus et erat ut mollis. Suspendisse potenti. Cras aliquet lacinia odio a gravida. Etiam ut aliquam elit. Aenean ultrices congue justo, sed laoreet odio lobortis vel. Donec ut tempus sem.");
        Message m3 = new Message("25-02-2018", "20:23", "Turno Noche", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus mollis eleifend feugiat. Suspendisse ultricies sapien id quam suscipit pretium. Vivamus sodales erat fringilla mi finibus hendrerit fringilla hendrerit dolor. Etiam rhoncus et erat ut mollis. Suspendisse potenti. Cras aliquet lacinia odio a gravida. Etiam ut aliquam elit. Aenean ultrices congue justo, sed laoreet odio lobortis vel. Donec ut tempus sem.");
        Message m4 = new Message("20-02-2018", "12:09", "Nuevo Arrendatario", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus mollis eleifend feugiat. Suspendisse ultricies sapien id quam suscipit pretium. Vivamus sodales erat fringilla mi finibus hendrerit fringilla hendrerit dolor. Etiam rhoncus et erat ut mollis. Suspendisse potenti. Cras aliquet lacinia odio a gravida. Etiam ut aliquam elit. Aenean ultrices congue justo, sed laoreet odio lobortis vel. Donec ut tempus sem.");

        final ArrayList<Message> messages = new ArrayList<>();
        messages.add(m1);
        messages.add(m2);
        messages.add(m3);
        messages.add(m4);

        MessageListAdapter adapter = new MessageListAdapter(this, R.layout.messages_item, messages);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView datetime = (TextView) findViewById(R.id.datetime);
                TextView title = (TextView) findViewById(R.id.titulo);
                TextView content = (TextView) findViewById(R.id.content);
                Message m = messages.get(position);
                Boolean read = m.getRead();
                m.setRead(!read);
                String dt = m.getDate() + " " + m.getTime();
                datetime.setText(dt);
                title.setText(m.getAsunto());
                content.setText(m.getContent());
                LinearLayout all_message = (LinearLayout) findViewById(R.id.all_message);
                all_message.setVisibility(View.VISIBLE);
            }
        }); */
/*
        try {
            Random r = new Random();
            long random = r.nextInt((150)+1) + 50;
            Intent intent  = new Intent(this, Alarm.class);
            intent.putExtra("CLASS", "mensajes");
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
        tareas = (Button) findViewById(R.id.tareas);
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

    public void goTareas (View view){
        Intent tareasActivity = new Intent(this, Tareas.class);
        startActivity(tareasActivity);
    }

}
