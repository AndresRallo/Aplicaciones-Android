package com.tecnoactive.awake;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ApartmentListAdapter extends ArrayAdapter<Apartment> {

    private static final String TAG = "ApartmentListAdapter";

    private Context mContext;
    int mResource;
    LayoutInflater mInflater;

    public ApartmentListAdapter(Context context, int resource, ArrayList<Apartment> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String number = getItem(position).getNumber();
        String owner = getItem(position).getOwner();
        String owner_phone = getItem(position).getOwner_phone();
        String coowner = getItem(position).getCoowner();
        String coowner_phone = getItem(position).getCoowner_phone();
        String parking = getItem(position).getParking();

        Apartment apartment = new Apartment(number, owner, owner_phone, coowner, coowner_phone, parking);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);



        TextView departamento = (TextView) convertView.findViewById(R.id.departamento2);
        TextView arrendatario = (TextView) convertView.findViewById(R.id.arrendatario2);
        TextView tel_arrendatario = (TextView) convertView.findViewById(R.id.tel_arrendatario2);
        TextView propietario = (TextView) convertView.findViewById(R.id.propietario2);
        TextView tel_propietario = (TextView) convertView.findViewById(R.id.tel_propietario2);
        TextView estacionamiento = (TextView) convertView.findViewById(R.id.estacionamiento2);

        departamento.setText(number);
        propietario.setText(owner);
        tel_propietario.setText(owner_phone);
        arrendatario.setText(coowner);
        tel_arrendatario.setText(coowner_phone);
        estacionamiento.setText(parking);

        return convertView;


    }
}
