package com.example.manvaasam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class ListViewAdapter extends ArrayAdapter<packages> {

    //the package list that will be displayed
    private List<packages> packagesList;

    //the context object
    private Context mCtx;

    //here we are getting the packagelist and context
    //so while creating the object of this adapter class we need to give packagelist and context
    public ListViewAdapter(List<packages> packagesList, Context mCtx) {
        super(mCtx, R.layout.list_items, packagesList);
        this.packagesList = packagesList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.manvid);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.stid);

        //Getting the hero for the specified position
        packages packages = packagesList.get(position);

        //setting hero values to textviews
        textViewName.setText("MANVAASAM ID: "+packages.getMid());
        textViewImageUrl.setText("ST Courier ID: "+packages.getSid());

        //returning the listitem
        return listViewItem;
    }
}