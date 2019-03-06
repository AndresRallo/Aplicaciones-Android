package com.tecnoactive.awake;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkListAdapter extends ArrayAdapter<Work> {

    private static final String TAG = "WorkListAdapter";

    private Context mContext;
    int mResource;
    LayoutInflater mInflater;

    public WorkListAdapter(Context context, int resource, ArrayList<Work> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String date = getItem(position).getDate();
        String time = getItem(position).getTime();
        String title = getItem(position).getTitle();
        final Boolean check = getItem(position).getCheck();

        Work work = new Work(date, time, title);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);



        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        TextView hora = (TextView) convertView.findViewById(R.id.hora);
        TextView titulo = (TextView) convertView.findViewById(R.id.asunto);
        ImageView icono = (ImageView) convertView.findViewById(R.id.icono);


        fecha.setText(date);
        hora.setText(time);
        titulo.setText(title);
        if (work.getCheck()){
            icono.setImageResource(R.mipmap.checkbox_ok);
            icono.setContentDescription("activated");
        } else {
            icono.setImageResource(R.mipmap.checkbox);
            icono.setContentDescription("desactivated");
        }

        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView icono = (ImageView) v.findViewById(R.id.icono);
                if (icono.getContentDescription().equals("activated")){
                    icono.setImageResource(R.mipmap.checkbox);
                    icono.setContentDescription("desactivated");
                } else if (icono.getContentDescription().equals("desactivated")){
                    icono.setImageResource(R.mipmap.checkbox_ok);
                    icono.setContentDescription("activated");
                }
            }
        });

        return convertView;


    }

}
