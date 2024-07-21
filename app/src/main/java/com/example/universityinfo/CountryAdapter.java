package com.example.universityinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] countries;
    private final int[] flags;

    public CountryAdapter(Context context, String[] countries, int[] flags) {
        super(context, R.layout.list_item_country, countries);
        this.context = context;
        this.countries = countries;
        this.flags = flags;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_country, parent, false);

        TextView textView = rowView.findViewById(R.id.country_name);
        ImageView imageView = rowView.findViewById(R.id.country_flag);

        textView.setText(countries[position]);
        imageView.setImageResource(flags[position]);

        return rowView;
    }
}