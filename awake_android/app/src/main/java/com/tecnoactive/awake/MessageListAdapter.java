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

public class MessageListAdapter extends ArrayAdapter<Message> {

    private static final String TAG = "MessageListAdapter";

    private Context mContext;
    int mResource;
    LayoutInflater mInflater;

    public MessageListAdapter(Context context, int resource, ArrayList<Message> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String date = getItem(position).getDate();
        String asunto = getItem(position).getAsunto();
        String content = getItem(position).getContent();
        final Boolean read = getItem(position).getRead();

        Message message = new Message(date, asunto, content);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);



        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        TextView hora = (TextView) convertView.findViewById(R.id.hora);
        TextView titulo = (TextView) convertView.findViewById(R.id.asunto);
        final ImageView icono = (ImageView) convertView.findViewById(R.id.icono);

        fecha.setText(date);
        titulo.setText(asunto);


        return convertView;


    }
}