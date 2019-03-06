package com.tecnoactive.awake;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VecinoListAdapter extends ArrayAdapter<Vecino> {

    private static final String TAG = "VecinoListAdapter";

    private Context mContext;
    int mResource;
    LayoutInflater mInflater;

    public VecinoListAdapter(Context context, int resource, ArrayList<Vecino> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String edificio = getItem(position).getEdificio();
        String direccion = getItem(position).getDireccion();
        String nombre = getItem(position).getNombre();
        String telefono = getItem(position).getTelefono();

        Vecino vecino = new Vecino(edificio, direccion, nombre, telefono);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);



        TextView tvEdificio = (TextView) convertView.findViewById(R.id.edificio);
        TextView tvDireccion = (TextView) convertView.findViewById(R.id.direccion);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.nombre);
        TextView tvTelefono = (TextView) convertView.findViewById(R.id.telefono);


        tvEdificio.setText(edificio);
        tvDireccion.setText(direccion);
        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);

        return convertView;


    }
}
