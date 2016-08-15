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
    private final Vector<String> web;
    private final Integer[] imageId;
    public CustomList(Activity context, Vector<String> web, Integer[] imageId) {
        super(context, R.layout.mytextview, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mytextview, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.text1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web.get(position));

        imageView.setImageResource(imageId[0]);
        return rowView;
    }

}
