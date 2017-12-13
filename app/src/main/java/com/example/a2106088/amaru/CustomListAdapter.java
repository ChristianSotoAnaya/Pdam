package com.example.a2106088.amaru;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by USER on 04/12/2017.
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final byte[] imgid;


    public CustomListAdapter(Activity context, String[] itemname, byte[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }



    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        Button txtTitle = (Button) rowView.findViewById(R.id.itemButton);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        Log.d("NOMBRE",itemname[position]);
        txtTitle.setText(itemname[position]);
        byte[] bites = imgid;
        Bitmap bmp = BitmapFactory.decodeByteArray(bites, 0, bites.length);
        imageView.setImageBitmap(bmp);


        return rowView;
    };
}


