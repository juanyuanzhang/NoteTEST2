package com.example.administrator.noteapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ColorAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<ColorItem> colorItems;
    public ColorAdapter(Context c , ArrayList<ColorItem> colorItems) {
        inflater =(LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        this.colorItems = colorItems;
    }

    @Override
    public int getCount() {
        return colorItems.size();
    }

    @Override
    public Object getItem(int position) {
        return colorItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return colorItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ColorItem colorItem  = (ColorItem) getItem(position);
        View v = inflater.inflate(R.layout.listitem,null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.listlayout);
        layout.setBackgroundColor(Color.parseColor(colorItem.color));
        return v;
    }
}
