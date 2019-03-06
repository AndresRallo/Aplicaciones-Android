package com.example.raulstrappa.escanerqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class mostrar extends AppCompatActivity
{
    private TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);

        nombre = findViewById(R.id.txtNombre);
        String dato = getIntent().getStringExtra("nombre");

        nombre.setText(dato);
    }
}
