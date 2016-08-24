package com.example.narendra.justcontact;

/**
 * Created by narendra on 8/15/16.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final Vector<String> namess;
    private final Vector<String> contactss;
    private final Vector<String> emailss;
    private final Integer[] imageId;
    public CustomList(Activity context, Vector<String> names,Vector<String> contacts,Vector<String> emails, Integer[] imageId) {
        super(context, R.layout.mytextview, names);
        this.context = context;
        namess = names;
        contactss = contacts;
        emailss = emails;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mytextview, null, true);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.text1);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.text2);
        TextView txtTitle3 = (TextView) rowView.findViewById(R.id.text3);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle1.setText(namess.get(position));
        txtTitle2.setText(contactss.get(position));
        txtTitle3.setText(emailss.get(position));
        imageView.setImageResource(imageId[0]);
        return rowView;
    }

}
