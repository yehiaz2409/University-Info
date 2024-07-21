package com.example.universityinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class UniversityAdapter extends ArrayAdapter<Uni> {
    private final Context context;
    private final List<Uni> universities;

    public UniversityAdapter(Context context, List<Uni> universities) {
        super(context,0,universities);
        this.context = context;
        this.universities = universities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : LayoutInflater.from(context).inflate(R.layout.list_item_university, parent, false);
        Uni university = getItem(position);

        TextView nameTextView = view.findViewById(R.id.university_name);
        nameTextView.setText(university.getName());

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, Details.class);
            intent.putExtra("university", university.getName());
            intent.putExtra("domain", university.getDomain());
            intent.putExtra("webPage", university.getWebPages());
            context.startActivity(intent);
        });

        return view;
    }
}
