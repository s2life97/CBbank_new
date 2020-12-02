package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;

public class MyArrayAdapter extends ArrayAdapter<String> {

    private static final int ITEM_HEIGHT = ViewGroup.LayoutParams.WRAP_CONTENT;

    private int textViewResourceId;


    public MyArrayAdapter(Context context,
                          int textViewResourceId,
                          String[] objects) {
        super(context, textViewResourceId, objects);
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            textView = (TextView) LayoutInflater.from(getContext())
                    .inflate(textViewResourceId, parent, false);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(getItem(position));
        if (position == 0) {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.height = 1;
            textView.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.height = ITEM_HEIGHT;
            textView.setLayoutParams(layoutParams);
        }

        return textView;
    }
}
