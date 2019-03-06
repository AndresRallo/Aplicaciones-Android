package com.tecnoactive.awake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PanicButton extends AppCompatActivity {

    ImageButton posponer, sosafe, entretencion, vecinos, mensajes, directorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panic_button);
        getSupportActionBar().hide();

        posponer = (ImageButton) findViewById(R.id.posponer);
        sosafe = (ImageButton) findViewById(R.id.sosafe);
        entretencion = (ImageButton) findViewById(R.id.entretencion);
        vecinos = (ImageButton) findViewById(R.id.vecinos);
        mensajes = (ImageButton) findViewById(R.id.mensajes);
        directorio = (ImageButton) findViewById(R.id.directorio);
    }

    public void tweet133(View view){

    }

    public void tweet132(View view){

    }

    public void tweet131(View view){

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

    public void goSosafe (View view){
        Intent sosafeActivity = new Intent(this, SoSafe.class);
        startActivity(sosafeActivity);
    }
}
