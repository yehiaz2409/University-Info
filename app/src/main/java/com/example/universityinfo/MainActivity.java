package com.example.universityinfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.country_list);
        String[] countries = getResources().getStringArray(R.array.country_array);

        int[] flags = {
                R.drawable.us,
                R.drawable.uk,
                R.drawable.canada,
                R.drawable.aus,
                R.drawable.germany,
                R.drawable.france,
                R.drawable.japan,
                R.drawable.egypt
        };

        CountryAdapter adapter = new CountryAdapter(this, countries, flags);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCountry = countries[position];
            Intent intent = new Intent(MainActivity.this, University.class);
            intent.putExtra("country", selectedCountry);
            startActivity(intent);
        });
    }
}
